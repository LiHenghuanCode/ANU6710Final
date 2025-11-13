package IO.IOPractice2;

import Exams.IO.IOPractice2.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for complex file system scenarios
 */
public class FsNodeIntegrationTests {

    @TempDir
    Path tempDir;

    // ========== Complex Directory Structure Tests ==========

    @Test
    void testComplexDirectoryStructure() throws IOException {
        // Create structure:
        // root/
        // ├── a/
        // │   ├── x.txt
        // │   └── y.doc
        // ├── b/
        // │   └── z.txt
        // └── c.txt

        File root = tempDir.resolve("root").toFile();
        root.mkdir();

        File dirA = new File(root, "a");
        File dirB = new File(root, "b");
        dirA.mkdir();
        dirB.mkdir();

        File fileX = new File(dirA, "x.txt");
        File fileY = new File(dirA, "y.doc");
        File fileZ = new File(dirB, "z.txt");
        File fileC = new File(root, "c.txt");

        Files.writeString(fileX.toPath(), "x content");
        Files.writeString(fileY.toPath(), "y content");
        Files.writeString(fileZ.toPath(), "z content");
        Files.writeString(fileC.toPath(), "c content");

        FsNode rootNode = FsNode.createNode(root);
        List<FsNode> nodes = rootNode.allNodes();

        // Expected order: root, a, x.txt, y.doc, b, z.txt, c.txt
        assertEquals(7, nodes.size());

        assertEquals("root", nodes.get(0).getUnderlyingFile().getName());
        assertEquals("a", nodes.get(1).getUnderlyingFile().getName());
        assertEquals("x.txt", nodes.get(2).getUnderlyingFile().getName());
        assertEquals("y.doc", nodes.get(3).getUnderlyingFile().getName());
        assertEquals("b", nodes.get(4).getUnderlyingFile().getName());
        assertEquals("z.txt", nodes.get(5).getUnderlyingFile().getName());
        assertEquals("c.txt", nodes.get(6).getUnderlyingFile().getName());
    }

    @Test
    void testMixedFileTypes() throws IOException {
        // Test directory with various file extensions
        File dir = tempDir.resolve("mixed").toFile();
        dir.mkdir();

        File txtFile = new File(dir, "document.txt");
        File docFile = new File(dir, "document.doc");
        File pdfFile = new File(dir, "document.pdf");
        File jpgFile = new File(dir, "image.jpg");
        File noExtFile = new File(dir, "README");

        Files.writeString(txtFile.toPath(), "text");
        Files.writeString(docFile.toPath(), "doc");
        Files.writeString(pdfFile.toPath(), "pdf");
        Files.writeString(jpgFile.toPath(), "jpg");
        Files.writeString(noExtFile.toPath(), "readme");

        FsNode dirNode = FsNode.createNode(dir);
        List<FsNode> nodes = dirNode.allNodes();

        // All files except .txt should be FsNonTextFile
        assertEquals(6, nodes.size());
        assertTrue(nodes.get(0) instanceof FsDir); // mixed dir
        assertTrue(nodes.get(1) instanceof FsNonTextFile); // README
        assertTrue(nodes.get(2) instanceof FsNonTextFile); // document.doc
        assertTrue(nodes.get(3) instanceof FsNonTextFile); // document.pdf
        assertTrue(nodes.get(4) instanceof FsTextFile); // document.txt
        assertTrue(nodes.get(5) instanceof FsNonTextFile); // image.jpg
    }

    @Test
    void testDirectoryWithOnlySubdirectories() throws IOException {
        // Create directory with only subdirectories, no files
        File root = tempDir.resolve("onlydirs").toFile();
        File sub1 = tempDir.resolve("onlydirs/sub1").toFile();
        File sub2 = tempDir.resolve("onlydirs/sub2").toFile();
        File sub3 = tempDir.resolve("onlydirs/sub3").toFile();

        sub1.mkdirs();
        sub2.mkdirs();
        sub3.mkdirs();

        FsNode rootNode = FsNode.createNode(root);
        List<FsNode> nodes = rootNode.allNodes();

        assertEquals(4, nodes.size());
        assertTrue(nodes.stream().allMatch(n -> n instanceof FsDir));
    }

