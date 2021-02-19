package org.clyze.persistent.model;

import org.clyze.persistent.model.Position;
import org.clyze.persistent.model.SymbolWithId;

import java.util.*;

/**
 * An annotateable symbol with a unique id (a class/type, a field, or a method).
 */
public class AnnotatableSymbolWithId extends SymbolWithId {

    private Set<String> annotationTypes = new HashSet<>();

    public AnnotatableSymbolWithId() {}

    public AnnotatableSymbolWithId(Position position, String sourceFileName, String symbolId) {
        super(position, sourceFileName, symbolId);
    }

    public Set<String> getAnnotationTypes() {
        return annotationTypes;
    }

    public void setAnnotationTypes(Set<String> annotationTypes) {
        this.annotationTypes = annotationTypes;
    }

    protected void saveTo(Map<String, Object> map) {
        super.saveTo(map);
        map.put("annotationTypes", this.annotationTypes);
    }

    @SuppressWarnings("unchecked")
    public void fromMap(Map<String, Object> map){
        super.fromMap(map);
        this.annotationTypes = new HashSet<>((Collection<String>) map.get("annotationTypes"));
    }
}
