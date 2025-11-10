package Hash.BasicPractices.countLettersV2;


import Exams.hash.BasicPractices;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class countLettersV2Test {

    BasicPractices bp = new BasicPractices();

    /**
     * "abracadabra" with {"a","b","r","z"}
     * → ["a=5", "b=2", "r=2", "z=0"]
     */
    @Test
    public void countLettersV2Test1() {
        String s = "abracadabra";
        String[] letters = {"a", "b", "r", "z"};
        List<String> expected = Arrays.asList("a=5", "b=2", "r=2", "z=0");
        assertEquals(expected, bp.countLettersV2(s, letters));
    }

    /**
     * Empty string "" → all counts should be 0
     */
    @Test
    public void countLettersV2Test2() {
        String s = "";
        String[] letters = {"a", "b", "r"};
        List<String> expected = Arrays.asList("a=0", "b=0", "r=0");
        assertEquals(expected, bp.countLettersV2(s, letters));
    }

    /**
     * String with no matching letters
     */
    @Test
    public void countLettersV2Test3() {
        String s = "xyz";
        String[] letters = {"a", "b"};
        List<String> expected = Arrays.asList("a=0", "b=0");
        assertEquals(expected, bp.countLettersV2(s, letters));
    }

    /**
     * Case-insensitive check (if implemented)
     * "AaBbCc" → ["a=2", "b=2", "c=2"]
     */
    @Test
    public void countLettersV2Test4() {
        String s = "AaBbCc";
        String[] letters = {"a", "b", "c"};
        List<String> expected = Arrays.asList("a=2", "b=2", "c=2");
        assertEquals(expected, bp.countLettersV2(s, letters));
    }

    /**
     * String with symbols and digits ignored
     * "a1!a@b#c" → ["a=2", "b=1", "c=1"]
     */
    @Test
    public void countLettersV2Test5() {
        String s = "a1!a@b#c";
        String[] letters = {"a", "b", "c"};
        List<String> expected = Arrays.asList("a=2", "b=1", "c=1");
        assertEquals(expected, bp.countLettersV2(s, letters));
    }
}

