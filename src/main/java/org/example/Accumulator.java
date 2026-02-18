package org.example;

import java.text.DecimalFormat;

public class Accumulator {

    /// <summary>
    /// Shared formatter used to present numeric values (e.g., standard deviation) with a small number of decimals.
    /// </summary>
    /// <see cref="DecimalFormat"/>
    private static DecimalFormat df = new DecimalFormat("###.###");
    // Accumulator.df.format(...) can be used anywhere in this class.

    // Instance variables (state tracked as we update the accumulator)

    private double min = Double.MAX_VALUE;   // smallest string length seen so far
    private double max = Double.MIN_VALUE;   // largest string length seen so far (NOTE: Double.MIN_VALUE is smallest positive number)
    private double mean = 0;              // running average length
    private double sum = 0;                  // total of all string lengths so far
    private int count = 0;                   // number of strings processed
    private double stdDev = 0;               // running standard deviation of string lengths
    private double sumStdDev = 0;            // running sum of squared diffs (used to compute std dev)

    /// <summary>
    /// Gets the current standard deviation for the accumulated string lengths.
    /// </summary>
    /// <returns>The current standard deviation value.</returns>
    public double getStdDev() {
        return stdDev;
    }

    /// <summary>
    /// Converts the current accumulator state to a readable string for debugging/printing.
    /// </summary>
    /// <returns>A string containing the tracked statistics.</returns>
    @Override
    public String toString() {

        return "Accumulator{" +
                "min=" + min +
                ", max=" + max +
                ", average=" + mean +
                ", sum=" + sum +
                ", count=" + count +
                ", stdDev=" + Accumulator.df.format(stdDev) +
                '}';
    }

    /// <summary>
    /// Updates the standard deviation based on the current string and the current running average.
    /// </summary>
    /// <param name="current">The current string being processed.</param>
    protected void updateStdDev(String current) {

        // Compute difference between this string's length and the current running average
        double diff = current.length() - mean;

        // Add squared difference to the running total of squared diffs
        sumStdDev += diff * diff;

        // Standard deviation = sqrt( (sum of squared diffs) / count )
        // Note: this uses the current average and an incremental sum of squared diffs.
        stdDev = Math.sqrt(sumStdDev / count);
    }

    /// <summary>
    /// Creates a new accumulator with default initial state.
    /// </summary>
    public Accumulator() {
        // Defaults are already set in field initialisers.
    }

    /// <summary>
    /// Updates the accumulator with one string.
    /// The accumulator records statistics based on the string's length.
    /// </summary>
    /// <param name="data">The string to include in the statistics.</param>
    public void update(String data) {

        // Defensive coding: ignore null or empty strings
        if (data == null || data.length() == 0)
            return;

        // Update each part of the tracked state
        updateMin(data);
        updateMax(data);
        updateMean(data);
        updateStdDev(data);
    }

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
    /// Gets the running average string length.
    /// </summary>
    /// <returns>The current average string length.</returns>
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
    /// Updates the running average by incrementing the count, adding the current length,
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

        if (length < min) {
            min = length;
        }
    }

    /// <summary>
    /// Updates the tracked maximum length if the current string is longer than the current maximum.
    /// </summary>
    /// <param name="current">The current string being processed.</param>
    protected void updateMax(String current) {
        double length = current.length();
        if (length > max) {
            max = length;
        }
    }
}
