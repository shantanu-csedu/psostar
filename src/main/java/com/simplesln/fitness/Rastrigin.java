package com.simplesln.fitness;

import com.simplesln.pso.Particle;

public class Rastrigin extends FitnessFunction{
    Rastrigin(int d,double l, double u) {
        super(d,l,u);
    }

    public double fitness(Particle particle) {
        return 0;
    }
}
