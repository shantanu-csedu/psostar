package com.simplesln.fitness;

import com.simplesln.data.Particle;

public class Ackley extends FitnessFunction{
    private static double a = 20;
    private static double b = 0.2;
    private static double c = 2.0*Math.PI;


    Ackley(double l, double u) {
        super(l,u);
    }

    public double fitness(Particle particle)
    {
        double sum = 0;
        double sumCos = 0;
        for(int i=0;i<particle.dimension;i++){
            sum += (particle.location.x[i] * particle.location.x[i]);
            sumCos += Math.cos(c*particle.location.x[i]);
        }
        double result = -a * Math.exp(
                -b * Math.sqrt((1.0/particle.dimension)*sum)
        ) - Math.exp((1.0/particle.dimension) * sumCos) + a + Math.exp(1.0);
        return result;
    }
}
