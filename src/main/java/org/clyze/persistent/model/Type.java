package org.clyze.persistent.model;

import java.util.Map;
import java.util.Objects;

/**
 * A type (examples: class, interface, struct, enum, union).
 */
public class Type extends AnnotatableSymbolWithId {
    /**
     * The type name (package name not included)
     */
    private String name;

    /**
     * Nullary constructor, use setters or fromMap() to populate the object.
     */
    public Type() { }

    public Type(String id) {
        this.id = id;
    }

    public Type(Position position,
                String sourceFileName,
                String symbolId,
                String name) {
        super(position, sourceFileName, symbolId);
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
        map.put("name", this.name);
    }

    @Override
    public void fromMap(Map<String, Object> map) {
        super.fromMap(map);
        this.name = (String) map.get("name");
    }

    @Override
    public boolean equals(Object object) {
        if ((!(object instanceof Type)))
            return false;
        Type that = (Type) object;
        return super.equals(object) && Objects.equals(this.name, that.name);
    }
}
