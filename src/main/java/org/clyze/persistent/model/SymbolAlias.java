package org.clyze.persistent.model;

import java.util.Map;

/**
 * This class is used to handle code elements that may be referenced with
 * more than one symbol ids (in addition to their original symbol id).
 */
public class SymbolAlias extends SymbolWithId {
    /** The symbol id of the aliased element. */
    private String originId;

    /** No-arg constructor, use setters or fromMap() to populate the object. */
    public SymbolAlias() {}

    /**
     * Single-arg constructor, use setters or fromMap() to populate the object.
     * Used during deserialization.
     * @param id      a unique deserialization id
     */
    public SymbolAlias(String id) {
        this.id = id;
    }

    /**
     * Create an id alias, e.g. a variable "method/x" that code may refer to
     * as "method/r23".
     * @param sourceFileName    the name of the source file
     * @param symbolId          the symbol id of the alias ("method/r23")
     * @param originId          the original id ("method/x")
     */
    public SymbolAlias(String sourceFileName, String symbolId, String originId) {
        super(null, sourceFileName, false, symbolId);
        this.originId = originId;
    }

    /**
     * Get the id of the aliased code element.
     * @return   the String id of the element referred to by this alias
     */
    public String getOriginId() {
        return this.originId;
    }

    /**
     * Set the original symbol aliased by this object.
     * @param originId   the original symbol id
     */
    public void setOriginId(String originId) {
        this.originId = originId;
    }

    @Override
    protected void saveTo(Map<String, Object> map) {
        super.saveTo(map);
        map.put("originId", getOriginId());
    }

    @Override
    public void fromMap(Map<String, Object> map){
        super.fromMap(map);
        setOriginId((String) map.get("originId"));
    }

    @Override
    public String toString() {
        return "Alias[" + getOriginId() + " as " + getSymbolId() + "]";
    }
}
