package StringSearch;

import java.util.ArrayList;

public class KMP {
    public static long comparisons = 0;

    public static int[] lps(String pat) {
        if (pat == null || pat.isEmpty()) {
            throw new IllegalArgumentException("Pattern cannot be empty");
        }
        int m = pat.length();
        int[] lpsArr = new int[m];
        int len = 0;
        int i = 1;

        while (i < m) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lpsArr[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lpsArr[len - 1];
                } else {
                    lpsArr[i] = 0;
                    i++;
                }
            }
        }
        return lpsArr;
    }

    public static ArrayList<Integer> search(String pat, String txt) {
        if (pat == null || pat.isEmpty()) {
            throw new IllegalArgumentException("Pattern cannot be empty");
        }
        ArrayList<Integer> res = new ArrayList<>();
        int n = txt.length();
        int m = pat.length();
        comparisons = 0;

        if (m > n) return res;

        int[] lpsArr = lps(pat);
        int i = 0;
        int j = 0;

        while (i < n) {
            comparisons++;
            if (txt.charAt(i) == pat.charAt(j)) {
                i++;
                j++;
                if (j == m) {
                    res.add(i - j);
                    j = lpsArr[j - 1];
                }
            } else {
                if (j != 0) {
                    j = lpsArr[j - 1];
                } else {
                    i++;
                }
            }
        }
        return res;
    }
}