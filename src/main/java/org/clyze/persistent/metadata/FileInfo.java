package org.clyze.persistent.metadata;

import static org.apache.commons.io.FilenameUtils.getBaseName;
import org.clyze.persistent.metadata.jvm.JvmMetadata;

/**
 * This class holds per-file information and offers functions relevant to files.
 */
public class FileInfo {

    /** Input source file path */
    protected final String inputFilePath;

    /** File path to store in "sourceFileName" property of elements */
    private final String sourceFileName;

    /** JSON output file name */
    private final String outputFilePath;

    /** The contents of the input source file */
    protected final String source;

    /** The container of found elements in the input source file */
    private final JvmMetadata elements;

    /**
     * Create a file information file for a source file (e.g. Java/Groovy/Kotlin).
     * @param packageName     the package name declared at the top of the file
     * @param inputName       the name of the file (without extension)
     * @param inputFilePath   the file path
     * @param source          the source contents
     * @param elements        the gathered metadata
     */
    public FileInfo(String packageName, String inputName, String inputFilePath, String source, JvmMetadata elements) {
        this.inputFilePath = inputFilePath;
        this.sourceFileName = packageName.replaceAll("\\.", "/") + inputName;
        this.outputFilePath = packageName.replaceAll("/", ".") + getBaseName(inputFilePath) + ".json";
        this.elements = elements;
        this.source = source;
    }

    @Override
    public int hashCode() {
        return inputFilePath.hashCode();
    }

    /**
     * Returns the source file name.
     * @return source file name
     */
    public String getSourceFileName() {
        return sourceFileName;
    }

    /**
     * Returns the output file path.
     * @return the path of the file to use for writing the metadata
     */
    public String getOutputFilePath() {
        return outputFilePath;
    }

    /**
     * Return the current metadata for this file.
     * @return the metadata object
     */
    public JvmMetadata getElements() {
        return elements;
    }

}
