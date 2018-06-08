package com.simplesln.pso;

import com.simplesln.fitness.FitnessFactory;

public class Main {
    public static void main(String[] args){
        PSOStar psoStar = new PSOStar(60,0,0,1, 20,20,0.01,
                FitnessFactory.getFitnessFunction(2,0,1,FitnessFactory.ROSENBROCK));
        //rosenbrock
        System.out.println("Starting 500 iteration");
        psoStar.iterate(500);
        //get required values from psoStar obj
        System.out.print("Gbest " + psoStar.getGbest());
        // psoStar.getPbest() ....

    }
}
