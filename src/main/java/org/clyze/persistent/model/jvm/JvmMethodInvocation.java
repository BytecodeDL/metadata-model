package org.clyze.persistent.model.jvm;

import org.clyze.persistent.model.Position;
import org.clyze.persistent.model.SymbolWithId;

import java.util.Map;

/**
 * A method invocation site (in the body of another method).
 */
public class JvmMethodInvocation extends SymbolWithId {
    /** The name of the invoked method. */
    private String name;
    /** The target type from where method look-up will start. */
    public String targetType;
    /** The static signature of the invoked method: return type. */
    private String targetReturnType;
    /** The static signature of the invoked method: parameter types. */
    private String targetParamTypes;
    /** The method containing this invocation site. */
    private String invokingMethodId;
    /**
     * The symbol id of the target method. Computing this field requires
     * some form of static analysis.
     */
    public String targetMethodId = null;

    /** If true, this is inside an instance initializer block. */
    private boolean inIIB = false;

    /** No-arg constructor, use setters or fromMap() to populate the object. */
    public JvmMethodInvocation() {}

    /**
     * Single-arg constructor, use setters or fromMap() to populate the object.
     * Used during deserialization.
     * @param id      a unique deserialization id
     */
    public JvmMethodInvocation(String id) {
        this.id = id;
    }

    public JvmMethodInvocation(Position position, String sourceFileName,
                               boolean source, String name, String symbolId,
                               String targetType, String targetReturnType,
                               String targetParamTypes, String invokingMethodId,
                               boolean inIIB) {
        super(position, sourceFileName, source, symbolId);
        this.name = name;
        this.invokingMethodId = invokingMethodId;
        this.inIIB = inIIB;
        this.targetType = targetType;
        this.targetReturnType = targetReturnType;
        this.targetParamTypes = targetParamTypes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInvokingMethodId() {
        return invokingMethodId;
    }

    public void setInvokingMethodId(String invokingMethodId) {
        this.invokingMethodId = invokingMethodId;
    }

    public String getTargetType() {
        return this.targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getTargetReturnType() {
        return this.targetReturnType;
    }

    public void setTargetReturnType(String targetReturnType) {
        this.targetReturnType = targetReturnType;
    }

    public String getTargetParamTypes() {
        return this.targetParamTypes;
    }

    public void setTargetParamTypes(String targetParamTypes) {
        this.targetParamTypes = targetParamTypes;
    }

    public String getTargetMethodId() {
        return this.targetMethodId;
    }

    public void setTargetMethodId(String targetMethodId) {
        this.targetMethodId = targetMethodId;
    }

    public boolean isInIIB() {
        return inIIB;
    }

    public void setInIIB(boolean inIIB) {
        this.inIIB = inIIB;
    }

    protected void saveTo(Map<String, Object> map) {
        super.saveTo(map);
        map.put("name", getName());
        map.put("targetType", getTargetType());
        map.put("targetReturnType", getTargetReturnType());
        map.put("targetParamTypes", getTargetParamTypes());
        map.put("targetMethodId", getTargetMethodId());
        map.put("invokingMethodId", getInvokingMethodId());
        map.put("inIIB", isInIIB());
    }

    public void fromMap(Map<String, Object> map){
        super.fromMap(map);
        setName((String) map.get("name"));
        setTargetType((String) map.get("targetType"));
        setTargetReturnType((String) map.get("targetReturnType"));
        setTargetParamTypes((String) map.get("targetParamTypes"));
        setTargetMethodId((String) map.get("targetMethodId"));
        setInvokingMethodId((String) map.get("invokingMethodId"));
        setInIIB((Boolean) map.get("inIIB"));
    }
}
