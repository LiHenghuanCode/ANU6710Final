package List.ListPractice1;

import Exams.List.ListPractice1;
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

public class ListPractice1Test {

    /**
     * [[1,2,3], [5,1], [10]] -> [3,6, 5,6, 10,10]
     */
    @Test
    public void testBasicExample() {
        List<List<Integer>> input = new ArrayList<>();
        input.add(Arrays.asList(1, 2, 3));
        input.add(Arrays.asList(5, 1));
        input.add(Arrays.asList(10));

        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(3, 6, 5, 6, 10, 10));
        assertEquals(expected, ListPractice1.analyzeNumbers(input));
    }

    /**
     * Includes null and empty sublists → skip them
     */
    @Test
    public void testWithNullAndEmptySublists() {
        List<List<Integer>> input = new ArrayList<>();
        input.add(Arrays.asList(2, 2, 2)); // valid
        input.add(null);                   // skip
        input.add(new ArrayList<>());      // skip
        input.add(Arrays.asList(1, 9));    // valid

        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(2, 6, 9, 10));
        assertEquals(expected, ListPractice1.analyzeNumbers(input));
    }

    /**
     * Negative numbers
     * [[-1,-2,-3]] → sum=-6, max=-1, -1 > -6 → [sum,max] = [-6,-1]
     */
    @Test
    public void testNegativeNumbers() {
        List<List<Integer>> input = new ArrayList<>();
        input.add(Arrays.asList(-1, -2, -3));

        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(-6, -1));
        assertEquals(expected, ListPractice1.analyzeNumbers(input));
    }

    /**
     * Single element per sublist
     * [[4],[7],[0]] → each [x,x]
     */
    @Test
    public void testSingleElementSublists() {
        List<List<Integer>> input = Arrays.asList(
                Arrays.asList(4),
                Arrays.asList(7),
                Arrays.asList(0)
        );

        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(4, 4, 7, 7, 0, 0));
        assertEquals(expected, ListPractice1.analyzeNumbers(input));
    }

    /**
     * Entire input null → return null or empty list
     */
    @Test
    public void testNullInput() {
        assertNull(ListPractice1.analyzeNumbers(null));
    }

    /**
     * Empty input list → return empty list
     */
    @Test
    public void testEmptyInput() {
        List<List<Integer>> input = new ArrayList<>();
        ArrayList<Integer> expected = new ArrayList<>();
        assertEquals(expected, ListPractice1.analyzeNumbers(input));
    }

    /**
     * Mixed order check — correctness per sublist, not global sorting
     */
    @Test
    public void testMixedNumbers() {
        List<List<Integer>> input = Arrays.asList(
                Arrays.asList(3, 3, 3), // sum=9, max=3 → [3,9]
                Arrays.asList(10, 1, 2), // sum=13, max=10 → [10,13]
                Arrays.asList(0, 0, 0)   // sum=0, max=0 → [0,0]
        );

        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(3, 9, 10, 13, 0, 0));
        assertEquals(expected, ListPractice1.analyzeNumbers(input));
    }
}
