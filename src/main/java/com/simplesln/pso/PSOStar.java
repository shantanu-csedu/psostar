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
    private double gbest = MAX_INT;

    public PSOStar(int numberOfParticles, int w, int c1, int c2,int n, FitnessFunction fitnessFunction){
        this.numberOfParticles = numberOfParticles;
        this.w = w;
        this.c1 = c1;
        this.c2 = c2;
        this.n = n;
        this.fitnessFunction = fitnessFunction;
        particles = new Particle[numberOfParticles];
        for(int i=0;i<numberOfParticles;i++){
            particles[i] = new Particle();
        }
    }

    public void iterate(int numOfIterate){
        gbest = MAX_INT;
        for(int i=0;i<numOfIterate;i++){
            //calculate next best location and velocity
            calculateNextBestLocation();

            //cross over
            crossOver();
        }
    }

    private void calculateNextBestLocation(){
        double fitness;
        for(int p=0;p<numberOfParticles;p++){
            Particle particle = particles[p];
            particle.velocity = getVelocity(particle,gbest);
            particle.location.x += particle.velocity.x;
            particle.location.y += particle.velocity.y;

            //adjust limit
            particle.location = adjustLimit(particle.location,fitnessFunction.getLowerLimit(),fitnessFunction.getUpperLimit());

            fitness = fitnessFunction.fitness(particle);
            if(particle.pbest > fitness){
                particle.pbest = fitness;
                particle.locationBest = particle.location;
            }
            if(gbest > fitness){
                gbest = fitness;
            }
        }
    }

    private void crossOver(){
        List<Particle> particleList = new ArrayList<Particle>();
        int pos = 0;
        for(int p1=0;p1<numberOfParticles;p1++){
            for(int p2 = p1+1; p2 < numberOfParticles ; p2++){
                Particle[] newParticles = new SBX(particles[p1], particles[p2], n).getBestTwoParticle();
                particleList.add(newParticles[0]);
                particleList.add(newParticles[1]);
            }
        }
        Collections.sort(particleList, new Comparator<Particle>() {
            public int compare(Particle o1, Particle o2) {
                return (int) (o1.location.x - o2.location.x);
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
