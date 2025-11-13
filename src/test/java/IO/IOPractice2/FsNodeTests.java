package IO.IOPractice2;

import Exams.IO.IOPractice2.FsDir;
import Exams.IO.IOPractice2.FsNode;
import Exams.IO.IOPractice2.FsNonTextFile;
import Exams.IO.IOPractice2.FsTextFile;
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
 * Comprehensive test suite for FsNode and its implementations
 */
public class FsNodeTests {

    @TempDir
    Path tempDir;

    private File testDir;
    private File textFile1;
    private File textFile2;
    private File nonTextFile;
    private File subDir;
    private File subTextFile;

    @BeforeEach
    void setUp() throws IOException {
        // Create test file structure:
        // tempDir/
        // ├── A/
        // │   ├── B/
        // │   │   └── C.txt ("content in C")
        // │   ├── C.txt ("content in root C")
        // │   └── D.doc
        // ├── test1.txt ("line 1\nline 2")
        // └── image.png

        testDir = tempDir.resolve("A").toFile();
        testDir.mkdirs();

        subDir = tempDir.resolve("A/B").toFile();
        subDir.mkdirs();

        subTextFile = tempDir.resolve("A/B/C.txt").toFile();
        Files.writeString(subTextFile.toPath(), "content in C");

        textFile1 = tempDir.resolve("A/C.txt").toFile();
        Files.writeString(textFile1.toPath(), "content in root C");

        nonTextFile = tempDir.resolve("A/D.doc").toFile();
        Files.writeString(nonTextFile.toPath(), "some binary content");

        textFile2 = tempDir.resolve("test1.txt").toFile();
        Files.writeString(textFile2.toPath(), "line 1\nline 2\nline 3");

        File imageFile = tempDir.resolve("image.png").toFile();
        Files.write(imageFile.toPath(), new byte[]{1, 2, 3, 4});
    }

    // ========== Test FsNode.createNode() Factory Method ==========

    @Test
    void testCreateNode_Directory() {
        FsNode node = FsNode.createNode(testDir);
        assertNotNull(node, "createNode should not return null");
        assertTrue(node instanceof FsDir, "Should create FsDir for directory");
        assertEquals(testDir, node.getUnderlyingFile());
    }

    @Test
    void testCreateNode_TextFile() {
        FsNode node = FsNode.createNode(textFile1);
        assertNotNull(node, "createNode should not return null");
        assertTrue(node instanceof FsTextFile, "Should create FsTextFile for .txt files");
        assertEquals(textFile1, node.getUnderlyingFile());
    }

    @Test
    void testCreateNode_NonTextFile() {
        FsNode node = FsNode.createNode(nonTextFile);
        assertNotNull(node, "createNode should not return null");
        assertTrue(node instanceof FsNonTextFile, "Should create FsNonTextFile for non-.txt files");
        assertEquals(nonTextFile, node.getUnderlyingFile());
    }

    // ========== Test FsTextFile ==========

    @Test
    void testTextFile_ReadLines() throws IOException {
        File testFile = tempDir.resolve("multiline.txt").toFile();
        Files.writeString(testFile.toPath(), "line 1\nline 2\nline 3");

        FsTextFile textFile = new FsTextFile(testFile);
        List<String> lines = textFile.getLines();

        assertEquals(3, lines.size(), "Should read all 3 lines");
        assertEquals("line 1", lines.get(0));
        assertEquals("line 2", lines.get(1));
        assertEquals("line 3", lines.get(2));
    }

    @Test
    void testTextFile_EmptyFile() throws IOException {
        File emptyFile = tempDir.resolve("empty.txt").toFile();
        Files.writeString(emptyFile.toPath(), "");

        FsTextFile textFile = new FsTextFile(emptyFile);
        List<String> lines = textFile.getLines();

        assertEquals(0, lines.size(), "Empty file should have 0 lines");
    }

    @Test
    void testTextFile_AllNodes() {
        FsTextFile textFile = new FsTextFile(textFile1);
        List<FsNode> nodes = textFile.allNodes();

        assertEquals(1, nodes.size(), "Text file should only contain itself");
        assertSame(textFile, nodes.get(0));
    }

