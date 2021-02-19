package org.clyze.persistent.model.jvm;

import org.clyze.persistent.model.AnnotatableSymbolWithId;
import org.clyze.persistent.model.Position;

import java.util.Map;

/**
 * A class field.
 */
public class JvmField extends AnnotatableSymbolWithId {

	private String name;

	private String type;

	private boolean isStatic;

	private String declaringClassId;

    /**
     * No-arg constructor, use setters or fromMap() to populate the object.
     */
    public JvmField() {}

    /**
     * Single-arg constructor, use setters or fromMap() to populate the object.
     * @param id      the unique symbol id
     */
    public JvmField(String id) {
        this.id = id;
    }

    /**
     * Normal object constructor.
     * @param position             the source position
     * @param sourceFileName       the source file
     * @param name                 the name of the field
     * @param symbolId             the unique symbol id
     * @param type                 the type of the field
     * @param declaringClassId     the symbol id of the enclosing type
     * @param isStatic             true if this is a static field
     */
	public JvmField(Position position,
                    String sourceFileName,
                    String name,
                    String symbolId,
                    String type,
                    String declaringClassId,
                    boolean isStatic) {
		super(position, sourceFileName, symbolId);
		this.name = name;		
		this.type = type;
		this.declaringClassId = declaringClassId;
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

    public String getDeclaringClassId() {
        return declaringClassId;
    }

    public void setDeclaringClassId(String declaringClassId) {
        this.declaringClassId = declaringClassId;
    }

    protected void saveTo(Map<String, Object> map) {
		super.saveTo(map);
		map.put("name", this.name);
		map.put("type", this.type);
		map.put("isStatic", this.isStatic);
		map.put("declaringClassId", this.declaringClassId);
	}

	public void fromMap(Map<String, Object> map){
		super.fromMap(map);
		this.name             = (String) map.get("name");
		this.type             = (String) map.get("type");
		this.isStatic         = (Boolean) map.get("isStatic");
		this.declaringClassId = (String) map.get("declaringClassId");
	}
}
