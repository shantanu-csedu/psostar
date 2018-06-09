package com.simplesln.fitness;

import com.simplesln.data.Particle;

public class Griewank extends FitnessFunction{
    Griewank(double l, double u) {
        super(l,u);
    }

    public double fitness(Particle particle) {
        double sum = 0;
        double prod = 1;
        for(int i=0;i<particle.dimension;i++){
            sum += (particle.location.x[i]*particle.location.x[i]) / 4000.0;
            prod *= (Math.cos(particle.location.x[i] / Math.sqrt(i+1)) + 1.0);
        }
        return (sum - prod);
    }
}
