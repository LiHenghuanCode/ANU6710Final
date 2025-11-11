package HashCollection.AdvancedPractices.checkIfEqual;


import Exams.HashCollection.AdvancedPractices;
import org.junit.Test;
import static org.junit.Assert.*;

public class checkIfEqualIgnoreCaseTest {

    AdvancedPractices ap = new AdvancedPractices();

    /**
     * "Apple" vs "pPlea" → same letters ignoring case
     */
    @Test
    public void testEqualLettersIgnoreCase1() {
        assertTrue(ap.checkIfEqualIgnoreCase("Apple", "pPlea"));
    }

    /**
     * "hello" vs "heLLo!" → same letters ignoring case
     */
    @Test
    public void testEqualLettersIgnoreCase2() {
        assertTrue(ap.checkIfEqualIgnoreCase("hello", "heLLo!"));
    }

    /**
     * "abc" vs "abcc" → different counts
     */
    @Test
    public void testNotEqualLettersIgnoreCase3() {
        assertFalse(ap.checkIfEqualIgnoreCase("abc", "abcc"));
    }

    /**
     * "Aa" vs "aA" → same ignoring case
     */
    @Test
    public void testEqualLettersIgnoreCase4() {
        assertTrue(ap.checkIfEqualIgnoreCase("Aa", "aA"));
    }

    /**
     * "Java" vs "Javb" → different letters
     */
    @Test
    public void testNotEqualLettersIgnoreCase5() {
        assertFalse(ap.checkIfEqualIgnoreCase("Java", "Javb"));
    }
}

