package org.example;

public class Accumulator {
    private double min = Double.MAX_VALUE;
    private double max = Double.MIN_VALUE;
    private double average = 0;
    private double sum = 0;
    private int count = 0;
    private double stdDev = 0;
    private double sumStdDev = 0;

    //new toString, call in update, getter (stddev), update reset

    public double getStdDev() {
        return stdDev;
    }

    @Override
    public String toString() {
        return "Accumulator{" +
                "min=" + min +
                ", max=" + max +
                ", average=" + average +
                ", sum=" + sum +
                ", count=" + count +
                ", stdDev=" + stdDev +
                ", sumStdDev=" + sumStdDev +
                '}';
    }

    protected void updateStdDev(String current)
    {
        //substract length - average
        double diff = current.length() - average;
        //square it and add to sumStdDev
        sumStdDev += diff*diff;
        //divide count and sqrt
        stdDev = Math.sqrt(sumStdDev/count);
    }

    //constructor
    public Accumulator()
    {
    }

    //update
    public void update(String data){
        if(data == null || data.length() == 0)
            return;

        updateMin(data);
        updateMax(data);
        updateAverage(data);
        updateStdDev(data);
    }
    //reset
    public void reset(){
        this.min = Double.MAX_VALUE;
        this.max = Double.MIN_VALUE;
        this.average = 0;
        this.count = 0;
        this.sum = 0;
        this.sumStdDev = 0;
        this.stdDev = 0;
    }

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

    protected void updateAverage(String current)
    {
        count++;
        sum +=current.length();
        average = sum/count;
    }

    protected void updateMin(String current){
        double length = current.length();
        if(length < min){
            min = length;
        }
    }
    protected void updateMax(String current){
        double length = current.length();
        if(length > max){
            max = length;
        }
    }

}
