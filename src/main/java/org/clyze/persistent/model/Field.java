package org.clyze.persistent.model;

import java.util.Map;

/**
 * A class field.
 */
public class Field extends AnnotateableSymbolWithDoopId {

	private String name;

	private String type;

	private boolean isStatic;

	private String declaringClassDoopId;	

    /**
     * No-arg constructor, use setters or fromMap() to populate the object.
     */
    public Field() {}

    /**
     * Single-arg constructor, use setters or fromMap() to populate the object.
     * @param id      the Doop id
     */
    public Field(String id) {
        this.id = id;
    }

    /**
     * Normal object constructor.
     * @param position             the source position
     * @param sourceFileName       the source file
     * @param name                 the name of the field
     * @param doopId               the Doop id
     * @param type                 the type of the field
     * @param declaringClassDoopId the Doop id of the enclosing type
     * @param isStatic             true if this is a static field
     */
	public Field(Position position, 
                 String sourceFileName, 
                 String name, 
                 String doopId, 
                 String type, 
                 String declaringClassDoopId,
                 boolean isStatic) {
		super(position, sourceFileName, doopId);
		this.name = name;		
		this.type = type;
		this.declaringClassDoopId = declaringClassDoopId;
		this.isStatic = isStatic;
	}    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }

    public String getDeclaringClassDoopId() {
        return declaringClassDoopId;
    }

    public void setDeclaringClassDoopId(String declaringClassDoopId) {
        this.declaringClassDoopId = declaringClassDoopId;
    }

    protected void saveTo(Map<String, Object> map) {
		super.saveTo(map);
		map.put("name", this.name);
		map.put("type", this.type);
		map.put("isStatic", this.isStatic);
		map.put("declaringClassDoopId", this.declaringClassDoopId);		
	}

	public void fromMap(Map<String, Object> map){
		super.fromMap(map);
		this.name                 = (String) map.get("name");
		this.type                 = (String) map.get("type");
		this.isStatic             = (Boolean) map.get("isStatic");
		this.declaringClassDoopId = (String) map.get("declaringClassDoopId");		
	}
}
