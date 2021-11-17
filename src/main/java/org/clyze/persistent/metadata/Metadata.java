package org.clyze.persistent.metadata;

import java.util.*;
import org.clyze.persistent.model.*;

/**
 * The common ancestor of both source and IR metadata.
 */
public abstract class Metadata {

    /**
     * The source files (including empty ones).
     */
    public final List<SourceFile> sourceFiles = new ArrayList<>();

    /**
     * Sort the metadata contents. This is useful for JSON output, as it
     * enables easy diffs between different runs.
     */
    public void sort() {
        Collections.sort(sourceFiles);
    }

    @SuppressWarnings("unchecked")
    public static void fillFromMap(Metadata metadata, Map<String, Object> map) {
        metadata.sourceFiles.addAll((List<SourceFile>) map.get(SourceFile.class.getSimpleName()));
    }

    public void printReportStats(Printer printer) {
        printer.println("Source files: " + sourceFiles.size());
    }

    public void populateJsonReport(Map<String, List<?>> jsonReport) {
        sort();
        jsonReport.put(SourceFile.class.getSimpleName(), sourceFiles);
    }

}