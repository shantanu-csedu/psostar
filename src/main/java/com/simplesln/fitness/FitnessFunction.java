package com.simplesln.fitness;

import com.simplesln.data.Particle;

/*
Super class for fitness function
 */
public abstract class FitnessFunction {
    protected double l;
    protected double u;

    FitnessFunction(double l,double u){
        this.l = l;
        this.u = u;
    }
    public abstract double fitness(Particle particle);

    public double getLowerLimit() {
        return l;
    }

    public double getUpperLimit() {
        return u;
    }
}
