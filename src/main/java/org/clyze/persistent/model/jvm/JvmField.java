package org.clyze.persistent.model.jvm;

import java.util.Map;
import java.util.Objects;

import org.clyze.persistent.model.Field;
import org.clyze.persistent.model.Position;

/**
 * A class field.
 */
public class JvmField extends Field {

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
     * @param source               true if the field appears in source code
     * @param name                 the name of the field
     * @param symbolId             the unique symbol id
     * @param type                 the type of the field
     * @param declaringClassId     the symbol id of the enclosing type
     * @param isStatic             true if this is a static field
     */
	public JvmField(Position position,
                    String sourceFileName,
                    boolean source,
                    String name,
                    String symbolId,
                    String type,
                    String declaringClassId,
                    boolean isStatic) {
		super(position, sourceFileName, source, name, symbolId);
		this.type = type;
		this.declaringClassId = declaringClassId;
		this.isStatic = isStatic;
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

    @Override
    protected void saveTo(Map<String, Object> map) {
		super.saveTo(map);
		map.put("type", getType());
		map.put("isStatic", isStatic());
		map.put("declaringClassId", getDeclaringClassId());
	}

	@Override
	public void fromMap(Map<String, Object> map){
		super.fromMap(map);
		setType((String) map.get("type"));
		setStatic((Boolean) map.get("isStatic"));
		setDeclaringClassId((String) map.get("declaringClassId"));
	}

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (!(that instanceof JvmField)) return false;
        JvmField field = (JvmField) that;
        return super.equals(field) && Objects.equals(type, field.type)  &&
                Objects.equals(isStatic, field.isStatic) &&
                Objects.equals(declaringClassId, field.declaringClassId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, isStatic, declaringClassId);
    }
}
