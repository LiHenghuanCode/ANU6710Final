package IO.IOPractice1;

import Exams.IO.IOPractice1.ReadEvenNumbers;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ReadEvenNumbersTest {

    /**
     * Helper: create a temporary file with the given text content.
     */
    private File createTempFile(String content) throws IOException {
        Path path = Files.createTempFile("read_even_numbers_test_", ".txt");
        Files.write(path, content.getBytes());
        return path.toFile();
    }

    @Test
    public void testBasicExample() throws IOException {
        String content = "5 2 8 1 4\n3 6 10";
        File file = createTempFile(content);

        List<Integer> result = ReadEvenNumbers.ReadEvenNumbersToFile(file);

        assertEquals(Arrays.asList(2, 4, 6, 8, 10), result);
    }

    @Test
    public void testEmptyFile() throws IOException {
        File file = createTempFile("");

        List<Integer> result = ReadEvenNumbers.ReadEvenNumbersToFile(file);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testNoEvenNumbers() throws IOException {
        String content = "1 3 5 7 9";
        File file = createTempFile(content);

        List<Integer> result = ReadEvenNumbers.ReadEvenNumbersToFile(file);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testNegativeNumbers() throws IOException {
        String content = "-3 -2 -1 0 4";
        File file = createTempFile(content);

        List<Integer> result = ReadEvenNumbers.ReadEvenNumbersToFile(file);

        assertEquals(Arrays.asList(-2, 0, 4), result);
    }

    @Test
    public void testMultipleLinesAndSpaces() throws IOException {
        String content = "   10   3  2\n4  8  \n   6  ";
        File file = createTempFile(content);

        List<Integer> result = ReadEvenNumbers.ReadEvenNumbersToFile(file);

        assertEquals(Arrays.asList(2, 4, 6, 8, 10), result);
    }

    @Test
    public void testWithInvalidTokens() throws IOException {
        String content = "5 two 8 3 a 4";
        File file = createTempFile(content);

        List<Integer> result = ReadEvenNumbers.ReadEvenNumbersToFile(file);

        assertEquals(Arrays.asList(4, 8), result);
    }

    @Test
    public void testAlreadySortedInput() throws IOException {
        String content = "2 4 6 8 10";
        File file = createTempFile(content);

        List<Integer> result = ReadEvenNumbers.ReadEvenNumbersToFile(file);

        assertEquals(Arrays.asList(2, 4, 6, 8, 10), result);
    }

    @Test
    public void testUnsortedInput() throws IOException {
        String content = "8 4 10 2 6";
        File file = createTempFile(content);

        List<Integer> result = ReadEvenNumbers.ReadEvenNumbersToFile(file);

        assertEquals(Arrays.asList(2, 4, 6, 8, 10), result);
    }
}
