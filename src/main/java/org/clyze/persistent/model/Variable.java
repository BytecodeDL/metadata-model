package org.clyze.persistent.model;

import java.util.Map;

/**
 * A variable.
 */
public class Variable extends SymbolWithId {

    /**
     * The variable name.
     */
    private String name;
    /**
     * If true, this is a local variable.
     */
    private boolean isLocal;
    /**
     * If true, this is a function/method parameter.
     */
    private boolean isParameter;

    /**
     * No-arg constructor, use setters or fromMap() to populate the object.
     */
    public Variable() {
    }

    /**
     * Single-arg constructor, use setters or fromMap() to populate the object.
     * Used during deserialization.
     *
     * @param id a unique deserialization id
     */
    public Variable(String id) {
        this.id = id;
    }

    public Variable(Position position, String sourceFileName, boolean source,
                    String artifactName, String symbolId, String name, boolean isLocal, boolean isParameter) {
        super(position, sourceFileName, source, artifactName, symbolId);
        this.name = name;
        this.isLocal = isLocal;
        this.isParameter = isParameter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLocal() {
        return isLocal;
    }

    public void setLocal(boolean local) {
        isLocal = local;
    }

    public boolean isParameter() {
        return isParameter;
    }

    public void setParameter(boolean parameter) {
        isParameter = parameter;
    }

    @Override
    protected void saveTo(Map<String, Object> map) {
        super.saveTo(map);
        map.put("name", getName());
        map.put("isLocal", isLocal());
        map.put("isParameter", isParameter());
    }

    @Override
    public void fromMap(Map<String, Object> map){
        super.fromMap(map);
        setName((String) map.get("name"));
        setLocal((Boolean) map.get("isLocal"));
        setParameter((Boolean) map.get("isParameter"));
    }

    @Override
    public String toString() {
        return "VAR-[" + getName() + "]" +
                "/isLocal=" + isLocal() +
                "/isParameter=" + isParameter() +
                "/symbolId=" + getSymbolId() +
                "@" + getSourceFileName();
    }
}