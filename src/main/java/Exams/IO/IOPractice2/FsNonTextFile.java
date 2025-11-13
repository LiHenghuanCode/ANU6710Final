package Exams.IO.IOPractice2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents some file that is not a directory nor a
 * text file in the file system.
 */
public class FsNonTextFile implements FsNode {

    private final File file;

    /**
     * Creates a new representation of a file that is not a
     * text file in a file system given an abstract path name
     * @param file an abstract pathname pointing to a file
     */
    public FsNonTextFile(File file) {
        // TODO: Initialize the file field

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

    @Override
    public boolean equals(Object obj) {
        // TODO: Implement equality based on:
        // 1. Same class type
        // 2. Same file name (not path)
        return false;
    }

    @Override
    public int hashCode() {
        // TODO: Implement hashCode consistent with equals
        // Use the hash of the file name
        return 0;
    }

    @Override
    public String toString() {
        return "FsNonTextFile{" + file.getAbsolutePath() + "}";
    }
}
