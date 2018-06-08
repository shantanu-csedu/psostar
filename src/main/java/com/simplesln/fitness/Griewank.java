package com.simplesln.fitness;

import com.simplesln.data.Particle;

public class Griewank extends FitnessFunction{
    Griewank(int d,double l, double u) {
        super(d,l,u);
    }

    public double fitness(Particle particle) {
        return 0;
    }
}
