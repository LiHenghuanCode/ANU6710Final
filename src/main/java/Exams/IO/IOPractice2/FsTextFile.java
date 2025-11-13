package Exams.IO.IOPractice2;

import Exams.IO.IOPractice2.FsNode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a text file in a file system given an abstract path name
 */
public class FsTextFile implements FsNode {

    private final File file;
    private final List<String> lines;

    /**
     * Creates a new representation of a text file in
     * a file system given an abstract path name
     * @param file an abstract pathname pointing to a text file
     */
    public FsTextFile(File file) {
        this.file = file;
        this.lines = new ArrayList<>();

        // TODO: Read all lines from the file
        // Hint: Use BufferedReader and FileReader
        // Remember to handle IOException

    }

    @Override
    public File getUnderlyingFile() {
        // TODO: Return the underlying file
        return null;
    }

    @Override
    public List<FsNode> allNodes() {
        // TODO: For a file, return a list containing only this node
        return null;
    }

    /**
     * @return the lines of text in this file
     */
    public List<String> getLines() {
        // TODO: Return a copy of the lines list
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        // TODO: Implement equality based on:
        // 1. Same class type
        // 2. Same file name
        // 3. Same text content (all lines equal)
        return false;
    }

    @Override
    public int hashCode() {
        // TODO: Implement hashCode consistent with equals
        // Combine hash of file name and lines
        return 0;
    }

    @Override
    public String toString() {
        return "FsTextFile{" + file.getAbsolutePath() + ", lines=" + lines.size() + "}";
    }
}
