package org.example.utilities;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathHelper {

    //region Class Variables

    /// <summary>
    /// Default number of decimal places used by <see cref="round(double)"/>.
    /// </summary>
    public static final int ROUND_PRECISION = 2;

    //endregion

    //region Class Methods

    /// <summary>
    /// Rounds a value using the default <see cref="ROUND_PRECISION"/>.
    /// </summary>
    /// <param name="value">The value to round.</param>
    /// <returns>The rounded value.</returns>
    /// <see cref="ROUND_PRECISION"/>
    public static double round(double value) {
        return round(value, ROUND_PRECISION);
    }

    /// <summary>
    /// Rounds a value to a specified number of decimal places.
    /// Uses <see cref="RoundingMode.HALF_UP"/> which matches typical "school rounding".
    /// </summary>
    /// <param name="value">The value to round.</param>
    /// <param name="precision">Number of decimal places (must be >= 0).</param>
    /// <returns>The rounded value.</returns>
    /// <see cref="BigDecimal"/>
    public static double round(double value, int precision) {

        if (precision < 0)
            throw new IllegalArgumentException("precision is negative.");

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(precision, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    //endregion
}
