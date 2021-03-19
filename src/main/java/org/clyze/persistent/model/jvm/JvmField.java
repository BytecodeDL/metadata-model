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

    /** No-arg constructor, use setters or fromMap() to populate the object. */
    public JvmField() {}

    /**
     * Single-arg constructor, use setters or fromMap() to populate the object.
     * Used during deserialization.
     * @param id      a unique deserialization id
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
		map.put("name", getName());
		map.put("type", getType());
		map.put("isStatic", isStatic());
		map.put("declaringClassId", getDeclaringClassId());
	}

	public void fromMap(Map<String, Object> map){
		super.fromMap(map);
		setName((String) map.get("name"));
		setType((String) map.get("type"));
		setStatic((Boolean) map.get("isStatic"));
		setDeclaringClassId((String) map.get("declaringClassId"));
	}
}
