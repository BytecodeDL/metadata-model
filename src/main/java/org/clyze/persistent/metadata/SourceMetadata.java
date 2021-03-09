package org.clyze.persistent.metadata;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.clyze.persistent.model.Function;
import org.clyze.persistent.model.Type;

/**
 * A container class for all metadata gathered for a set of source files.
 */
public class SourceMetadata {
    public final List<Type> types = new LinkedList<>();
    public final List<Function> functions = new LinkedList<>();

    public void sort() {
        Collections.sort(types);
        Collections.sort(functions);
    }

    /**
     * Create a source metadata object from a map representation for JSON data.
     * @param  map the map to use
     * @return the deserialized source metadata object
     */
    public static SourceMetadata fromMap(Map<String, Object> map) {
        SourceMetadata metadata = new SourceMetadata();
        metadata.types.addAll((List<Type>) map.get(Type.class.getSimpleName()));
        metadata.functions.addAll((List<Function>) map.get(Function.class.getSimpleName()));
        return metadata;
    }
}
