package org.clyze.persistent.metadata;

import java.io.File;

/**
 * The output configuration for the metadata generator.
 */
public class Configuration {

    /** The output printer used to filter standard output/error. */
    public final Printer printer;
    /** The directory to store the json output files */
    private File outDir = null;

    /** The encoding of the input source code files; changed by "Main" if "-encoding" argument was provided */
    public static String encoding = "UTF-8";

    public Configuration(Printer printer) {
        this.printer = printer;
    }

    /**
     * Returns the output directory where JSON metadata will be stored.
     * @return   the output directory
     */
    public File getOutDir() {
        return outDir;
    }

    /**
     * Sets the output directory where JSON metadata will be stored.
     * @param outDir   the output directory
     */
    public void setOutDir(File outDir) {
        if (outDir != null && outDir.exists() && outDir.isDirectory() && outDir.canWrite())
            this.outDir = outDir;
        else
            throw new IllegalArgumentException("Output directory is invalid: " + outDir);
    }
}
