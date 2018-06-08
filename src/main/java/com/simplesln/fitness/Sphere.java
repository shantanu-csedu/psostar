package com.simplesln.fitness;

import com.simplesln.pso.Particle;

public class Sphere extends FitnessFunction{
    Sphere(int d,double l, double u) {
        super(d,l,u);
    }

    public double fitness(Particle particle) {
        return 0;
    }
}
