package org.clyze.persistent.model;

import java.util.Map;

/**
 * A heap allocation site ("new").
 */
public class HeapAllocation extends SymbolWithDoopId {

	private String allocatedTypeDoopId;

	private String allocatingMethodDoopId;	

	/** If true, the allocation is inside an instance initializer block. */
	private boolean inIIB = false;

	/** If true, the allocation is an array type. */
	private boolean isArray = false;

    public HeapAllocation() {}

    public HeapAllocation(String id) {
        this.id = id;
    }
	
	public HeapAllocation(Position position, 
                          String sourceFileName, 
                          String doopId, 
                          String allocatedTypeDoopId, 
                          String allocatingMethodDoopId,
                          boolean inIIB, 
                          boolean isArray) {
		super(position, sourceFileName, doopId);		
		this.allocatedTypeDoopId = allocatedTypeDoopId;
		this.allocatingMethodDoopId = allocatingMethodDoopId;
		this.inIIB = inIIB;
		this.isArray = isArray;
	}

    public String getAllocatedTypeDoopId() {
        return allocatedTypeDoopId;
    }

    public void setAllocatedTypeDoopId(String allocatedTypeDoopId) {
        this.allocatedTypeDoopId = allocatedTypeDoopId;
    }

    public String getAllocatingMethodDoopId() {
        return allocatingMethodDoopId;
    }

    public void setAllocatingMethodDoopId(String allocatingMethodDoopId) {
        this.allocatingMethodDoopId = allocatingMethodDoopId;
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
		map.put("allocatedTypeDoopId", this.allocatedTypeDoopId);
		map.put("allocatingMethodDoopId", this.allocatingMethodDoopId);		
		map.put("inIIB", this.inIIB);
		map.put("isArray", this.isArray);
	}

	@Override
	public void fromMap(Map<String, Object> map){
		super.fromMap(map);
		this.allocatedTypeDoopId    = (String) map.get("allocatedTypeDoopId");
		this.allocatingMethodDoopId = (String) map.get("allocatingMethodDoopId");		
		this.inIIB                  = (Boolean) map.get("inIIB");
		this.isArray                = (Boolean) map.get("isArray");
	}
}
