package org.clyze.persistent.metadata.jvm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.clyze.persistent.metadata.Configuration;
import org.clyze.persistent.metadata.FileInfo;
import org.clyze.persistent.metadata.FileReporter;

/**
 * The reporter for JVM-specific metadata.
 */
public class JvmFileReporter extends FileReporter {
    private final FileInfo fInfo;

    /**
     * Creates a new file reporter to use for generating metadata.
     *
     * @param configuration  the output configuration to use
     * @param fInfo          the object containing the metadata
     */
    public JvmFileReporter(Configuration configuration, FileInfo fInfo) {
        super(configuration);
        this.fInfo = fInfo;
    }

    /**
     * Prints a metadata report in the standard output.
     */
    @Override
    public void printReportStats() {
        JvmMetadata metadata = fInfo.getElements();
        configuration.printer.println("Classes: " + metadata.jvmClasses.size());
        configuration.printer.println("Fields: " + metadata.jvmFields.size());
        configuration.printer.println("Methods: " + metadata.jvmMethods.size());
        configuration.printer.println("Variables: " + metadata.jvmVariables.size());
        configuration.printer.println("HeapAllocations: " + metadata.jvmHeapAllocations.size());
        configuration.printer.println("MethodInvocations: " + metadata.jvmInvocations.size());
        configuration.printer.println("Usages: " + metadata.usages.size());
        configuration.printer.println("StringConstants: " + metadata.stringConstants.size());
    }

    @Override
    protected Map<String, List<?>> createJsonReport() {
        JvmMetadata metadata = fInfo.getElements();
        Map<String, List<?>> jsonReport = new HashMap<>();
        // Sort sets (by id) so that output order is predictable.
        jsonReport.put("Class", JvmMetadata.getSortedByDoopId(metadata.jvmClasses));
        jsonReport.put("Field", JvmMetadata.getSortedByDoopId(metadata.jvmFields));
        jsonReport.put("Method", JvmMetadata.getSortedByDoopId(metadata.jvmMethods));
        jsonReport.put("Variable", JvmMetadata.getSortedByDoopId(metadata.jvmVariables));
        jsonReport.put("HeapAllocation", JvmMetadata.getSortedByDoopId(metadata.jvmHeapAllocations));
        jsonReport.put("MethodInvocation", JvmMetadata.getSortedByDoopId(metadata.jvmInvocations));
        jsonReport.put("Usage", JvmMetadata.getSortedByDoopId(metadata.usages));
        jsonReport.put("StringConstant", new ArrayList<>(metadata.stringConstants));
        return jsonReport;
    }

}
