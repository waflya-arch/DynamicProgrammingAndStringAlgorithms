package EditDistance;

public class TwoRows {
    public static long cellUpdates = 0;

    public static int editDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        cellUpdates = 0;

        int[] curr = new int[n + 1];
        for (int j = 0; j <= n; j++) curr[j] = j;

        for (int i = 1; i <= m; i++) {
            int prev = curr[0];
            curr[0] = i;
            for (int j = 1; j <= n; j++) {
                cellUpdates++;
                int temp = curr[j];
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    curr[j] = prev;
                } else {
                    curr[j] = 1 + Math.min(Math.min(curr[j - 1], prev), curr[j]);
                }
                prev = temp;
            }
        }
        return curr[n];
    }
}