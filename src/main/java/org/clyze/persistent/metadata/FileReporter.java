package org.clyze.persistent.metadata;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * This class provides utilities for writing the final JSON metadata files.
 */
public class FileReporter {

    /** The output configuration to use. */
    protected final Configuration configuration;
    /** The metadata to be serialized. */
    private final Metadata metadata;

    /**
     * Creates a new file reporter to use for generating metadata.
     * @param configuration   the output configuration to use
     * @param metadata        the metadata object to use
     */
    public FileReporter(Configuration configuration, Metadata metadata) {
        this.configuration = configuration;
        this.metadata = metadata;
    }

    /**
     * Prints a metadata report in the standard output.
     */
    public void printReportStats() {
        metadata.printReportStats(configuration.printer);
    }


    protected Map<String, List<?>> createJsonReport() {
        Map<String, List<?>> jsonReport = new HashMap<>();
        metadata.populateJsonReport(jsonReport);
        return jsonReport;
    }

    /**
     * Writes the JSON metadata to the filesystem.
     * @param outputFilePath      the output report file path
     */
    public void createReportFile(String outputFilePath) {
        File outDir = configuration.getOutDir();
        File reportFile = (outDir == null
                ? new File(outputFilePath)
                : new File(outDir, outputFilePath)
        );
        try (PrintWriter report = new PrintWriter(reportFile, "UTF-8")) {
            configuration.printer.println("Report: " + reportFile.getCanonicalPath());
            Map<String, List<?>> jsonReport = createJsonReport();
            report.write(JSONUtil.getObjectWriter().writeValueAsString(jsonReport));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
