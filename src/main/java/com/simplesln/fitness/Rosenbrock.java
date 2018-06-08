package com.simplesln.fitness;

import com.simplesln.pso.Particle;

public class Rosenbrock extends FitnessFunction {

    Rosenbrock(int d,double l, double u) {
        super(d,l,u);
    }

    public double fitness(Particle particle) {
        double result = 0;
        for(int i=0;i<d;i++){
            result += (100.0 * (particle.location.y - particle.location.x * particle.location.x) *
                    (particle.location.y - particle.location.x * particle.location.x)) +
                    ((particle.location.x - 1) * (particle.location.x - 1));
        }
        return result;
    }
}
