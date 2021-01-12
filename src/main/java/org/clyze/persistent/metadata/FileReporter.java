package org.clyze.persistent.metadata;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import org.clyze.persistent.model.BasicMetadata;

/**
 * This class provides utilities for writing the final JSON metadata files.
 */
public class FileReporter {

    /** The output configuration to use. */
    private final Configuration configuration;
    /** JSON formatter */
    private static final Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    /**
     * Creates a new file reporter to use for generating metadata.
     * @param configuration   the output configuration to use
     */
    public FileReporter(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * Prints a metadata report in the standard output.
     * @param fInfo  the object containing the metadata
     */
    public void printReportStats(FileInfo fInfo) {
        BasicMetadata metadata = fInfo.getElements();

        configuration.printer.println("Classes: " + metadata.classes.size());
        configuration.printer.println("Fields: " + metadata.fields.size());
        configuration.printer.println("Methods: " + metadata.methods.size());
        configuration.printer.println("Variables: " + metadata.variables.size());
        configuration.printer.println("HeapAllocations: " + metadata.heapAllocations.size());
        configuration.printer.println("MethodInvocations: " + metadata.invocations.size());
        configuration.printer.println("Usages: " + metadata.usages.size());
        configuration.printer.println("StringConstants: " + metadata.stringConstants.size());
    }

    /**
     * Writes the JSON metadata to the filesystem.
     * @param fInfo           the metadata file representation object
     */
    public void createReportFile(FileInfo fInfo) {
        File outDir = configuration.getOutDir();
        File reportFile = (outDir == null
                ? new File(fInfo.getOutputFilePath())
                : new File(outDir, fInfo.getOutputFilePath())
        );
        try (PrintWriter report = new PrintWriter(reportFile, "UTF-8")) {
            configuration.printer.println("Report: " + reportFile.getCanonicalPath());
            BasicMetadata metadata = fInfo.getElements();
            Map<String, List<?>> jsonReport = new HashMap<>();
            // Sort sets (by Doop id) so that output order is predictable.
            jsonReport.put("Class", BasicMetadata.getSortedByDoopId(metadata.classes));
            jsonReport.put("Field", BasicMetadata.getSortedByDoopId(metadata.fields));
            jsonReport.put("Method", BasicMetadata.getSortedByDoopId(metadata.methods));
            jsonReport.put("Variable", BasicMetadata.getSortedByDoopId(metadata.variables));
            jsonReport.put("HeapAllocation", BasicMetadata.getSortedByDoopId(metadata.heapAllocations));
            jsonReport.put("MethodInvocation", BasicMetadata.getSortedByDoopId(metadata.invocations));
            jsonReport.put("Usage", BasicMetadata.getSortedByDoopId(metadata.usages));
            jsonReport.put("StringConstant", new ArrayList<>(metadata.stringConstants));
            report.write(gson.toJson(jsonReport));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
