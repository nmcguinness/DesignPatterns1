package org.example;

public class Accumulator {
    private double min = Double.MAX_VALUE;
    private double max = Double.MIN_VALUE;
    private double average;
    private double sum;
    private int count;
    public void updateAverage(String current)
    {
        sum +=current.length();
        average = sum/count;
    }

    public void updateMin(String current){
        double length = current.length();
        if(length < min){
            min = length;
        }
    }
    public void updateMax(String current){
        double length = current.length();
        if(length > max){
            max = length;
        }
    }

}
