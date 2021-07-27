package org.clyze.persistent.metadata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.clyze.persistent.model.Function;
import org.clyze.persistent.model.SourceFile;
import org.clyze.persistent.model.Type;
import org.clyze.persistent.model.Variable;

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
        configuration.printer.println("Variables: " + metadata.variables.size());
        configuration.printer.println("Source files: " + metadata.sourceFiles.size());
    }

    @Override
    protected Map<String, List<?>> createJsonReport() {
        Map<String, List<?>> jsonReport = new HashMap<>();
        metadata.sort();
        jsonReport.put(Type.class.getSimpleName(), metadata.types);
        jsonReport.put(Function.class.getSimpleName(), metadata.functions);
        jsonReport.put(Variable.class.getSimpleName(), metadata.variables);
        jsonReport.put(SourceFile.class.getSimpleName(), metadata.sourceFiles);
        return jsonReport;
    }
}
