package HashCollection.AnotherPractices;


import Exams.HashCollection.AnotherPractice1;
import org.junit.Test;
import static org.junit.Assert.*;

public class AnotherPractice1Test {

    /**
     * "aaa" -> false (repeated 'a')
     */
    @Test
    public void testAllSameLetters() {
        assertFalse(AnotherPractice1.isIsogram("aaa"));
    }

    /**
     * "abc" -> true (no repetition)
     */
    @Test
    public void testAllUniqueLetters() {
        assertTrue(AnotherPractice1.isIsogram("abc"));
    }

    /**
     * "Abc" -> true (case-insensitive check)
     */
    @Test
    public void testCaseInsensitive() {
        assertTrue(AnotherPractice1.isIsogram("Abc"));
    }

    /**
     * "ABBA" -> false (A and B repeat)
     */
    @Test
    public void testWithRepeatsDifferentCase() {
        assertFalse(AnotherPractice1.isIsogram("ABBA"));
    }

    /**
     * "" -> true (empty string counts as isogram)
     */
    @Test
    public void testEmptyString() {
        assertTrue(AnotherPractice1.isIsogram(""));
    }

    /**
     * null -> true (null treated as isogram)
     */
    @Test
    public void testNullInput() {
        assertTrue(AnotherPractice1.isIsogram(null));
    }

    /**
     * "Programming" -> false ('g' and 'r' repeat)
     */
    @Test
    public void testComplexWord() {
        assertFalse(AnotherPractice1.isIsogram("Programming"));
    }

    /**
     * "Python" -> true (no repeating letters)
     */
    @Test
    public void testTypicalWord() {
        assertTrue(AnotherPractice1.isIsogram("Python"));
    }

    /**
     * "isogram" -> true
     */
    @Test
    public void testWordItself() {
        assertTrue(AnotherPractice1.isIsogram("isogram"));
    }
}
