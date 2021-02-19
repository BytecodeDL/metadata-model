package org.clyze.persistent.metadata;

import org.clyze.persistent.model.Type;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * A container class for all metadata gathered for a set of source files.
 */
public class SourceMetadata {
    public final List<Type> types = new LinkedList<>();

    public void sort() {
        Collections.sort(types);
    }
}
