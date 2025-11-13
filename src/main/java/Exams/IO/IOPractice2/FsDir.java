package Exams.IO.IOPractice2;

import java.io.File;
import java.util.List;

public class FsDir {
    private final File directory;
    private final List<FsNode> children;
    /**
     * Creates a new representation of a directory in
     * a file system given an abstract path name
     * @param dir an abstract pathname pointing to a directory
     */
    public FsDir(File dir) {


    }

    public File getDirectory() {
        return directory;
    }

    public List<FsNode> getChildren() {
        return children;
    }
}
