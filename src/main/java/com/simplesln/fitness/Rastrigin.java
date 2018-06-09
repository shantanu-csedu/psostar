package com.simplesln.fitness;

import com.simplesln.data.Particle;

public class Rastrigin extends FitnessFunction{
    Rastrigin(double l, double u) {
        super(l,u);
    }

    public double fitness(Particle particle) {
        double result = 10.0*particle.dimension;
        for(int i=0;i<particle.dimension;i++){
            result += (particle.location.x[i]*particle.location.x[i]) -
                    (10.0*Math.cos(2.0*Math.PI*particle.location.x[i]));
        }
        return result;
    }
}
