# City Search & Spell-Check: Dynamic Programming and String Matching

This repository contains the implementation and experimental evaluation of Edit Distance (Dynamic Programming) and String Search (Naive & KMP) algorithms, built for Assignment 4: Dynamic Programming and String Algorithms.

---

## Project Structure

```text
.
├── pom.xml
├── README.md
├── REPORT.md
├── generate_plots.py
├── results/
│   ├── results.csv
│   └── plots/
│       ├── edit_distance_time.png
│       ├── search_comparisons.png
│       └── table_vs_tworows.png
└── src/
    ├── main/
    │   └── java/
    │       ├── EditDistance/
    │       │   ├── Recursive.java
    │       │   ├── Table.java
    │       │   └── TwoRows.java
    │       ├── StringSearch/
    │       │   ├── KMP.java
    │       │   └── Naive.java
    │       └── ExperimentRunner.java
    └── test/
        └── java/
            └── Assignment4Test.java
```

---

## Prerequisites

* **Java Development Kit (JDK):** Version 17 or higher
* **Apache Maven:** Version 3.8+
* **Python:** Version 3.8+ (with `pandas` and `matplotlib` packages for plot generation)

---

## Build & Run Instructions

### 1. Build and Run Unit Tests
To compile the source code and execute all JUnit 5 unit tests (including reference check values, edge cases, and 100-random test verifications):

```bash
mvn clean test
```

### 2. Run Experiments & Generate CSV Data
To execute the benchmark experiments (runs 5 trials per configuration and records median runtime and operation counters):

```bash
mvn exec:java -Dexec.mainClass="ExperimentRunner"
```

This command automatically generates/updates `results/results.csv`.

### 3. Generate Benchmark Plots
To generate the 3 required visualization charts from `results/results.csv`:

```bash
python generate_plots.py
```

Generated charts will be placed in `results/plots/`.

---

## AI Use Acknowledgement

In accordance with course guidelines:
* **AI Assistance (Gemini):** Used strictly as a conversational Q&A assistant for clarifying requirements, understanding theoretical concepts, and asking questions regarding project setup.