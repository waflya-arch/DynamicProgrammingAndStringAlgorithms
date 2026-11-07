package EditDistance;

public class Recursive {
    public static long callsCount = 0;

    private static int editDistRec(String s1, String s2, int m, int n) {
        callsCount++;
        if (m == 0) return n;
        if (n == 0) return m;

        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            return editDistRec(s1, s2, m - 1, n - 1);
        }

        return 1 + Math.min(
                editDistRec(s1, s2, m, n - 1),
                Math.min(
                        editDistRec(s1, s2, m - 1, n),
                        editDistRec(s1, s2, m - 1, n - 1)
                )
        );
    }

    public static int editDistance(String s1, String s2) {
        callsCount = 0;
        return editDistRec(s1, s2, s1.length(), s2.length());
    }
}