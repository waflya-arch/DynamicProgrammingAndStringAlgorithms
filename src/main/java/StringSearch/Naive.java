package StringSearch;

import java.util.ArrayList;

public class Naive {
    public static long comparisons = 0;

    public static ArrayList<Integer> search(String pat, String txt) {
        if (pat == null || pat.isEmpty()) {
            throw new IllegalArgumentException("Pattern cannot be empty");
        }
        ArrayList<Integer> ans = new ArrayList<>();
        int m = pat.length();
        int n = txt.length();
        comparisons = 0;

        if (m > n) return ans;

        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                comparisons++;
                if (txt.charAt(i + j) != pat.charAt(j)) {
                    break;
                }
            }
            if (j == m) {
                ans.add(i);
            }
        }
        return ans;
    }
}