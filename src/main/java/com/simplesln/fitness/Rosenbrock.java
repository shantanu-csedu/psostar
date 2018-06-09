package com.simplesln.fitness;

import com.simplesln.data.Particle;

public class Rosenbrock extends FitnessFunction {

    Rosenbrock(double l, double u) {
        super(l,u);
    }

    public double fitness(Particle particle) {
        double result = 0;
        for(int i=0;i<particle.dimension-1;i++){
            result += (100.0 * (particle.location.x[i+1] - particle.location.x[i] * particle.location.x[i]) *
                    (particle.location.x[i+1] - particle.location.x[i] * particle.location.x[i]) +
                    ((particle.location.x[i] - 1) * (particle.location.x[i] - 1)));
        }
        return result;
    }
}
