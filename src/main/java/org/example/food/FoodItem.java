package org.example.food;

public class FoodItem {

    //region Instance Variables (one of each variable per instance of FoodItem)

    private String _name;
    private FoodCategory _category;

    private int _calories;        // kcal per serving
    private double _priceEuro;    // € per serving

    private int _sugarGrams;      // g per serving
    private int _proteinGrams;    // g per serving

    private String _notes;

    //endregion

    //region Constructors

    /// <summary>
    /// Creates a new food item with common nutrition and cost attributes suitable for measurement/filtering demos.
    /// </summary>
    /// <param name="name">Display name of the food item.</param>
    /// <param name="category">Broad category (e.g., snack, drink, meal).</param>
    /// <param name="calories">Calories (kcal) per serving.</param>
    /// <param name="priceEuro">Price (€) per serving.</param>
    /// <param name="sugarGrams">Sugar grams per serving.</param>
    /// <param name="proteinGrams">Protein grams per serving.</param>
    /// <param name="notes">Optional notes.</param>
    /// <see cref="FoodCategory"/>
    public FoodItem(
            String name,
            FoodCategory category,
            int calories,
            double priceEuro,
            int sugarGrams,
            int proteinGrams,
            String notes
    ) {
        _name = name;
        _category = category;
        _calories = calories;
        _priceEuro = priceEuro;
        _sugarGrams = sugarGrams;
        _proteinGrams = proteinGrams;
        _notes = notes;
    }

    //endregion

    //region Getters

    public String getName() {
        return _name;
    }

    public FoodCategory getCategory() {
        return _category;
    }

    public int getCalories() {
        return _calories;
    }

    public double getPriceEuro() {
        return _priceEuro;
    }

    public int getSugarGrams() {
        return _sugarGrams;
    }

    public int getProteinGrams() {
        return _proteinGrams;
    }

    public String getNotes() {
        return _notes;
    }

    //endregion

    //region Validation Helpers

    /// <summary>
    /// Checks whether the food item has a non-blank name.
    /// </summary>
    public boolean hasValidName() {
        return _name != null && !_name.isBlank();
    }

    /// <summary>
    /// Checks whether numeric fields are within reasonable demo ranges (non-negative).
    /// </summary>
    public boolean hasValidNumbers() {
        return _calories >= 0 &&
                _priceEuro >= 0 &&
                _sugarGrams >= 0 &&
                _proteinGrams >= 0;
    }

    /// <summary>
    /// Checks whether a category has been assigned.
    /// </summary>
    public boolean hasValidCategory() {
        return _category != null;
    }

    //endregion

    //region Overrides

    @Override
    public String toString() {
        return "FoodItem{" +
                "name='" + _name + '\'' +
                ", category=" + _category +
                ", calories=" + _calories +
                ", priceEuro=" + _priceEuro +
                ", sugarGrams=" + _sugarGrams +
                ", proteinGrams=" + _proteinGrams +
                ", notes='" + _notes + '\'' +
                '}';
    }

    /// <summary>
    /// Returns a neatly formatted, aligned summary of this food item.
    /// This is useful for console demos where you want readable output on a projector.
    /// </summary>
    /// <returns>A formatted multi-line string describing this food item.</returns>
    public String toPrettyString() {

        // Use String.format so columns line up nicely in console output.
        // %-12s  -> left-align a label in a 12 character column
        // %-14s  -> left-align a second label column (for the food name/category line)
        // %8d    -> right-align an integer in an 8 character column
        // %8.2f  -> right-align a decimal number in an 8 character column with 2 decimal places
        // %n     -> platform-safe newline (Windows/macOS/Linux)
        return String.format(
                "%-12s %-14s%n" +
                        "%-12s %-14s%n" +
                        "%-12s %8d kcal%n" +
                        "%-12s %8.2f €%n" +
                        "%-12s %8d g%n" +
                        "%-12s %8d g%n" +
                        "%-12s %-14s%n",
                "Name:", _name,
                "Category:", String.valueOf(_category),
                "Calories:", _calories,
                "Price:", _priceEuro,
                "Sugar:", _sugarGrams,
                "Protein:", _proteinGrams,
                "Notes:", _notes == null ? "" : _notes
        );
    }


    //endregion
}
