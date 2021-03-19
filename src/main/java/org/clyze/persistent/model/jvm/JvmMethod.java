package org.clyze.persistent.model.jvm;

import java.util.Map;
import org.clyze.persistent.model.Function;
import org.clyze.persistent.model.Position;

/**
 * A class method.
 */
public class JvmMethod extends Function {
	/** The return type of the method. */
	private String returnType;
	/** The types of the method parameters. */
	private String[] paramTypes;
	/** True for static methods. */
	private boolean isStatic;
	/** True for methods declared in interfaces. */
	private boolean isInterface;
	/** True if this is an abstract method. */
	private boolean isAbstract;
	/** True for native methods. */
	private boolean isNative;
	/** True for synchronized methods. */
	private boolean isSynchronized;
	/** True for final methods. */
	private boolean isFinal;
	/** True for synthetic (compiled-generated) methods. */
	private boolean isSynthetic;
	/** True for public methods. */
	private boolean isPublic;
	/** True for protected methods. */
	private boolean isProtected;
	/** True for private methods. */
	private boolean isPrivate;
	/** The id of the class containing this method declaration. */
	private String declaringClassId;

	/** No-arg constructor, use setters or fromMap() to populate the object. */
	public JvmMethod() {}

	/**
	 * Single-arg constructor, use setters or fromMap() to populate the object.
	 * Used during deserialization.
	 * @param id      a unique deserialization id
	 */
	public JvmMethod(String id) {
		this.id = id;
	}

	public JvmMethod(Position position,
					 String sourceFileName,
					 String name,
					 String declaringClassId,
					 String returnType,
					 String symbolId,
					 String[] params,
					 String[] paramTypes,
					 boolean isStatic,
					 boolean isInterface,
					 boolean isAbstract,
					 boolean isNative,
					 boolean isSynchronized,
					 boolean isFinal,
					 boolean isSynthetic,
					 boolean isPublic,
					 boolean isProtected,
					 boolean isPrivate,
					 Position outerPosition) {
		super(position, sourceFileName, symbolId, name, params, outerPosition);
		this.declaringClassId = declaringClassId;
		this.returnType = returnType;		
		this.paramTypes = paramTypes;
		this.isStatic = isStatic;		
		this.isInterface = isInterface;
		this.isAbstract = isAbstract;
		this.isNative = isNative;
		this.isSynchronized = isSynchronized;
		this.isFinal = isFinal;
		this.isSynthetic = isSynthetic;
		this.isPublic = isPublic;
		this.isProtected = isProtected;
		this.isPrivate = isPrivate;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public String[] getParamTypes() {
		return paramTypes;
	}

	public void setParamTypes(String[] paramTypes) {
		this.paramTypes = paramTypes;
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean aStatic) {
		this.isStatic = aStatic;
	}

	public boolean isInterface() {
		return isInterface;
	}

	public void setInterface(boolean anInterface) {
		isInterface = anInterface;
	}

	public boolean isAbstract() {
		return isAbstract;
	}

	public void setAbstract(boolean anAbstract) {
		isAbstract = anAbstract;
	}

	public boolean isNative() {
		return isNative;
	}

	public void setNative(boolean aNative) {
		isNative = aNative;
	}

	public boolean isSynchronized() { return isSynchronized; }

	public void setSynchronized(boolean aSynchronized) { isSynchronized = aSynchronized; }

	public boolean isFinal() { return isFinal; }

	public void setFinal(boolean aFinal) { isFinal = aFinal; }

	public boolean isSynthetic() { return isSynthetic; }

	public void setSynthetic(boolean synthetic) { isSynthetic = synthetic;}

	public boolean isPublic() { return isPublic; }

	public void setPublic(boolean aPublic) {  isPublic = aPublic;  }

	public boolean isProtected() { return isProtected; }

	public void setProtected(boolean aProtected) {  isProtected = aProtected; }

	public boolean isPrivate() {  return isPrivate;  }

	public void setPrivate(boolean aPrivate) { isPrivate = aPrivate; }

	public String getDeclaringClassId() {
		return declaringClassId;
	}

	public void setDeclaringClassId(String declaringClassId) {
		this.declaringClassId = declaringClassId;
	}

	@Override
    protected void saveTo(Map<String, Object> map) {
		super.saveTo(map);
		map.put("returnType", getReturnType());
		map.put("paramTypes", arrayToList(getParamTypes()));
		map.put("isStatic", isStatic());
		map.put("isInterface", isInterface());
		map.put("isAbstract", isAbstract());
		map.put("isNative", isNative());
		map.put("isSynchronized", isSynchronized());
		map.put("isFinal", isFinal());
		map.put("isSynthetic", isSynthetic());
		map.put("isPublic", isPublic());
		map.put("isProtected", isProtected());
		map.put("isPrivate", isPrivate());
		map.put("declaringClassId", getDeclaringClassId());
	}

	@Override
	public void fromMap(Map<String, Object> map){
		super.fromMap(map);
		setReturnType((String) map.get("returnType"));
		setParamTypes(listToArray(map.get("paramTypes")));
		setStatic((Boolean) map.get("isStatic"));
		setInterface((Boolean) map.get("isInterface"));
		setAbstract((Boolean) map.get("isAbstract"));
		setNative((Boolean) map.get("isNative"));
		setFinal((Boolean) map.get("isFinal"));
		setSynthetic((Boolean) map.get("isSynthetic"));
		setSynchronized((Boolean) map.get("isSynchronized"));
		setPublic((Boolean) map.get("isPublic"));
		setProtected((Boolean) map.get("isProtected"));
		setPrivate((Boolean) map.get("isPrivate"));
		setDeclaringClassId((String) map.get("declaringClassId"));
	}
}
