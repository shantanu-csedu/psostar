package com.simplesln.pso;

import com.simplesln.fitness.FitnessFunction;

public class PolynomialMutation {
    private final Particle parent;
    private final Particle child;
    private final int nm;
    private final double rk;
    private final double delta;

    public PolynomialMutation(Particle parent, double rk , FitnessFunction fitnessFunction, int mutationDistributionIndex){
        this.parent = parent;
        this.nm = mutationDistributionIndex;
        this.rk = rk;
        delta = getDelta();
        this.child = new Particle();
        this.child.velocity = parent.velocity;
        this.child.location.x = parent.location.x + (fitnessFunction.getUpperLimit() - fitnessFunction.getLowerLimit()) * delta;
        this.child.location.y = parent.location.y + (fitnessFunction.getUpperLimit() - fitnessFunction.getLowerLimit()) * delta;
        this.child.fitness = fitnessFunction.fitness(child);
    }

    public Particle getBestParticle(){
        if(parent.fitness < child.fitness) return parent;
        return child;
    }


    private double getDelta(){
        if(rk < 0.5){
            return Math.pow((2.0 * rk),(1.0/(nm+1))) -1.0 ;
        }
        else{
            return 1.0 - Math.pow((2.0 *(1.0 - rk)),(1.0/(nm+1)));
        }
    }
}
