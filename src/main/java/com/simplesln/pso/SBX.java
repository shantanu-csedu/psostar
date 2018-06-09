package com.simplesln.pso;

import com.simplesln.data.Location;
import com.simplesln.data.Particle;
import com.simplesln.fitness.FitnessFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SBX {
    private final Particle parent1;
    private final Particle parent2;
    private final Particle child1;
    private final Particle child2;
    private final int n; //crossover distribution index
    private final double beta;
    private final FitnessFunction fitnessFunction;

    /*
    Simulated Binary Crossover
    @param
    parent1
    parent2
    n : crossover distribution index
     */
    public SBX(Particle parent1, Particle parent2, int n, FitnessFunction fitnessFunction){
        this.parent1 = parent1;
        this.parent2 = parent2;
        this.n = n;
        this.fitnessFunction = fitnessFunction;
        this.beta = calculateBeta();

        this.child1 = new Particle(parent1.dimension);
        this.child1.velocity = parent1.velocity; //not sure which parent velocity should be inherited
        crossOV1(this.child1.location);
        this.child1.fitness = fitnessFunction.fitness(child1);

        this.child2 = new Particle(parent2.dimension);
        this.child2.velocity = parent2.velocity;
        crossOV2(this.child2.location);
        this.child2.fitness = fitnessFunction.fitness(child2);
    }

    /*
    Beta calculation function
     */
    private double calculateBeta(){
        double u = Math.random();
        if(u <= 0.5){
            return Math.pow(2.0*u,(1.0/(n+1)));
        }
        else{
            return Math.pow(2.0*(1.0-u),(-1.0/(n+1)));
        }
    }

    private void crossOV1(Location location){
        for(int i=0;i<location.d;i++){
            location.x[i] = 0.5 * ((1.0-beta)*parent1.location.x[i] + (1.0+beta)*parent2.location.x[i]);
        }
    }

    private void crossOV2(Location location){
        for(int i=0;i<location.d;i++){
            location.x[i] = 0.5 * ((1.0-beta)*parent2.location.x[i] + (1.0+beta)*parent1.location.x[i]);
        }
    }

    public Particle[] getBestTwoParticle(){
        List<Particle> particles = new ArrayList<Particle>();
        particles.add(parent1);
        particles.add(parent2);
        particles.add(child1);
        particles.add(child2);
        Collections.sort(particles, new Comparator<Particle>() {
            public int compare(Particle o1, Particle o2) {
//                return (int) (o1.location.x - o2.location.x);
                return Double.compare(o1.fitness, o2.fitness);
            }
        });
        return new Particle[]{particles.get(0),particles.get(1)};
    }

}
