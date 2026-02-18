package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {

    /// <summary>
    /// Entry point for the demo application. Creates a Main instance and runs
    /// two demonstrations: a basic string-length accumulator and a domain-based incident dataset.
    /// </summary>
    /// <see cref="Main"/>
    public static void main(String[] args) {

        // Create an instance so we can call instance methods (demoBasicAccumulator / demoGenericAccumulator)
        // (We could make these static, but using an instance keeps the demo code consistent with OOP style.)
        Main theApp = new Main();

        // Demo 1: the original "basic" accumulator idea (strings -> length stats)
        theApp.demoBasicAccumulator();

        // Demo 2: a domain-based dataset (IncidentReport) ready to be used with a generic accumulator
        theApp.demoGenericAccumulator();
    }

    /// <summary>
    /// Builds a small dataset of factory incident reports that can be processed by a generic
    /// accumulator using a measurement function and optional filter predicates.
    /// TODO
    /// </summary>
    /// <see cref="IncidentReport"/>
    private void demoGenericAccumulator() {

        System.out.println("\n---------------- demoGenericAccumulator ----------------\n");

        // Create a list of incident events (factory safety / operations log)
        List<IncidentReport> events = new ArrayList<>();

        // Base epoch time in *seconds* (we then add offsets: +60 = 1 minute, etc.)
        long t = 1700000000L;

        // Add 12 incident reports.
        // Fields: id, timestampUtcSeconds, durationSeconds, severity(1..5), type, zoneType, notes
        events.add(new IncidentReport("IR-001", t + 0,    420, 3, IncidentType.SPILL,            ZoneType.CHEM_STORE,        "Small solvent spill; cleaned with kit"));
        events.add(new IncidentReport("IR-002", t + 60,    35, 2, IncidentType.NEAR_MISS,        ZoneType.PRODUCTION_LINE_1, "Forklift reversed; pedestrian stepped back"));
        events.add(new IncidentReport("IR-003", t + 120,  900, 4, IncidentType.MACHINE_FAULT,    ZoneType.PRODUCTION_LINE_2, "Conveyor jam; line stopped"));
        events.add(new IncidentReport("IR-004", t + 180,  110, 2, IncidentType.INTRUSION,        ZoneType.LOADING_BAY,       "Unauthorized entry attempt; challenged by staff"));
        events.add(new IncidentReport("IR-005", t + 240,   55, 1, IncidentType.EVACUATION_DRILL, ZoneType.OFFICE,            "Scheduled drill; all clear"));
        events.add(new IncidentReport("IR-006", t + 300,  180, 3, IncidentType.FIRE_ALARM,       ZoneType.WAREHOUSE_A,       "Smoke detector triggered; burnt toast in canteen"));
        events.add(new IncidentReport("IR-007", t + 360,  240, 5, IncidentType.INJURY,           ZoneType.PRODUCTION_LINE_1, "Hand laceration; first aid applied; logged"));
        events.add(new IncidentReport("IR-008", t + 420, 1200, 4, IncidentType.POWER_OUTAGE,     ZoneType.WAREHOUSE_B,       "Partial outage; backup lighting engaged"));
        events.add(new IncidentReport("IR-009", t + 480,   15, 2, IncidentType.NEAR_MISS,        ZoneType.LOADING_BAY,       "Dropped pallet wrap; no contact; reported"));
        events.add(new IncidentReport("IR-010", t + 540,  660, 3, IncidentType.MACHINE_FAULT,    ZoneType.PRODUCTION_LINE_2, "Motor overheating; cool-down + inspection"));

        // These last two are "discard candidates" you can use to demonstrate filtering rules:
        // - blank id
        // - negative duration
        events.add(new IncidentReport("",       t + 600,   90, 3, IncidentType.SPILL,           ZoneType.CHEM_STORE,        "Blank id (discard candidate)"));
        events.add(new IncidentReport("IR-012", t + 660,  -10, 2, IncidentType.INTRUSION,        ZoneType.WAREHOUSE_A,       "Negative duration (discard candidate)"));

        // Next step for class:
        // - Use a generic Accumulator<IncidentReport> with a measure function, e.g. IncidentReport::getDurationSeconds
        // - Add a Predicate<IncidentReport> filter to discard invalid records (blank id / negative duration / drills, etc.)
    }

    /// <summary>
    /// Demonstrates the basic accumulator idea using two lists of strings.
    /// The accumulator is updated with each string and reports statistics (based on string length).
    /// </summary>
    /// <see cref="Accumulator"/>
    public void demoBasicAccumulator() {
        System.out.println("\n---------------- demoBasicAccumulator ----------------\n");
        // Make first list with no variety in string length
        // (So the standard deviation should be low / possibly zero depending on the values.)
        List<String> dataList1 = new ArrayList<>(List.of("Ana", "Ben", "Cia", "Dav", "Era"));

        // Accumulator here is assumed to measure each string (likely its length) and accumulate stats
        Accumulator myStringAccumulator1 = new Accumulator();

        // Feed every string to the accumulator
        getStatistics(dataList1, myStringAccumulator1);

        // Print computed stats (min/max/avg/std dev/count etc.)
        System.out.println(myStringAccumulator1);

        // Make second list to demonstrate difference in average and standard deviation
        // (This list has much more variation in string length.)
        List<String> dataList2 = new ArrayList<>(List.of("ad", "fghgfhgfh", "sdfsd", "asddfgdfgfdgfdggsd", "sdffsd"));

        Accumulator myStringAccumulator2 = new Accumulator();
        getStatistics(dataList2, myStringAccumulator2);
        System.out.println(myStringAccumulator2);

        //TODO - refactor Accumulator to support generics
    }

    /// <summary>
    /// Calculates the average string length of the supplied list.
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
}

