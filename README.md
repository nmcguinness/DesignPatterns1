# DesignPatterns1 — Accumulator + Functional Interfaces Demo

This repo contains a small example that starts with a **basic accumulator** and evolves into a **generic, reusable accumulator** using Java **functional interfaces** (`Function` + `Predicate`).

The goal is to show students how a single algorithm can become reusable across multiple domains by *injecting behaviour* (measurement + filtering) instead of hard-coding logic.

---

## What this project demonstrates

### 1) The “Accumulator” idea (domain-specific)
In `org.example.core.Accumulator` we implement a simple accumulator that processes **Strings** and accumulates statistics based on **string length**:

- min
- max
- mean
- count
- standard deviation

This is a concrete, easy-to-grasp starting point.

**Key teaching point:** the algorithm works, but it is *locked* to one data type and one measurement (string length).

---

### 2) Making it reusable with a GenericAccumulator (behaviour injection)
In `org.example.core.GenericAccumulator` we generalise the algorithm:

- We accept any object `T`
- We ask the caller for:
  - a **measurer**: `Function<T, Double>` (how to get a number from `T`)
  - a **filter**: `Predicate<T>` (which items to include/exclude)

This is effectively the **Strategy Pattern**, implemented using lambdas instead of concrete strategy classes.

**Key teaching points:**
- *Same accumulator, different domains* (code reuse)
- *Algorithms + behaviour injection* (Strategy via functional interfaces)
- *Filtering and measuring are independent concerns*

---

### 3) Reusing the same logic across multiple datasets
`org.example.Main` demonstrates the same accumulator algorithm on different data:

- **IncidentReport** (`org.example.incidents`)
  - measure: severity (or duration, etc.)
  - filter: valid items + specific zone (`ZoneType.CHEM_STORE`)
- **Integer** list
  - measure: numeric value
  - filter: only positives
- **String** list
  - measure: length
  - filter: non-blank
- **FoodItem** (`org.example.food`)
  - measure: calories / sugar / price / protein
  - filter: category + validity rules

**Key teaching point:** students see that “generic” doesn’t mean abstract-and-pointless — it means *reusable in multiple realistic mini-domains*.

---

## Packages / Key Classes

### `org.example`
- `Main` — runs the demos and prints results

### `org.example.core`
- `Accumulator` — basic string-length accumulator
- `GenericAccumulator` — reusable accumulator via `Function` + `Predicate`

### `org.example.incidents`
- `IncidentReport` — simple domain class with validation helpers
- `IncidentType`, `ZoneType` — enums used for filtering and classification

### `org.example.food`
- `FoodItem`, `FoodCategory` — second domain used to show reuse and filtering

### `org.example.utilities`
- `MathHelper` — rounding helper (used for consistent output formatting)

---

## Design / OOP ideas students should notice

### Strategy Pattern (without the ceremony)
Instead of writing:

- `SeverityMeasurer implements Measurer<IncidentReport>`
- `IsChemStore implements Filter<IncidentReport>`

…we can use:

- `report -> (double) report.getSeverity()`
- `report -> report.getZoneType() == ZoneType.CHEM_STORE`

This is a lightweight, readable way to introduce “strategy” before introducing full pattern catalogues.

---

### Separation of concerns
The accumulator’s job is only:

- accept numeric measurements
- update running statistics

It does *not* decide:
- what to measure
- what to include

Those responsibilities are supplied by callers.

---

### Defensive coding
The demo includes “discard candidates” (e.g., blank IDs / invalid values) so filtering rules are meaningful.
The `accumulateList(...)` method checks inputs for null and fails fast with `IllegalArgumentException`.

---

## Running the demo

### Option A (recommended): IntelliJ / IDE
1. Open the repo as a Maven project
2. Run:
   - `src/main/java/org/example/Main.java`

### Option B: Command line (if you have Maven + JDK installed)
If your environment is set up to run the project from Maven, run the `Main` class from your IDE.
(If you add the Maven Exec plugin later, you can run via `mvn exec:java`.)

---

## Suggested “next steps” for students (good lab extensions)

1. Add new measures for `IncidentReport`:
   - duration stats
   - “severity x duration” as a risk score

2. Add more filters:
   - severity >= 3
   - only INJURY or MACHINE_FAULT types
   - only valid duration and valid id

3. Improve the numeric API:
   - replace `Function<T, Double>` with `ToDoubleFunction<T>` to avoid boxing

4. Add a `combineFilters(...)` helper:
   - demonstrate predicate composition using `and()`, `or()`, `negate()`

5. Add CSV loading for a domain list (then feed into `GenericAccumulator`).

---

## Why this repo exists
This is deliberately small and readable. It is meant to be a “teaching seed” that supports:
- generics
- functional interfaces
- defensive coding
- separation of concerns
- introducing design patterns gradually (Strategy via lambdas)

