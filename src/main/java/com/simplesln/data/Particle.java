package com.simplesln.data;

public class Particle {
    private final static int MAX_INT = 999999999;
    public Location location;
    public Location locationBest;
    public double fitness;
    public double pbest;
    public Velocity velocity;
    public int dimension;

    public Particle(int d){
        this.dimension = d;
        location = new Location(d);
        locationBest = new Location(d);
        fitness = MAX_INT;
        pbest = MAX_INT;
        velocity = new Velocity(d);
    }

    public void updateLocation(){
        for(int i=0;i<location.d;i++){
            location.x[i] += velocity.x[i];
        }
    }

    public void updateVelocity(int w,int c1,int c2,double gbest) {
        for(int i=0;i<velocity.d;i++){
            velocity.x[i] = (velocity.x[i] * w) +
                    (c1*Math.random()*(pbest - location.x[i])) +
                    (c2*Math.random()*(gbest - location.x[i]));
        }
    }

    public Location adjustLocationLimit(double l, double u){
        double diff = 0;
        for(int i=0;i<location.d;i++){
            if(location.x[i] < l){
                // diff = (l-value)
                // make sure diff is not exceed range (u-l)
                // get min of (l-value) and (u-l) to prevent diff not exceed range
                diff = Math.min((l - location.x[i]),(u-l));
                location.x[i] = u - diff;
            }
            else if(location.x[i] > u){
                //check previous explanation
                diff = Math.min((location.x[i] - u),(u-l));
                location.x[i] = l + diff;
            }
        }
        return location;
    }

}
