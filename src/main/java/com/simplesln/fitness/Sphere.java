package com.simplesln.fitness;

import com.simplesln.data.Particle;

public class Sphere extends FitnessFunction{
    Sphere(double l, double u) {
        super(l,u);
    }

    public double fitness(Particle particle) {
        double result = 0;
        for(int i=0;i<particle.dimension;i++){
            result += particle.location.x[i]*particle.location.x[i];
        }
        return result;
    }
}
