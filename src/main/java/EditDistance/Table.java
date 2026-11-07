package EditDistance;

public class Table {
    public static long cellUpdates = 0;

    /*
     * State: dp[i][j] - min edit distance between prefix s1[0..i-1] and s2[0..j-1]
     * Transition: if s1[i-1] == s2[j-1] -> dp[i-1][j-1]
     *             else -> 1 + min(dp[i][j-1], dp[i-1][j], dp[i-1][j-1])
     * Base Case: dp[i][0] = i, dp[0][j] = j
     */
    public static int editDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        cellUpdates = 0;

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) dp[i][0] = i;
        for (int j = 0; j <= n; j++) dp[0][j] = j;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                cellUpdates++;
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}