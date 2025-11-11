package HashCollection.AdvancedPractices.checkIfEqual;

import Exams.HashCollection.AdvancedPractices;
import org.junit.Test;
import static org.junit.Assert.*;

public class checkIfEqualNotIgnoreCaseTest {

    AdvancedPractices ap = new AdvancedPractices();

    /**
     * "Apple" vs "pPlea" → not equal when case-sensitive (A vs a)
     */
    @Test
    public void testCaseSensitiveNull() {
        assertTrue(ap.checkIfEqualNotIgnoreCase(null, null));
    }
    @Test
    public void testCaseSensitiveEmpty() {
        assertTrue(ap.checkIfEqualNotIgnoreCase(null, "null"));
    }

    /**
     * "Apple" vs "pPlea" → not equal when case-sensitive (A vs a)
     */
    @Test
    public void testCaseSensitive1() {
        assertFalse(ap.checkIfEqualNotIgnoreCase("Apple", "pPlea"));
    }

    /**
     * "Hello" vs "Hello" → exactly same
     */
    @Test
    public void testCaseSensitive2() {
        assertTrue(ap.checkIfEqualNotIgnoreCase("Hello", "Hello"));
    }

    /**
     * "abc" vs "Abc" → not equal (a vs A)
     */
    @Test
    public void testCaseSensitive3() {
        assertFalse(ap.checkIfEqualNotIgnoreCase("abc", "Abc"));
    }

    /**
     * "XYZ" vs "XYZ" → equal
     */
    @Test
    public void testCaseSensitive4() {
        assertTrue(ap.checkIfEqualNotIgnoreCase("XYZ", "XYZ"));
    }

    /**
     * "test" vs "tesT" → not equal (t vs T)
     */
    @Test
    public void testCaseSensitive5() {
        assertFalse(ap.checkIfEqualNotIgnoreCase("test", "tesT"));
    }
}
