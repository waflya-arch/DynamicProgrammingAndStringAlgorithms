import EditDistance.*;
import StringSearch.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class Assignment4Test {

    @Test
    void testEditDistanceReference() {
        assertEquals(3, Table.editDistance("kitten", "sitting"));
        assertEquals(2, Table.editDistance("flaw", "lawn"));
        assertEquals(5, Table.editDistance("intention", "execution"));
        assertEquals(3, Table.editDistance("", "abc"));
        assertEquals(0, Table.editDistance("abc", "abc"));
    }

    @Test
    void testEditDistance100Random() {
        Random rnd = new Random(42);
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < 100; i++) {
            String s1 = generateRandomString(rnd, alphabet, 8);
            String s2 = generateRandomString(rnd, alphabet, 8);

            int rec = Recursive.editDistance(s1, s2);
            int tbl = Table.editDistance(s1, s2);
            int two = TwoRows.editDistance(s1, s2);

            assertEquals(rec, tbl);
            assertEquals(tbl, two);
        }
    }

    @Test
    void testLPSReference() {
        assertArrayEquals(new int[]{0, 1, 2, 0, 1, 2, 3, 3}, KMP.lps("AAACAAAA"));
        assertArrayEquals(new int[]{0, 0, 1, 2, 0, 1, 2, 3, 4}, KMP.lps("ABABCABAB"));
    }

    @Test
    void testSearchReference() {
        assertEquals(List.of(10), KMP.search("ABABCABAB", "ABABDABACDABABCABAB"));
        assertEquals(List.of(0, 1, 2, 3), KMP.search("AA", "AAAAA"));
        assertEquals(List.of(0, 2, 4), KMP.search("ABA", "ABABABA"));
    }

    @Test
    void testSearch100Random() {
        Random rnd = new Random(42);
        String alphabet = "abc";

        for (int i = 0; i < 100; i++) {
            String txt = generateRandomString(rnd, alphabet, 30);
            String pat = generateRandomString(rnd, alphabet, 3);

            List<Integer> naiveRes = Naive.search(pat, txt);
            List<Integer> kmpRes = KMP.search(pat, txt);

            assertEquals(naiveRes, kmpRes);
        }
    }

    @Test
    void testEdgeCases() {
        assertThrows(IllegalArgumentException.class, () -> KMP.search("", "text"));
        assertThrows(IllegalArgumentException.class, () -> Naive.search("", "text"));
        assertTrue(KMP.search("verylongpattern", "short").isEmpty());
        assertTrue(Naive.search("verylongpattern", "short").isEmpty());
    }

    private String generateRandomString(Random rnd, String alphabet, int maxLength) {
        int len = rnd.nextInt(maxLength) + 1;
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(alphabet.charAt(rnd.nextInt(alphabet.length())));
        }
        return sb.toString();
    }
}