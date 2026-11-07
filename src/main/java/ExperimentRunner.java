import EditDistance.*;
import StringSearch.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class ExperimentRunner {
    private static final Random rnd = new Random(42);

    public static void main(String[] args) throws IOException {
        java.io.File resultsDir = new java.io.File("results");
        if (!resultsDir.exists()) resultsDir.mkdirs();

        PrintWriter writer = new PrintWriter(new FileWriter("results/results.csv"));
        writer.println("algorithm,input_type,n,m,time_ms,counter");

        runEditDistanceExperiments(writer);
        runStringSearchExperiments(writer);

        writer.close();
        System.out.println("Experiment complete. Data saved to results/results.csv");
    }

    private static void runEditDistanceExperiments(PrintWriter writer) {
        int[] recSizes = {4, 8, 12, 14};
        int[] dpSizes = {100, 500, 2000, 5000};
        String alpha = "abcdefghijklmnopqrstuvwxyz";

        // 1. Random Inputs
        for (int n : recSizes) {
            String s1 = genString(n, alpha);
            String s2 = genString(n, alpha);
            long[] res = measure(() -> Recursive.editDistance(s1, s2));
            writer.printf("Recursive,random,%d,%d,%.4f,%d\n", n, n, res[0] / 1e6, Recursive.callsCount);
        }

        for (int n : dpSizes) {
            String s1 = genString(n, alpha);
            String s2 = genString(n, alpha);

            long[] tRes = measure(() -> Table.editDistance(s1, s2));
            writer.printf("Table,random,%d,%d,%.4f,%d\n", n, n, tRes[0] / 1e6, Table.cellUpdates);

            long[] rRes = measure(() -> TwoRows.editDistance(s1, s2));
            writer.printf("TwoRows,random,%d,%d,%.4f,%d\n", n, n, rRes[0] / 1e6, TwoRows.cellUpdates);
        }

        // 2. Worst-Case Inputs (Completely different strings)
        for (int n : dpSizes) {
            String s1 = "A".repeat(n);
            String s2 = "B".repeat(n);

            long[] tRes = measure(() -> Table.editDistance(s1, s2));
            writer.printf("Table,worst_case,%d,%d,%.4f,%d\n", n, n, tRes[0] / 1e6, Table.cellUpdates);

            long[] rRes = measure(() -> TwoRows.editDistance(s1, s2));
            writer.printf("TwoRows,worst_case,%d,%d,%.4f,%d\n", n, n, rRes[0] / 1e6, TwoRows.cellUpdates);
        }
    }

    private static void runStringSearchExperiments(PrintWriter writer) {
        int[] textSizes = {1000, 5000, 20000, 100000};

        for (int n : textSizes) {
            int m = n / 10;
            String txtRandom = genString(n, "abcd");
            String patRandom = genString(m, "abcd");

            long[] nRand = measure(() -> Naive.search(patRandom, txtRandom));
            writer.printf("Naive,random,%d,%d,%.4f,%d\n", n, m, nRand[0] / 1e6, Naive.comparisons);

            long[] kRand = measure(() -> KMP.search(patRandom, txtRandom));
            writer.printf("KMP,random,%d,%d,%.4f,%d\n", n, m, kRand[0] / 1e6, KMP.comparisons);

            String txtWorst = "A".repeat(n);
            String patWorst = "A".repeat(m - 1) + "B";

            long[] nWorst = measure(() -> Naive.search(patWorst, txtWorst));
            writer.printf("Naive,worst_case,%d,%d,%.4f,%d\n", n, m, nWorst[0] / 1e6, Naive.comparisons);

            long[] kWorst = measure(() -> KMP.search(patWorst, txtWorst));
            writer.printf("KMP,worst_case,%d,%d,%.4f,%d\n", n, m, kWorst[0] / 1e6, KMP.comparisons);
        }
    }

    private static long[] measure(Runnable task) {
        int runs = 5;
        long[] times = new long[runs];
        for (int i = 0; i < runs; i++) {
            long start = System.nanoTime();
            task.run();
            times[i] = System.nanoTime() - start;
        }
        Arrays.sort(times);
        return new long[]{times[runs / 2]};
    }

    private static String genString(int len, String alpha) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(alpha.charAt(rnd.nextInt(alpha.length())));
        }
        return sb.toString();
    }
}