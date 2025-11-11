package HashCollection.BasicPractices.countLetters;

import Exams.HashCollection.BasicPractices;
import org.junit.Test;
import static org.junit.Assert.*;

public class countLettersTest {

    BasicPractices bp = new BasicPractices();

    /**
     * "abracadabra" with {"a","b","r","z"}
     * → a=5, b=2, r=2, z=0
     */
    @Test
    public void countLettersTest1() {
        String s = "abracadabra";
        String[] letters = {"a", "b", "r", "z"};
        int[] expected = {5, 2, 2, 0};
        assertArrayEquals(expected, bp.countLetters(s, letters));
    }

    /**
     * Empty string "" → all counts should be 0
     */
    @Test
    public void countLettersTest2() {
        String s = "";
        String[] letters = {"a", "b", "r"};
        int[] expected = {0, 0, 0};
        assertArrayEquals(expected, bp.countLetters(s, letters));
    }

    /**
     * String without any matching letters
     */
    @Test
    public void countLettersTest3() {
        String s = "xyz";
        String[] letters = {"a", "b", "c"};
        int[] expected = {0, 0, 0};
        assertArrayEquals(expected, bp.countLetters(s, letters));
    }

    /**
     * Case-insensitive check (if implemented that way)
     * "AaBbCc" → a=2, b=2, c=2
     */
    @Test
    public void countLettersTest4() {
        String s = "AaBbCc";
        String[] letters = {"a", "b", "c"};
        int[] expected = {2, 2, 2};
        assertArrayEquals(expected, bp.countLetters(s, letters));
    }

    /**
     * Non-letter characters should be ignored
     * "a1!a@b#c" → a=2, b=1, c=1
     */
    @Test
    public void countLettersTest5() {
        String s = "a1!a@b#c";
        String[] letters = {"a", "b", "c"};
        int[] expected = {2, 1, 1};
        assertArrayEquals(expected, bp.countLetters(s, letters));
    }
}

