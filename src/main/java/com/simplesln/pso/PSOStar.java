package com.simplesln.pso;

import com.simplesln.fitness.FitnessFunction;

import java.util.*;

public class PSOStar {

    private final int numberOfParticles;
    private final int w;
    private final int c1;
    private final int c2;
    private final Particle particles[];
    private final static int MAX_INT = 999999999;
    private final FitnessFunction fitnessFunction;
    private final int n; //crossover distribution index
    private final int nm; //mutation distribution index
    private final double minRk;//polymonial mutation will happen if rk <= minRk
    private double gbest = MAX_INT;

    public PSOStar(int numberOfParticles, int w, int c1, int c2,int n,int nm,double minRk, FitnessFunction fitnessFunction){
        this.numberOfParticles = numberOfParticles;
        this.w = w;
        this.c1 = c1;
        this.c2 = c2;
        this.n = n;
        this.nm = nm;
        this.minRk = minRk;
        this.fitnessFunction = fitnessFunction;
        particles = new Particle[numberOfParticles];
        for(int i=0;i<numberOfParticles;i++){
            particles[i] = new Particle();
        }
    }

    public void iterate(int numOfIterate){
        for(int i=0;i<numOfIterate;i++){
            //calculate next best location and velocity
            calculateNextLocation();

            //cross over
            crossOver();

            //polynomial mutation
            polynomialMutation();
        }
    }

    private void calculateNextLocation(){
        for(int p=0;p<numberOfParticles;p++){
            Particle particle = particles[p];
            particle.velocity = getVelocity(particle,gbest);
            particle.location.x += particle.velocity.x;
            particle.location.y += particle.velocity.y;

            //adjust limit
            particle.location = adjustLimit(particle.location,fitnessFunction.getLowerLimit(),fitnessFunction.getUpperLimit());

            particle.fitness = fitnessFunction.fitness(particle);
            if(particle.pbest > particle.fitness){
                particle.pbest = particle.fitness;
                particle.locationBest = particle.location;
            }
            if(gbest > particle.fitness){
                gbest = particle.fitness;
            }
        }
    }


    private void polynomialMutation(){
        double rk = Math.random();
        if(rk <= minRk){
            for(int p=0;p<numberOfParticles;p++){
                Particle particle = new PolynomialMutation(particles[p],rk,fitnessFunction,nm).getBestParticle();
                particles[p] = particle;
            }
        }
    }

    private void crossOver(){
        List<Particle> particleList = new ArrayList<Particle>();
        Map<Particle,Boolean> particleFilter = new HashMap<Particle, Boolean>();

        for(int p1=0;p1<numberOfParticles;p1++){
            for(int p2 = p1+1; p2 < numberOfParticles ; p2++) {
                Particle[] newParticles = new SBX(particles[p1], particles[p2], n, fitnessFunction).getBestTwoParticle();
                if (!particleFilter.containsKey(newParticles[0])) {
                    particleFilter.put(newParticles[0], true);
                    particleList.add(newParticles[0]);
                }

                if (!particleFilter.containsKey(newParticles[1])) {
                    particleFilter.put(newParticles[1], true);
                    particleList.add(newParticles[1]);
                }
            }
        }

        Collections.sort(particleList, new Comparator<Particle>() {
            public int compare(Particle o1, Particle o2) {
                return Double.compare(o1.fitness, o2.fitness);
            }
        });

        //replace old generation with current best new generation
        System.arraycopy(particleList.toArray(new Particle[]{}),0,particles,0,numberOfParticles);
    }

    private Location adjustLimit(Location location,double l,double u){
        double diff = 0;
        if(location.x < l){
            // diff = (l-value)
            // make sure diff is not exceed range (u-l)
            // get min of (l-value) and (u-l) to prevent diff not exceed range
            diff = Math.min((l - location.x),(u-l));
            location.x = u - diff;
        }
        else if(location.x > u){
            //check previous explanation
            diff = Math.min((location.x - u),(u-l));
            location.x = l + diff;
        }
        if(location.y < l){
            // diff = (l-value)
            // make sure diff is not exceed range (u-l)
            // get min of (l-value) and (u-l) to prevent diff not exceed range
            diff = Math.min((l - location.y),(u-l));
            location.y = u - diff;
        }
        else if(location.y > u){
            //check previous explanation
            diff = Math.min((location.y - u),(u-l));
            location.x = l + diff;
        }
        return location;
    }

    private Velocity getVelocity(Particle particle, double gbest) {

        double x = (
                (particle.velocity.x * w) +
                        (c1*Math.random()*(particle.pbest - particle.location.x)) +
                        (c2*Math.random()*(gbest - particle.location.x))
        );
        double y = (
                (particle.velocity.y * w) +
                        (c1*Math.random()*(particle.pbest - particle.location.y)) +
                        (c2*Math.random()*(gbest - particle.location.y))
        );
        return new Velocity(x,y);
    }


    public double getGbest(){
        return gbest;
    }

    public double[] getPbest(){
        double[] pbest = new double[numberOfParticles];
        int pos = 0;
        for(Particle particle : particles){
            pbest[pos++] = particle.pbest;
        }
        return pbest;
    }

    public int getNumberOfParticles() {
        return numberOfParticles;
    }

    public int getW() {
        return w;
    }

    public int getC1() {
        return c1;
    }

    public int getC2() {
        return c2;
    }

    public Particle[] getParticles() {
        return particles;
    }
}
