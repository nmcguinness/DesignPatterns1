package org.example;

public class Accumulator {
    private double min = Double.MAX_VALUE;
    private double max = Double.MIN_VALUE;
    private double average = 0;
    private double sum = 0;
    private int count = 0;

    //constructor
    public Accumulator()
    {
    }

    //update

    //reset

    //toString

    //getters only
    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public double getAverage() {
        return average;
    }

    public double getSum() {
        return sum;
    }

    public int getCount() {
        return count;
    }

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
