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

        // Проверка совпадения всех трех алгоритмов
        assertEquals(Recursive.editDistance("kitten", "sitting"), Table.editDistance("kitten", "sitting"));
        assertEquals(TwoRows.editDistance("kitten", "sitting"), Table.editDistance("kitten", "sitting"));
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
    void testEdgeCases() {
        assertThrows(IllegalArgumentException.class, () -> KMP.search("", "text"));
        assertThrows(IllegalArgumentException.class, () -> Naive.search("", "text"));
        assertTrue(KMP.search("verylongpattern", "short").isEmpty());
        assertTrue(Naive.search("verylongpattern", "short").isEmpty());
    }
}