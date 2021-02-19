package org.clyze.persistent.model.jvm;

import org.clyze.persistent.model.Position;
import org.clyze.persistent.model.SymbolWithId;

import java.util.Map;

/**
 * A heap allocation site ("new").
 */
public class JvmHeapAllocation extends SymbolWithId {

    private String allocatedTypeId;

    private String allocatingMethodId;

    /** If true, the allocation is inside an instance initializer block. */
    private boolean inIIB = false;

    /** If true, the allocation is an array type. */
    private boolean isArray = false;

    public JvmHeapAllocation() {}

    public JvmHeapAllocation(String id) {
        this.id = id;
    }

    public JvmHeapAllocation(Position position,
                             String sourceFileName,
                             String symbolId,
                             String allocatedTypeId,
                             String allocatingMethodId,
                             boolean inIIB,
                             boolean isArray) {
        super(position, sourceFileName, symbolId);
        this.allocatedTypeId = allocatedTypeId;
        this.allocatingMethodId = allocatingMethodId;
        this.inIIB = inIIB;
        this.isArray = isArray;
    }

    public String getAllocatedTypeId() {
        return allocatedTypeId;
    }

    public void setAllocatedTypeId(String allocatedTypeId) {
        this.allocatedTypeId = allocatedTypeId;
    }

    public String getAllocatingMethodId() {
        return allocatingMethodId;
    }

    public void setAllocatingMethodId(String allocatingMethodId) {
        this.allocatingMethodId = allocatingMethodId;
    }

    public boolean isInIIB() {
        return inIIB;
    }

    public void setInIIB(boolean inIIB) {
        this.inIIB = inIIB;
    }

    public boolean isArray() {
        return isArray;
    }

    public void setArray(boolean array) {
        isArray = array;
    }

    @Override
    protected void saveTo(Map<String, Object> map) {
        super.saveTo(map);
        map.put("allocatedTypeId", this.allocatedTypeId);
        map.put("allocatingMethodId", this.allocatingMethodId);
        map.put("inIIB", this.inIIB);
        map.put("isArray", this.isArray);
    }

    @Override
    public void fromMap(Map<String, Object> map){
        super.fromMap(map);
        this.allocatedTypeId    = (String) map.get("allocatedTypeId");
        this.allocatingMethodId = (String) map.get("allocatingMethodId");
        this.inIIB              = (Boolean) map.get("inIIB");
        this.isArray            = (Boolean) map.get("isArray");
    }
}