    @Test
    void testTextFile_Equality_SameNameAndContent() throws IOException {
        File file1 = tempDir.resolve("test.txt").toFile();
        File file2 = tempDir.resolve("test.txt").toFile();
        Files.writeString(file1.toPath(), "content");
        Files.writeString(file2.toPath(), "content");

        FsTextFile text1 = new FsTextFile(file1);
        FsTextFile text2 = new FsTextFile(file2);

        assertEquals(text1, text2, "Text files with same name and content should be equal");
        assertEquals(text1.hashCode(), text2.hashCode(), "Equal objects should have same hashCode");
    }

    @Test
    void testTextFile_Equality_SameNameDifferentContent() throws IOException {
        File file1 = tempDir.resolve("dir1/test.txt").toFile();
        File file2 = tempDir.resolve("dir2/test.txt").toFile();
        file1.getParentFile().mkdirs();
        file2.getParentFile().mkdirs();
        Files.writeString(file1.toPath(), "content1");
        Files.writeString(file2.toPath(), "content2");

        FsTextFile text1 = new FsTextFile(file1);
        FsTextFile text2 = new FsTextFile(file2);

        assertNotEquals(text1, text2, "Text files with different content should not be equal");
    }

    @Test
    void testTextFile_Equality_DifferentName() throws IOException {
        File file1 = tempDir.resolve("test1.txt").toFile();
        File file2 = tempDir.resolve("test2.txt").toFile();
        Files.writeString(file1.toPath(), "content");
        Files.writeString(file2.toPath(), "content");

        FsTextFile text1 = new FsTextFile(file1);
        FsTextFile text2 = new FsTextFile(file2);

        assertNotEquals(text1, text2, "Text files with different names should not be equal");
    }

    // ========== Test FsNonTextFile ==========

    @Test
    void testNonTextFile_AllNodes() {
        FsNonTextFile nonText = new FsNonTextFile(nonTextFile);
        List<FsNode> nodes = nonText.allNodes();

        assertEquals(1, nodes.size(), "Non-text file should only contain itself");
        assertSame(nonText, nodes.get(0));
    }

    @Test
    void testNonTextFile_Equality_SameName() throws IOException {
        File file1 = tempDir.resolve("dir1/test.doc").toFile();
        File file2 = tempDir.resolve("dir2/test.doc").toFile();
        file1.getParentFile().mkdirs();
        file2.getParentFile().mkdirs();
        Files.writeString(file1.toPath(), "content1");
        Files.writeString(file2.toPath(), "content2");

        FsNonTextFile nonText1 = new FsNonTextFile(file1);
        FsNonTextFile nonText2 = new FsNonTextFile(file2);

        assertEquals(nonText1, nonText2, "Non-text files with same name should be equal regardless of content");
        assertEquals(nonText1.hashCode(), nonText2.hashCode());
    }

    @Test
    void testNonTextFile_Equality_DifferentName() {
        File file1 = tempDir.resolve("test1.doc").toFile();
        File file2 = tempDir.resolve("test2.doc").toFile();

        FsNonTextFile nonText1 = new FsNonTextFile(file1);
        FsNonTextFile nonText2 = new FsNonTextFile(file2);

        assertNotEquals(nonText1, nonText2, "Non-text files with different names should not be equal");
    }

    // ========== Test FsDir ==========

    @Test
    void testDir_ChildrenSortedAlphabetically() {
        FsDir dir = new FsDir(testDir);
        List<FsNode> children = dir.getChildren();

        assertEquals(3, children.size(), "Directory A should have 3 children");

        // Children should be in alphabetical order: B, C.txt, D.doc
        assertEquals("B", children.get(0).getUnderlyingFile().getName());
        assertEquals("C.txt", children.get(1).getUnderlyingFile().getName());
        assertEquals("D.doc", children.get(2).getUnderlyingFile().getName());
    }

    @Test
    void testDir_ChildrenTypes() {
        FsDir dir = new FsDir(testDir);
        List<FsNode> children = dir.getChildren();

        assertTrue(children.get(0) instanceof FsDir, "B should be directory");
        assertTrue(children.get(1) instanceof FsTextFile, "C.txt should be text file");
        assertTrue(children.get(2) instanceof FsNonTextFile, "D.doc should be non-text file");
    }

