package org.example.core;

import org.example.utilities.MathHelper;

import java.text.DecimalFormat;

public class Accumulator {

    //region Class Variables

    /// <summary>
    /// Shared formatter used to present numeric values (e.g., standard deviation) with a small number of decimals.
    /// </summary>
    /// <see cref="DecimalFormat"/>
    private static DecimalFormat df = new DecimalFormat("###.###");

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

    //region Constructors

    /// <summary>
    /// Creates a new accumulator with default initial state.
    /// </summary>
    public Accumulator() {
        // Defaults are already set in field initialisers.
    }

    //endregion

    //region Getters

    /// <summary>
    /// Gets the minimum string length observed so far.
    /// </summary>
    /// <returns>The smallest observed string length.</returns>
    public double getMin() {
        return min;
    }

    /// <summary>
    /// Gets the maximum string length observed so far.
    /// </summary>
    /// <returns>The largest observed string length.</returns>
    public double getMax() {
        return max;
    }

    /// <summary>
    /// Gets the running mean (average) string length.
    /// </summary>
    /// <returns>The current mean string length.</returns>
    public double getMean() {
        return mean;
    }

    /// <summary>
    /// Gets the sum of all string lengths processed so far.
    /// </summary>
    /// <returns>The total length sum.</returns>
    public double getSum() {
        return sum;
    }

    /// <summary>
    /// Gets the number of strings processed so far.
    /// </summary>
    /// <returns>The count of processed strings.</returns>
    public int getCount() {
        return count;
    }

    /// <summary>
    /// Gets the current standard deviation for the accumulated string lengths.
    /// </summary>
    /// <returns>The current standard deviation value.</returns>
    public double getStdDev() {
        return stdDev;
    }

    //endregion

    //region Methods

    /// <summary>
    /// Updates the accumulator with one string.
    /// The accumulator records statistics based on the string's length.
    /// </summary>
    /// <param name="data">The string to include in the statistics.</param>
    public void update(String data) {

        // Defensive coding: ignore null
        if (data == null)
            return;

        // Update each part of the tracked state
        updateMin(data);
        updateMax(data);
        updateMean(data);
        updateStdDev(data);
    }

    /// <summary>
    /// Resets the accumulator back to its initial state.
    /// After calling this, it is as if no strings have been processed.
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
    /// Updates the running mean by incrementing count, adding the current length to sum,
    /// and recomputing mean as sum / count.
    /// </summary>
    /// <param name="current">The current string being processed.</param>
    protected void updateMean(String current) {

        // Increase sample count
        count++;

        // Add this string's length to the running sum
        sum += current.length();

        // Recompute mean
        mean = sum / count;
    }

    /// <summary>
    /// Updates the tracked minimum length if the current string is shorter than the current minimum.
    /// </summary>
    /// <param name="current">The current string being processed.</param>
    protected void updateMin(String current) {

        double length = current.length();

        if (length < min)
            min = length;
    }

    /// <summary>
    /// Updates the tracked maximum length if the current string is longer than the current maximum.
    /// </summary>
    /// <param name="current">The current string being processed.</param>
    protected void updateMax(String current) {

        double length = current.length();

        if (length > max)
            max = length;
    }

    /// <summary>
    /// Updates the standard deviation based on the current string length and the current running mean.
    /// </summary>
    /// <param name="current">The current string being processed.</param>
    protected void updateStdDev(String current) {

        // Compute difference between this string's length and the current running mean
        double diff = current.length() - mean;

        // Add squared difference to the running total of squared diffs
        sumStdDev += diff * diff;

        // Standard deviation = sqrt( (sum of squared diffs) / count )
        // Note: this uses the current mean and an incremental sum of squared diffs.
        stdDev = Math.sqrt(sumStdDev / count);
    }

    //endregion

    //region Overrides

    /// <summary>
    /// Converts the current accumulator state to a readable multi-line string for debugging/printing.
    /// Uses <see cref="MathHelper"/> to round mean and std dev for readability.
    /// </summary>
    /// <returns>A multi-line string containing the tracked statistics.</returns>
    /// <see cref="MathHelper"/>
    @Override
    public String toString() {
        return  "min: " + min + "\n" +
                "max: " + max + "\n" +
                "mean: " + MathHelper.round(mean) + "\n" +
                "sum: " + sum + "\n" +
                "count: " + count + "\n" +
                "stdDev: " + MathHelper.round(stdDev) + "\n";
    }

    /// <summary>
    /// Returns a neatly formatted, aligned version of the statistics using <see cref="String.format"/>.
    /// This is suitable for console output on a projector (easy to scan).
    /// </summary>
    /// <returns>A formatted string containing accumulator stats in aligned columns.</returns>
    public String toPrettyString() {
        return String.format(
                "  %-10s %10.2f%n" +
                        "  %-10s %10.2f%n" +
                        "  %-10s %10.2f%n" +
                        "  %-10s %10.2f%n" +
                        "  %-10s %10d%n" +
                        "  %-10s %10.2f%n",
                "min:", min,
                "max:", max,
                "mean:", mean,
                "sum:", sum,
                "count:", count,
                "stdDev:", stdDev
        );
    }

    //endregion
}