    @Test
    void testDirectoryWithOnlyFiles() throws IOException {
        // Create directory with only files, no subdirectories
        File dir = tempDir.resolve("onlyfiles").toFile();
        dir.mkdir();

        Files.writeString(new File(dir, "a.txt").toPath(), "a");
        Files.writeString(new File(dir, "b.txt").toPath(), "b");
        Files.writeString(new File(dir, "c.doc").toPath(), "c");

        FsNode dirNode = FsNode.createNode(dir);
        List<FsNode> nodes = dirNode.allNodes();

        assertEquals(4, nodes.size());
        assertTrue(nodes.get(0) instanceof FsDir);
        assertTrue(nodes.get(1) instanceof FsTextFile);
        assertTrue(nodes.get(2) instanceof FsTextFile);
        assertTrue(nodes.get(3) instanceof FsNonTextFile);
    }

    // ========== Equality Tests for Complex Structures ==========

    @Test
    void testDirectoryEquality_ComplexIdenticalStructure() throws IOException {
        // Create two identical complex directory structures
        File dir1 = createComplexStructure("struct1");
        File dir2 = createComplexStructure("struct2");

        // Rename both to have the same name
        File renamed1 = new File(dir1.getParentFile(), "same");
        File renamed2 = new File(dir2.getParentFile(), "same");
        dir1.renameTo(renamed1);
        dir2.renameTo(renamed2);

        FsDir fsDir1 = new FsDir(renamed1);
        FsDir fsDir2 = new FsDir(renamed2);

        assertEquals(fsDir1, fsDir2, "Identical complex structures should be equal");
    }

    @Test
    void testDirectoryEquality_DifferentSubdirectoryContent() throws IOException {
        // Create two directories with same structure but different file content
        File dir1 = tempDir.resolve("dir1/parent").toFile();
        File dir2 = tempDir.resolve("dir2/parent").toFile();
        dir1.mkdirs();
        dir2.mkdirs();

        File sub1 = new File(dir1, "sub");
        File sub2 = new File(dir2, "sub");
        sub1.mkdir();
        sub2.mkdir();

        File file1 = new File(sub1, "test.txt");
        File file2 = new File(sub2, "test.txt");
        Files.writeString(file1.toPath(), "content1");
        Files.writeString(file2.toPath(), "content2");

        FsDir fsDir1 = new FsDir(dir1);
        FsDir fsDir2 = new FsDir(dir2);

        assertNotEquals(fsDir1, fsDir2, "Directories with different file contents should not be equal");
    }

    // ========== Performance and Edge Cases ==========

    @Test
    void testLargeNumberOfFiles() throws IOException {
        // Test with many files in a directory
        File dir = tempDir.resolve("manyfiles").toFile();
        dir.mkdir();

        for (int i = 0; i < 100; i++) {
            String filename = String.format("file%03d.txt", i);
            File file = new File(dir, filename);
            Files.writeString(file.toPath(), "content " + i);
        }

        FsNode dirNode = FsNode.createNode(dir);
        List<FsNode> nodes = dirNode.allNodes();

        assertEquals(101, nodes.size()); // 1 dir + 100 files

        // Verify alphabetical ordering
        for (int i = 1; i < 100; i++) {
            String prev = nodes.get(i).getUnderlyingFile().getName();
            String next = nodes.get(i + 1).getUnderlyingFile().getName();
            assertTrue(prev.compareTo(next) < 0,
                    "Files should be in alphabetical order: " + prev + " before " + next);
        }
    }

    @Test
    void testDeepNesting() throws IOException {
        // Create very deep directory structure
        File current = tempDir.resolve("level0").toFile();
        current.mkdir();

        for (int i = 1; i <= 10; i++) {
            current = new File(current, "level" + i);
            current.mkdir();
        }

        // Add a file at the deepest level
        File deepFile = new File(current, "deep.txt");
        Files.writeString(deepFile.toPath(), "very deep");

        FsNode rootNode = FsNode.createNode(tempDir.resolve("level0").toFile());
        List<FsNode> nodes = rootNode.allNodes();

        assertEquals(12, nodes.size()); // 11 directories + 1 file
        assertEquals("deep.txt", nodes.get(11).getUnderlyingFile().getName());
    }

