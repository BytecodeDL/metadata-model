package org.clyze.persistent.metadata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The reporter for language-agnostic metadata.
 */
public class SourceFileReporter extends FileReporter {
    /** The metadata to be serialized. */
    private final SourceMetadata metadata;

    /**
     * Creates a new file reporter to use for generating metadata.
     *
     * @param configuration the output configuration to use
     * @param metadata      the metadata object to use
     */
    public SourceFileReporter(Configuration configuration, SourceMetadata metadata) {
        super(configuration);
        this.metadata = metadata;
    }

    @Override
    public void printReportStats() {
        configuration.printer.println("Types: " + metadata.types.size());
        configuration.printer.println("Functions: " + metadata.functions.size());
    }

    @Override
    protected Map<String, List<?>> createJsonReport() {
        Map<String, List<?>> jsonReport = new HashMap<>();
        metadata.sort();
        jsonReport.put("Type", metadata.types);
        jsonReport.put("Function", metadata.functions);
        return jsonReport;
    }
}
