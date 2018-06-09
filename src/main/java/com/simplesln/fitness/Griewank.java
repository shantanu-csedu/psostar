package com.simplesln.fitness;

import com.simplesln.data.Particle;

public class Griewank extends FitnessFunction{
    Griewank(double l, double u) {
        super(l,u);
    }

    public double fitness(Particle particle) {
        return 0;
    }
}
