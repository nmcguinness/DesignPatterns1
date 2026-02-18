package org.example.core;

import java.text.DecimalFormat;
import java.util.function.Function;
import java.util.function.Predicate;

public class GenericAccumulator {

    //region Class Variables

    /// <summary>
    /// Shared formatter used to present numeric values (e.g., standard deviation) with a small number of decimals.
    /// </summary>
    /// <see cref="DecimalFormat"/>
    private static DecimalFormat df = new DecimalFormat("###.##");

    //endregion

    //region Instance Variables (one of each variable per instance of Accumulator)

    /// <summary>
    /// The smallest measured value observed so far.
    /// Starts at <see cref="Double.POSITIVE_INFINITY"/> so the first accepted measurement becomes the new minimum.
    /// </summary>
    private double min = Double.POSITIVE_INFINITY;

    /// <summary>
    /// The largest measured value observed so far.
    /// Starts at <see cref="Double.NEGATIVE_INFINITY"/> so the first accepted measurement becomes the new maximum.
    /// </summary>
    private double max = Double.NEGATIVE_INFINITY;

    /// <summary>
    /// The running mean (average) of string lengths.
    /// </summary>
    private double mean = 0;

    /// <summary>
    /// The running sum of all string lengths processed so far.
    /// </summary>
    private double sum = 0;

    /// <summary>
    /// The number of strings processed so far (excluding null inputs).
    /// </summary>
    private int count = 0;

    /// <summary>
    /// The running standard deviation of string lengths.
    /// </summary>
    private double stdDev = 0;

    /// <summary>
    /// Running total of squared differences used to compute standard deviation.
    /// </summary>
    private double sumStdDev = 0;

    //endregion

    //region Getters

    public double getMin() { return min; }
    public double getMax() { return max; }
    public double getMean() { return mean; }
    public double getSum() { return sum; }
    public int getCount() { return count; }
    public double getStdDev() { return stdDev; }

    //endregion

    //region Constructors

    /// <summary>
    /// Creates a new accumulator with default initial state.
    /// </summary>
    public GenericAccumulator() {
    }

    //endregion

    //region Class-specific

    /// <summary>
    /// Resets the accumulator back to its initial state.
    /// </summary>
    public void reset() {

        this.min = Double.MAX_VALUE;
        this.max = Double.MIN_VALUE;
        this.mean = 0;
        this.count = 0;
        this.sum = 0;
        this.sumStdDev = 0;
        this.stdDev = 0;
    }

    /// <summary>
    /// Updates the accumulator using a target object, a measurement function and a filter predicate.
    /// </summary>
    public <T> void update(T target, Function<T, Double> measurer, Predicate<T> filter) {

        if (target == null)
            return;

        if (measurer == null || filter == null)
            return;

        if (filter.test(target) == false)
            return;

        double measure = measurer.apply(target).doubleValue();

        if (measure < min)
            min = measure;

        if (measure > max)
            max = measure;

        count++;
        sum += measure;
        mean = sum / count;

        double diff = measure - mean;
        sumStdDev += diff * diff;
        stdDev = Math.sqrt(sumStdDev / count);
    }

    //endregion

    //region Overrides

    @Override
    public String toString() {

        return  "min: " + df.format(min) + "\n" +
                "max: " + df.format(max) + "\n" +
                "mean: " + df.format(mean) + "\n" +
                "sum: " + df.format(sum) + "\n" +
                "count: " + count + "\n" +
                "stdDev: " + df.format(stdDev) + "\n";
    }

    /// <summary>
    /// Returns a nicely aligned version of the stats. Uses <see cref="DecimalFormat"/> so the
    /// numeric formatting matches <see cref="toString"/>.
    /// </summary>
    public String toPrettyString() {

        return String.format(
                "  %-10s %10s%n" +
                        "  %-10s %10s%n" +
                        "  %-10s %10s%n" +
                        "  %-10s %10s%n" +
                        "  %-10s %10d%n" +
                        "  %-10s %10s%n",
                "min:", df.format(min),
                "max:", df.format(max),
                "mean:", df.format(mean),
                "sum:", df.format(sum),
                "count:", count,
                "stdDev:", df.format(stdDev)
        );
    }

    //endregion
}
