package org.clyze.persistent.model.jvm;

import java.util.Map;
import java.util.Arrays;
import org.clyze.persistent.model.Function;
import org.clyze.persistent.model.Position;

/**
 * A class method.
 */
public class JvmMethod extends Function {

	private String returnType;
	private String[] paramTypes;
	private boolean isStatic;
	private boolean isInterface;
	private boolean isAbstract;
	private boolean isNative;
	private boolean isSynchronized;
	private boolean isFinal;
	private boolean isSynthetic;
	private boolean isPublic;
	private boolean isProtected;
	private boolean isPrivate;
	private String declaringClassId;

	public JvmMethod() {}

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
		isStatic = aStatic;
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