    @Test
    void testSpecialCharactersInFilenames() throws IOException {
        // Test files with special characters (but valid for file system)
        File dir = tempDir.resolve("special").toFile();
        dir.mkdir();

        File file1 = new File(dir, "file-with-dash.txt");
        File file2 = new File(dir, "file_with_underscore.txt");
        File file3 = new File(dir, "file.with.dots.txt");

        Files.writeString(file1.toPath(), "dash");
        Files.writeString(file2.toPath(), "underscore");
        Files.writeString(file3.toPath(), "dots");

        FsNode dirNode = FsNode.createNode(dir);
        List<FsNode> nodes = dirNode.allNodes();

        assertEquals(4, nodes.size());
        // Verify alphabetical ordering with special characters
        assertEquals("file-with-dash.txt", nodes.get(1).getUnderlyingFile().getName());
        assertEquals("file.with.dots.txt", nodes.get(2).getUnderlyingFile().getName());
        assertEquals("file_with_underscore.txt", nodes.get(3).getUnderlyingFile().getName());
    }

    @Test
    void testCaseSensitiveOrdering() throws IOException {
        // Test alphabetical ordering with mixed case
        File dir = tempDir.resolve("casetest").toFile();
        dir.mkdir();

        File fileA = new File(dir, "Apple.txt");
        File filea = new File(dir, "apple.txt");
        File fileB = new File(dir, "Banana.txt");

        Files.writeString(fileA.toPath(), "A");
        Files.writeString(filea.toPath(), "a");
        Files.writeString(fileB.toPath(), "B");

        FsNode dirNode = FsNode.createNode(dir);
        List<FsNode> nodes = dirNode.allNodes();

        assertEquals(4, nodes.size());
        // Natural ordering: 'A' < 'B' < 'a' (uppercase before lowercase)
        assertEquals("Apple.txt", nodes.get(1).getUnderlyingFile().getName());
        assertEquals("Banana.txt", nodes.get(2).getUnderlyingFile().getName());
        assertEquals("apple.txt", nodes.get(3).getUnderlyingFile().getName());
    }

    // ========== Text File Content Tests ==========

    @Test
    void testTextFileWithMultipleLineEndings() throws IOException {
        File file = tempDir.resolve("lineendings.txt").toFile();
        // Mix of line endings
        Files.writeString(file.toPath(), "line1\nline2\rline3\r\nline4");

        FsTextFile textFile = new FsTextFile(file);
        List<String> lines = textFile.getLines();

        // BufferedReader.readLine() should handle all line ending types
        assertTrue(lines.size() >= 3, "Should handle different line endings");
    }

    @Test
    void testTextFileWithUnicodeContent() throws IOException {
        File file = tempDir.resolve("unicode.txt").toFile();
        Files.writeString(file.toPath(), "Hello 世界\n你好 World\némoji 🌍");

        FsTextFile textFile = new FsTextFile(file);
        List<String> lines = textFile.getLines();

        assertTrue(lines.size() >= 2, "Should read unicode content");
        assertTrue(lines.get(0).contains("世界"), "Should preserve unicode characters");
    }

    @Test
    void testTextFileWithVeryLongLines() throws IOException {
        File file = tempDir.resolve("longline.txt").toFile();
        String longLine = "a".repeat(10000);
        Files.writeString(file.toPath(), longLine + "\nshort line");

        FsTextFile textFile = new FsTextFile(file);
        List<String> lines = textFile.getLines();

        assertEquals(2, lines.size());
        assertEquals(10000, lines.get(0).length());
        assertEquals("short line", lines.get(1));
    }

    // ========== Helper Methods ==========

    private File createComplexStructure(String baseName) throws IOException {
        File base = tempDir.resolve(baseName).toFile();
        base.mkdir();

        File sub1 = new File(base, "sub1");
        File sub2 = new File(base, "sub2");
        sub1.mkdir();
        sub2.mkdir();

        Files.writeString(new File(sub1, "a.txt").toPath(), "content a");
        Files.writeString(new File(sub1, "b.doc").toPath(), "content b");
        Files.writeString(new File(sub2, "c.txt").toPath(), "content c");
        Files.writeString(new File(base, "root.txt").toPath(), "root content");

        return base;
    }
}