    @Test
    void testDir_EmptyDirectory() throws IOException {
        File emptyDir = tempDir.resolve("empty").toFile();
        emptyDir.mkdir();

        FsDir dir = new FsDir(emptyDir);
        List<FsNode> children = dir.getChildren();

        assertEquals(0, children.size(), "Empty directory should have 0 children");
    }

    @Test
    void testDir_Equality_SameStructure() throws IOException {
        // Create two identical directory structures
        File dir1 = tempDir.resolve("dir1/test").toFile();
        File dir2 = tempDir.resolve("dir2/test").toFile();
        dir1.mkdirs();
        dir2.mkdirs();

        File file1 = new File(dir1, "a.txt");
        File file2 = new File(dir2, "a.txt");
        Files.writeString(file1.toPath(), "content");
        Files.writeString(file2.toPath(), "content");

        FsDir fsDir1 = new FsDir(dir1);
        FsDir fsDir2 = new FsDir(dir2);

        assertEquals(fsDir1, fsDir2, "Directories with same name and contents should be equal");
    }

    @Test
    void testDir_Equality_DifferentChildren() throws IOException {
        File dir1 = tempDir.resolve("dir1/test").toFile();
        File dir2 = tempDir.resolve("dir2/test").toFile();
        dir1.mkdirs();
        dir2.mkdirs();

        File file1 = new File(dir1, "a.txt");
        File file2 = new File(dir2, "b.txt");
        Files.writeString(file1.toPath(), "content");
        Files.writeString(file2.toPath(), "content");

        FsDir fsDir1 = new FsDir(dir1);
        FsDir fsDir2 = new FsDir(dir2);

        assertNotEquals(fsDir1, fsDir2, "Directories with different children should not be equal");
    }

    // ========== Test allNodes() Pre-order DFS ==========

    @Test
    void testAllNodes_SingleFile() {
        FsNode node = FsNode.createNode(textFile1);
        List<FsNode> nodes = node.allNodes();

        assertEquals(1, nodes.size());
        assertEquals("C.txt", nodes.get(0).getUnderlyingFile().getName());
    }

    @Test
    void testAllNodes_DirectoryPreOrderDFS() {
        FsNode rootNode = FsNode.createNode(testDir);
        List<FsNode> nodes = rootNode.allNodes();

        // Expected order (pre-order DFS, alphabetical):
        // A, B, C.txt (in B), C.txt (in A), D.doc
        assertEquals(5, nodes.size(), "Should have 5 nodes total");

        assertEquals("A", nodes.get(0).getUnderlyingFile().getName());
        assertEquals("B", nodes.get(1).getUnderlyingFile().getName());
        assertEquals("C.txt", nodes.get(2).getUnderlyingFile().getName());
        assertEquals("C.txt", nodes.get(3).getUnderlyingFile().getName());
        assertEquals("D.doc", nodes.get(4).getUnderlyingFile().getName());

        // Verify types
        assertTrue(nodes.get(0) instanceof FsDir);
        assertTrue(nodes.get(1) instanceof FsDir);
        assertTrue(nodes.get(2) instanceof FsTextFile);
        assertTrue(nodes.get(3) instanceof FsTextFile);
        assertTrue(nodes.get(4) instanceof FsNonTextFile);
    }

    @Test
    void testAllNodes_VerifyPreOrderNotPostOrder() throws IOException {
        // Create structure: root/ -> child/ -> file.txt
        File root = tempDir.resolve("root").toFile();
        File child = tempDir.resolve("root/child").toFile();
        child.mkdirs();
        File file = tempDir.resolve("root/child/file.txt").toFile();
        Files.writeString(file.toPath(), "content");

        FsNode rootNode = FsNode.createNode(root);
        List<FsNode> nodes = rootNode.allNodes();

        // Pre-order: root comes before its children
        assertEquals("root", nodes.get(0).getUnderlyingFile().getName());
        assertEquals("child", nodes.get(1).getUnderlyingFile().getName());
        assertEquals("file.txt", nodes.get(2).getUnderlyingFile().getName());
    }

