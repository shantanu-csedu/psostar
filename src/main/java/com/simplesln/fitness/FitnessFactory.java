package com.simplesln.fitness;

public class FitnessFactory {
    public static final int ROSENBROCK = 1;
    public static final int RASTRIGIN = 2;
    public static final int SPHERE = 3;
    public static final int ACKLEY = 4;
    public static final int GRIEWANK = 5;

    /*
    Fitness factory
    @param
    d : summation range
    l : lower limit of X
    u : upper limit of Y

     */
    public static FitnessFunction getFitnessFunction(double l,double u, int fitnessFunction){
        switch (fitnessFunction){
            case ROSENBROCK:
                return new Rosenbrock(l,u);
            case RASTRIGIN:
                return new Rastrigin(l,u);
            case SPHERE:
                return new Sphere(l,u);
            case ACKLEY:
                return new Ackley(l,u);
            case GRIEWANK:
                return new Griewank(l,u);
            default:
                return null;
        }
    }
}
