package org.example;

public class GenericAccumulator {
    private double min;
    private double max;
    private double mean, sum;
    private int count;

    public GenericAccumulator() {}
    public double getMin() {return min;}
    public double getMax() {return max;}
    public double getMean() {return mean;}

    public void update(String value){
        double measure = value.length();
        //min
        if(measure < min) min = measure;
        //max
        if(measure > max) max = measure;
        //mean
        count++;
        sum += measure;
        mean /=count;
    }
}
