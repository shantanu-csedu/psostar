package com.simplesln.data;

public class Particle {
    public Location location = new Location();
    public Location locationBest = new Location();
    public double fitness = 999999999;
    public double pbest = 999999999;
    public Velocity velocity = new Velocity(1,1);

}
