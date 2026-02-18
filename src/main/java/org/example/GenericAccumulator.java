package org.example;

public class GenericAccumulator {
    private double min;
    private double max;
    private double mean;

    public GenericAccumulator() {}
    public double getMin() {return min;}
    public double getMax() {return max;}
    public double getMean() {return mean;}

    public void update(String value){
        double measure = value.length();
        //min

        //max

        //mean
    }
}
