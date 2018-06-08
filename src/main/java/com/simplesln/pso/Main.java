package com.simplesln.pso;

import com.simplesln.fitness.FitnessFactory;

public class Main {
    public static void main(String[] args){
        PSOStar psoStar = new PSOStar(100,0,0,1, 20,
                FitnessFactory.getFitnessFunction(2,0,1,FitnessFactory.ROSENBROCK));
        //rosenbrock
        psoStar.iterate(100);
        //get required values from psoStar obj
        System.out.print("Gbest " + psoStar.getGbest());
        // psoStar.getPbest() ....

    }
}
