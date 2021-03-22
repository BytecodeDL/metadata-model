package org.clyze.persistent.model.jvm;

import org.clyze.persistent.model.Position;
import org.clyze.persistent.model.SymbolWithId;

import java.util.Map;

/**
 * A method invocation site (in the body of another method).
 */
public class JvmMethodInvocation extends SymbolWithId {

    private String name;

    private String invokingMethodId;

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
                               String invokingMethodId, boolean inIIB) {
        super(position, sourceFileName, source, symbolId);
        this.name = name;
        this.invokingMethodId = invokingMethodId;
        this.inIIB = inIIB;
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

    public boolean isInIIB() {
        return inIIB;
    }

    public void setInIIB(boolean inIIB) {
        this.inIIB = inIIB;
    }

    protected void saveTo(Map<String, Object> map) {
        super.saveTo(map);
        map.put("name", getName());
        map.put("invokingMethodId", getInvokingMethodId());
        map.put("inIIB", isInIIB());
    }

    public void fromMap(Map<String, Object> map){
        super.fromMap(map);
        setName((String) map.get("name"));
        setInvokingMethodId((String) map.get("invokingMethodId"));
        setInIIB((Boolean) map.get("inIIB"));
    }
}
