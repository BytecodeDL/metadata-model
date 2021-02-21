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
		map.put("returnType", this.returnType);
		map.put("paramTypes", this.paramTypes == null ? null : Arrays.asList(this.paramTypes));
		map.put("isStatic", this.isStatic);
		map.put("isInterface", this.isInterface);
		map.put("isAbstract", this.isAbstract);
		map.put("isNative", this.isNative);
		map.put("isSynchronized", this.isSynchronized);
		map.put("isFinal", this.isFinal);
		map.put("isSynthetic", this.isSynthetic);
		map.put("isPublic", this.isPublic);
		map.put("isProtected", this.isProtected);
		map.put("isPrivate", this.isPrivate);
		map.put("declaringClassId", this.declaringClassId);
	}

	@Override
	public void fromMap(Map<String, Object> map){
		super.fromMap(map);
		this.returnType           = (String) map.get("returnType");
		this.paramTypes           = loadArray(map.get("paramTypes"));
		this.isStatic             = (Boolean) map.get("isStatic");
		this.isInterface          = (Boolean) map.get("isInterface");
		this.isAbstract           = (Boolean) map.get("isAbstract");
		this.isNative             = (Boolean) map.get("isNative");
		this.isFinal              = (Boolean) map.get("isFinal");
		this.isSynthetic          = (Boolean) map.get("isSynthetic");
		this.isPublic             = (Boolean) map.get("isPublic");
		this.isProtected          = (Boolean) map.get("isProtected");
		this.isPrivate            = (Boolean) map.get("isPrivate");
		this.declaringClassId = (String) map.get("declaringClassId");
	}
}