    @Test
    void testAllNodes_AlphabeticalOrdering() throws IOException {
        // Create directory with files in non-alphabetical order
        File dir = tempDir.resolve("testorder").toFile();
        dir.mkdir();

        // Create files that would not be in alphabetical order naturally
        new File(dir, "z.txt").createNewFile();
        new File(dir, "a.txt").createNewFile();
        new File(dir, "m.txt").createNewFile();

        Files.writeString(new File(dir, "z.txt").toPath(), "");
        Files.writeString(new File(dir, "a.txt").toPath(), "");
        Files.writeString(new File(dir, "m.txt").toPath(), "");

        FsNode dirNode = FsNode.createNode(dir);
        List<FsNode> nodes = dirNode.allNodes();

        // Should be: testorder, a.txt, m.txt, z.txt
        assertEquals(4, nodes.size());
        assertEquals("testorder", nodes.get(0).getUnderlyingFile().getName());
        assertEquals("a.txt", nodes.get(1).getUnderlyingFile().getName());
        assertEquals("m.txt", nodes.get(2).getUnderlyingFile().getName());
        assertEquals("z.txt", nodes.get(3).getUnderlyingFile().getName());
    }

    @Test
    void testAllNodes_DeepNesting() throws IOException {
        // Create deeply nested structure
        File level1 = tempDir.resolve("level1").toFile();
        File level2 = tempDir.resolve("level1/level2").toFile();
        File level3 = tempDir.resolve("level1/level2/level3").toFile();
        level3.mkdirs();

        File file = tempDir.resolve("level1/level2/level3/deep.txt").toFile();
        Files.writeString(file.toPath(), "deep content");

        FsNode rootNode = FsNode.createNode(level1);
        List<FsNode> nodes = rootNode.allNodes();

        assertEquals(4, nodes.size());
        assertEquals("level1", nodes.get(0).getUnderlyingFile().getName());
        assertEquals("level2", nodes.get(1).getUnderlyingFile().getName());
        assertEquals("level3", nodes.get(2).getUnderlyingFile().getName());
        assertEquals("deep.txt", nodes.get(3).getUnderlyingFile().getName());
    }

    // ========== Edge Cases ==========

    @Test
    void testEquality_Reflexive() {
        FsNode node = FsNode.createNode(textFile1);
        assertEquals(node, node, "Object should equal itself");
    }

    @Test
    void testEquality_Symmetric() throws IOException {
        File file1 = tempDir.resolve("same1.txt").toFile();
        File file2 = tempDir.resolve("same2.txt").toFile();
        Files.writeString(file1.toPath(), "content");
        Files.writeString(file2.toPath(), "content");

        // Make them have the same name
        file1 = tempDir.resolve("dir1/same.txt").toFile();
        file2 = tempDir.resolve("dir2/same.txt").toFile();
        file1.getParentFile().mkdirs();
        file2.getParentFile().mkdirs();
        Files.writeString(file1.toPath(), "content");
        Files.writeString(file2.toPath(), "content");

        FsTextFile text1 = new FsTextFile(file1);
        FsTextFile text2 = new FsTextFile(file2);

        assertEquals(text1, text2);
        assertEquals(text2, text1, "Equality should be symmetric");
    }

    @Test
    void testEquality_NullAndDifferentClass() {
        FsNode node = FsNode.createNode(textFile1);
        assertNotEquals(node, null, "Should not equal null");
        assertNotEquals(node, "string", "Should not equal different class");
    }

    @Test
    void testGetUnderlyingFile_ReturnsCorrectFile() {
        FsNode textNode = FsNode.createNode(textFile1);
        FsNode nonTextNode = FsNode.createNode(nonTextFile);
        FsNode dirNode = FsNode.createNode(testDir);

        assertEquals(textFile1, textNode.getUnderlyingFile());
        assertEquals(nonTextFile, nonTextNode.getUnderlyingFile());
        assertEquals(testDir, dirNode.getUnderlyingFile());
    }
}
