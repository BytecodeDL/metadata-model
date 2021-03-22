package org.clyze.persistent.model;

import java.util.*;

/**
 * An annotatable symbol with a unique id (a class/type, a field, or a method).
 */
public class AnnotatableSymbolWithId extends SymbolWithId {

    private Set<String> annotations = new HashSet<>();

    public AnnotatableSymbolWithId() {}

    public AnnotatableSymbolWithId(Position position, String sourceFileName,
                                   boolean source, String symbolId) {
        super(position, sourceFileName, source, symbolId);
    }

    public Set<String> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(Set<String> annotations) {
        this.annotations = annotations;
    }

    protected void saveTo(Map<String, Object> map) {
        super.saveTo(map);
        map.put("annotations", this.annotations);
    }

    @SuppressWarnings("unchecked")
    public void fromMap(Map<String, Object> map){
        super.fromMap(map);
        this.annotations = new HashSet<>((Collection<String>) map.get("annotations"));
    }
}
