package org.clyze.persistent.model.jvm;

import org.clyze.persistent.model.Position;
import org.clyze.persistent.model.SymbolWithId;

import java.util.Map;

/**
 * A heap allocation site ("new").
 */
public class JvmHeapAllocation extends SymbolWithId {
    /** The type of the allocated object. */
    private String allocatedTypeId;
    /** The id of the method containing this allocation site. */
    private String allocatingMethodId;
    /** If true, the allocation is inside an instance initializer block. */
    private boolean inIIB = false;
    /** If true, the allocation is an array type. */
    private boolean isArray = false;

    /** No-arg constructor, use setters or fromMap() to populate the object. */
    public JvmHeapAllocation() {}

    /**
     * Single-arg constructor, use setters or fromMap() to populate the object.
     * Used during deserialization.
     * @param id      a unique deserialization id
     */
    public JvmHeapAllocation(String id) {
        this.id = id;
    }

    public JvmHeapAllocation(Position position, String sourceFileName,
                             boolean source, String artifactName, String symbolId,
                             String allocatedTypeId, String allocatingMethodId,
                             boolean inIIB, boolean isArray) {
        super(position, sourceFileName, source, artifactName, symbolId);
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
        map.put("allocatedTypeId", getAllocatedTypeId());
        map.put("allocatingMethodId", getAllocatingMethodId());
        map.put("inIIB", isInIIB());
        map.put("isArray", isArray());
    }

    @Override
    public void fromMap(Map<String, Object> map){
        super.fromMap(map);
        setAllocatedTypeId((String) map.get("allocatedTypeId"));
        setAllocatingMethodId((String) map.get("allocatingMethodId"));
        setInIIB((Boolean) map.get("inIIB"));
        setArray((Boolean) map.get("isArray"));
    }
}
