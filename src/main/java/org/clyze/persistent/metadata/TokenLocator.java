package org.clyze.persistent.metadata;

import org.clyze.persistent.model.Position;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * This interface maps source code tokens to their position.
 */
public interface TokenLocator {
    /**
     * Computes a map from parser tokens to their source locations.
     * @return   a map from (non-null) tokens to (non-null) locations
     */
    Map<String, Collection<Position>> getTokenLocations();

    default void addTokenWithLocation(Map<String, Collection<Position>> map, String key, Position pos) {
        if (key == null || pos == null)
            return;
        map.computeIfAbsent(key, (k -> new ArrayList<>())).add(pos);
    }

}
