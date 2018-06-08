package com.simplesln.fitness;

import com.simplesln.data.Particle;

/*
Super class for fitness function
 */
public abstract class FitnessFunction {
    protected int d;
    protected double l;
    protected double u;

    FitnessFunction(int d,double l,double u){
        this.d = d;
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
