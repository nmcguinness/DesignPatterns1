package org.example;

import org.example.core.Accumulator;
import org.example.core.GenericAccumulator;
import org.example.incidents.IncidentReport;
import org.example.incidents.IncidentType;
import org.example.incidents.ZoneType;
import org.example.food.FoodItem;
import org.example.food.FoodCategory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {

    /// <summary>
    /// Entry point for the demo application. Creates a <see cref="Main"/> instance and runs
    /// two demonstrations: a basic string-length accumulator and a generic accumulator demo.
    /// </summary>
    /// <see cref="Main"/>
    public static void main(String[] args) {

        System.out.println(
                "Accumulator Demo\n" +
                        "----------------\n" +
                        "This example starts with a simple Accumulator that measures string lengths.\n" +
                        "It then introduces a GenericAccumulator that can measure any object using:\n" +
                        "  - a measurer function (Function<T, Double>) to extract a numeric value, and\n" +
                        "  - a filter predicate (Predicate<T>) to include/exclude items.\n" +
                        "We demo this with IncidentReport, Integer, String, and FoodItem datasets.\n"
        );


        // Create an instance so we can call instance methods (demoBasicAccumulator / demoGenericAccumulator).
        // (We could make these static, but using an instance keeps the demo code consistent with OOP style.)
        Main theApp = new Main();

        // Demo 1: the original "basic" accumulator idea (strings -> length stats)
        theApp.demoBasicAccumulator();

        // Demo 2: generic accumulator using Function + Predicate for different domains
        theApp.demoGenericAccumulator();
    }

    /// <summary>
    /// Demonstrates the basic accumulator idea using two lists of strings.
    /// The <see cref="Accumulator"/> is updated with each string and reports statistics
    /// based on string length.
    /// </summary>
    /// <see cref="Accumulator"/>
    public void demoBasicAccumulator() {

        System.out.println("\n---------------- demoBasicAccumulator ----------------\n");

        // Make first list with little variety in string length.
        // (So the standard deviation should be low.)
        List<String> dataList1 = new ArrayList<>(List.of("Ana", "Ben", "Cia", "Dav", "Era"));

        // Accumulator measures string length internally and accumulates stats.
        Accumulator myStringAccumulator1 = new Accumulator();

        // Feed every string to the accumulator
        getStatistics(dataList1, myStringAccumulator1);

        // Print computed stats (min/max/mean/stdDev/count etc.)
        System.out.println(myStringAccumulator1);

        // Make second list to demonstrate a different average and larger variation.
        List<String> dataList2 = new ArrayList<>(List.of("ad", "fghgfhgfh", "sdfsd", "asddfgdfgfdgfdggsd", "sdffsd"));

        Accumulator myStringAccumulator2 = new Accumulator();
        getStatistics(dataList2, myStringAccumulator2);
        System.out.println(myStringAccumulator2);

        // TODO: Refactor Accumulator to support generics (GenericAccumulator already demonstrates this).
    }

    /// <summary>
    /// Calculates the average string length of the supplied list.
    /// This is separate from the accumulator and is useful for comparing approaches in class.
    /// </summary>
    /// <param name="data">A list of strings to analyse.</param>
    /// <returns>The average length of the strings in <paramref name="data"/>.</returns>
    public double getAverage(List<String> data) {

        // Defensive coding: reject null or empty input
        if (data == null || data.size() == 0)
            throw new IllegalArgumentException("data is null or zero length!");

        // Sum the lengths of every string
        double lengthSum = 0;
        for (String s : data)
            lengthSum += s.length();

        // Average length = total length / number of strings
        return lengthSum / data.size();
    }

    /// <summary>
    /// Updates an <see cref="Accumulator"/> with each string in the list.
    /// This acts as a small adapter between a collection and an accumulator object.
    /// </summary>
    /// <param name="data">The source list of strings.</param>
    /// <param name="accumulator">The accumulator to update.</param>
    /// <see cref="Accumulator"/>
    public void getStatistics(List<String> data, Accumulator accumulator) {

        // Feed each string into the accumulator.
        for (String s : data)
            accumulator.update(s);
    }

    /// <summary>
    /// Demonstrates the <see cref="GenericAccumulator"/> across multiple domains:
    /// - incident reports (measure severity, filter by zone + validity),
    /// - integers (measure value, filter positives),
    /// - strings (measure length, filter non-blank).
    /// </summary>
    /// <see cref="GenericAccumulator"/>
    private void demoGenericAccumulator() {

        System.out.println("\n---------------- demoGenericAccumulator ----------------\n");

        // Run three small sub-demos to see "same code, different data types".
        demoGenericAccumulatorIncidents();
        demoGenericAccumulatorIntegers();
        demoGenericAccumulatorStrings();
        demoGenericAccumulatorFoodItems();
    }

    /// <summary>
    /// Demonstrates generic accumulation over a list of integers.
    /// Measures the integer value and filters out non-positive values.
    /// </summary>
    /// <see cref="GenericAccumulator"/>
    private void demoGenericAccumulatorIntegers() {

        System.out.println("\n--- GenericAccumulator: Integer (positive values only) ---\n");

        // Sample data: positive, negative, and zero values.
        // This makes the filter predicate (n > 0) meaningful.
        List<Integer> tournamentScoreDeltas =
                new LinkedList<>(List.of(2, 4, 5, -6, 7, 8, -1, 2, 0, 5, 9, 0));

        // Accumulate stats:
        // - measurer: the numeric value itself
        // - filter: accept only positive values
        GenericAccumulator acc = accumulateList(
                tournamentScoreDeltas,
                n -> (double) n,
                n -> n != null && n > 0
        );

        // Print formatted stats
        System.out.println(acc.toPrettyString());
    }

    /// <summary>
    /// Demonstrates generic accumulation over a list of strings.
    /// Measures each string's length and filters out blank strings.
    /// </summary>
    /// <see cref="GenericAccumulator"/>
    private void demoGenericAccumulatorStrings() {

        System.out.println("\n--- GenericAccumulator: String (non-blank only, measuring length) ---\n");

        // Sample data includes empty strings and whitespace strings,
        // so the predicate (!isBlank) is meaningful.
        List<String> words = new ArrayList<>(
                List.of("Ana", "Ben", "", "Ciaran", "  ", "Dee", "Elizabeth", "", "Finn")
        );

        // Accumulate stats:
        // - measurer: string length
        // - filter: accept only non-blank strings
        GenericAccumulator acc = accumulateList(
                words,
                s -> (double) s.length(),
                s -> !s.isBlank()
        );

        // Print formatted stats
        System.out.println(acc.toPrettyString());
    }

    /// <summary>
    /// Demonstrates generic accumulation over a list of <see cref="IncidentReport"/>.
    /// Measures incident severity and filters to include only valid reports in the CHEM_STORE zone.
    /// </summary>
    /// <see cref="IncidentReport"/>
    /// <see cref="GenericAccumulator"/>
    private void demoGenericAccumulatorIncidents() {

        System.out.println("\n--- GenericAccumulator: IncidentReport (severity in CHEM_STORE) ---\n");

        List<IncidentReport> events = new ArrayList<>();

        // Base epoch time in *seconds* (we then add offsets: +60 = 1 minute, etc.)
        long t = 1700000000L;

        // Fields: id, timestampUtcSeconds, durationSeconds, severity(1..5), type, zoneType, notes
        events.add(new IncidentReport("IR-001", t + 0,    420, 1, IncidentType.SPILL,            ZoneType.CHEM_STORE,        "Small solvent spill; cleaned with kit"));
        events.add(new IncidentReport("IR-002", t + 60,    35, 2, IncidentType.NEAR_MISS,        ZoneType.PRODUCTION_LINE_1, "Forklift reversed; pedestrian stepped back"));
        events.add(new IncidentReport("IR-003", t + 120,  900, 4, IncidentType.MACHINE_FAULT,    ZoneType.PRODUCTION_LINE_2, "Conveyor jam; line stopped"));
        events.add(new IncidentReport("IR-004", t + 180,  110, 2, IncidentType.INTRUSION,        ZoneType.LOADING_BAY,       "Unauthorized entry attempt; challenged by staff"));
        events.add(new IncidentReport("IR-005", t + 240,   55, 1, IncidentType.EVACUATION_DRILL, ZoneType.OFFICE,            "Scheduled drill; all clear"));
        events.add(new IncidentReport("IR-006", t + 300,  180, 1, IncidentType.FIRE_ALARM,       ZoneType.CHEM_STORE,        "Smoke detector triggered; false alarm"));
        events.add(new IncidentReport("IR-007", t + 360,  240, 5, IncidentType.INJURY,           ZoneType.PRODUCTION_LINE_1, "Hand laceration; first aid applied; logged"));
        events.add(new IncidentReport("IR-008", t + 420, 1200, 4, IncidentType.POWER_OUTAGE,     ZoneType.WAREHOUSE_B,       "Partial outage; backup lighting engaged"));
        events.add(new IncidentReport("IR-009", t + 480,   15, 2, IncidentType.NEAR_MISS,        ZoneType.LOADING_BAY,       "Dropped pallet wrap; no contact; reported"));
        events.add(new IncidentReport("IR-010", t + 540,  660, 5, IncidentType.MACHINE_FAULT,    ZoneType.CHEM_STORE,        "Motor overheating; cool-down + inspection"));

        // Deliberately invalid records for filtering demonstrations:
        // - blank id
        // - negative duration
        events.add(new IncidentReport("",       t + 600,   90, 3, IncidentType.SPILL,            ZoneType.LOADING_BAY,       "Blank id (discard candidate)"));
        events.add(new IncidentReport("IR-012", t + 660,  -10, 2, IncidentType.INTRUSION,         ZoneType.WAREHOUSE_A,       "Negative duration (discard candidate)"));

        // Accumulate stats:
        // - measurer: severity (converted to double)
        // - filter: accept only valid reports and only those in CHEM_STORE
        GenericAccumulator acc = accumulateList(
                events,
                report -> (double) report.getSeverity(),
                report -> report != null &&
                        report.hasValidId() &&
                        report.hasValidSeverity() &&
                        report.getZoneType() == ZoneType.CHEM_STORE
        );

        // Print formatted stats
        System.out.println(acc.toPrettyString());
    }

    /// <summary>
    /// Demonstrates generic accumulation over a list of <see cref="FoodItem"/>.
    /// Shows how the same accumulator can measure different numeric properties
    /// (price, calories, sugar, protein) and can be filtered in different ways (category, thresholds, validity).
    /// </summary>
    /// <see cref="FoodItem"/>
    /// <see cref="GenericAccumulator"/>
    private void demoGenericAccumulatorFoodItems() {

        System.out.println("\n--- GenericAccumulator: FoodItem (calories demos) ---\n");

        // Build a small list of food items with enough variety to make filtering meaningful.
        List<FoodItem> foods = new ArrayList<>(List.of(
                new FoodItem("Chicken Wrap", FoodCategory.MEAL, 520, 5.50, 4, 32, "Lunch"),
                new FoodItem("Protein Yogurt", FoodCategory.SNACK, 180, 2.20, 9, 18, "High protein"),
                new FoodItem("Apple", FoodCategory.FRUIT, 95, 0.60, 19, 0, "Whole fruit"),
                new FoodItem("Cola Can", FoodCategory.DRINK, 139, 1.20, 35, 0, "Sugary drink"),
                new FoodItem("Sparkling Water", FoodCategory.DRINK, 5, 1.10, 0, 0, "No sugar"),
                new FoodItem("Brownie", FoodCategory.DESSERT, 380, 2.80, 28, 4, "Treat"),
                new FoodItem("Salad Bowl", FoodCategory.MEAL, 320, 6.20, 6, 14, "Light meal"),
                new FoodItem("Banana", FoodCategory.FRUIT, 105, 0.40, 14, 1, "Quick snack"),
                new FoodItem("Energy Bar", FoodCategory.SNACK, 250, 1.60, 12, 10, "On the go"),
                new FoodItem("", FoodCategory.SNACK, 200, 1.50, 10, 6, "Blank name (discard candidate)")
        ));

        // Calories for meals under €6 (a different kind of filter)
        // - measurer: calories
        // - filter: MEAL and price constraint
        GenericAccumulator mealCaloriesUnder6 = accumulateList(
                foods,
                f -> (double) f.getCalories(),
                f -> f != null &&
                        f.hasValidName());

        System.out.println("\nMeal calories (kcal) for meals under €6:");
        System.out.println(mealCaloriesUnder6.toPrettyString());
    }

    /// <summary>
    /// Accumulates statistics over a list of items using a measurer function and a filter predicate.
    /// This method demonstrates how <see cref="Function{T, R}"/> and <see cref="Predicate{T}"/> can be
    /// passed into a reusable algorithm to work across many different data types.
    /// </summary>
    /// <param name="list">The source list of items.</param>
    /// <param name="measurer">Extracts the numeric measurement to accumulate from each item.</param>
    /// <param name="filter">Determines whether an item should be included in the accumulation.</param>
    /// <typeparam name="T">The type of item contained in the list.</typeparam>
    /// <returns>A <see cref="GenericAccumulator"/> containing computed statistics.</returns>
    /// <see cref="GenericAccumulator"/>
    /// <see cref="Function{T, R}"/>
    /// <see cref="Predicate{T}"/>
    public <T> GenericAccumulator accumulateList(
            List<T> list,
            Function<T, Double> measurer,
            Predicate<T> filter
    ) {
        // Fail fast: these are required inputs.
        if (list == null)
            throw new IllegalArgumentException("list is null.");

        if (measurer == null)
            throw new IllegalArgumentException("measurer is null.");

        if (filter == null)
            throw new IllegalArgumentException("filter is null.");

        GenericAccumulator accumulator = new GenericAccumulator();

        // Apply the same accumulation algorithm to each item.
        for (T obj : list)
            accumulator.update(obj, measurer, filter);

        return accumulator;
    }
}
