package org.clyze.persistent.model;

import java.util.Map;
import java.util.Objects;

/**
 * A class/type field.
 */
public class Field extends AnnotatableSymbolWithId {
    private String name;

    /** No-arg constructor, use setters or fromMap() to populate the object. */
    public Field() {}

    /**
     * Single-arg constructor, use setters or fromMap() to populate the object.
     * Used during deserialization.
     * @param id      a unique deserialization id
     */
    public Field(String id) {
        this.id = id;
    }

    public Field(Position position,
                    String sourceFileName,
                    boolean source,
                    String name,
                    String symbolId) {
        super(position, sourceFileName, source, symbolId);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void saveTo(Map<String, Object> map) {
        super.saveTo(map);
        map.put("name", getName());
    }

    @Override
    public void fromMap(Map<String, Object> map){
        super.fromMap(map);
        setName((String) map.get("name"));
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (!(that instanceof Field)) return false;
        Field field = (Field) that;
        return super.equals(field) && Objects.equals(name, field.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
}
