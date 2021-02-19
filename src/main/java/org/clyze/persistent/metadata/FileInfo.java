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

    public String getSourceFileName() {
        return sourceFileName;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }

    public JvmMetadata getElements() {
        return elements;
    }

}
