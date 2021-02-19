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

    public JvmMethodInvocation() {}

    public JvmMethodInvocation(String id) {
        this.id = id;
    }

    public JvmMethodInvocation(Position position,
                               String sourceFileName,
                               String name,
                               String symbolId,
                               String invokingMethodId,
                               boolean inIIB) {
        super(position, sourceFileName, symbolId);
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
        map.put("name", this.name);
        map.put("invokingMethodId", this.invokingMethodId);
        map.put("inIIB", this.inIIB);
    }

    public void fromMap(Map<String, Object> map){
        super.fromMap(map);
        this.name                 = (String) map.get("name");
        this.invokingMethodId     = (String) map.get("invokingMethodId");
        this.inIIB                = (Boolean) map.get("inIIB");
    }
}
