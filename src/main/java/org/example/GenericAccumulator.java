package org.example;

import java.util.function.Function;

public class GenericAccumulator {
    private double min = Double.POSITIVE_INFINITY;
    private double max = Double.NEGATIVE_INFINITY;
    private double mean, sum;
    private int count;

    public GenericAccumulator() {}
    public double getMin() {return min;}
    public double getMax() {return max;}
    public double getMean() {return mean;}

    public void reset() {min = max = mean = sum = 0; count = 0;}

    public <T> void update(T value, Function<T, Double> measurer){
        double measure = measurer.apply(value).doubleValue();
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
