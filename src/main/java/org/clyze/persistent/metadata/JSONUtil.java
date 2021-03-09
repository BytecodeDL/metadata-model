package org.clyze.persistent.metadata;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

/** A collection of JSON utility methods. */
public class JSONUtil {
    /**
     * Get a JSON serializer object. Use this method for consistency in JSON output.
     * @return   an object writer to use for serializing Java objects
     */
    public static ObjectWriter getObjectWriter() {
        return (new ObjectMapper())
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                .disable(MapperFeature.AUTO_DETECT_IS_GETTERS)
                .disable(MapperFeature.AUTO_DETECT_GETTERS).writerWithDefaultPrettyPrinter();
    }

    /**
     * Convert JSON data to a Java Map.
     * @param json   the JSON data
     * @return       the Map object
     */
    public static Map<String, Object> toMap(String json) throws JsonProcessingException {
        return (Map<String, Object>)new ObjectMapper().readValue(json, Map.class);
    }

    /**
     * Convert JSON file to a Java Map.
     * @param path   the JSON file path
     * @return       the Map object
     */
    public static Map<String, Object> toMap(Path path) throws IOException {
        return JSONUtil.toMap(new String(Files.readAllBytes(path)));
    }
}
