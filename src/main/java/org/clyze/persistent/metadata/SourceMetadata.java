package org.clyze.persistent.metadata;

import java.util.*;
import org.clyze.persistent.model.Function;
import org.clyze.persistent.model.SourceFile;
import org.clyze.persistent.model.Type;
import org.clyze.persistent.model.Variable;

/**
 * A container class for all metadata gathered for a set of source files.
 */
public class SourceMetadata {
    /** The declared source types. */
    public final List<Type> types = new ArrayList<>();
    /** The declared source functions. */
    public final List<Function> functions = new ArrayList<>();
    /** The source variables. */
    public final List<Variable> variables = new ArrayList<>();
    /** The source files (including empty ones). */
    public final List<SourceFile> sourceFiles = new ArrayList<>();

    /**
     * Sort the metadata contents. This is useful for JSON output, as it
     * enables easy diffs between different runs.
     */
    public void sort() {
        Collections.sort(types);
        Collections.sort(functions);
        Collections.sort(variables);
        Collections.sort(sourceFiles);
    }

    /**
     * Create a source metadata object from a map representation for JSON data.
     * @param  map the map to use
     * @return the deserialized source metadata object
     */
    @SuppressWarnings("unchecked")
    public static SourceMetadata fromMap(Map<String, Object> map) {
        SourceMetadata metadata = new SourceMetadata();
        metadata.types.addAll((List<Type>) map.get(Type.class.getSimpleName()));
        metadata.functions.addAll((List<Function>) map.get(Function.class.getSimpleName()));
        metadata.variables.addAll((List<Variable>) map.get(Variable.class.getSimpleName()));
        metadata.sourceFiles.addAll((List<SourceFile>) map.get(SourceFile.class.getSimpleName()));
        return metadata;
    }
}
