package org.clyze.persistent.model.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.clyze.persistent.model.Position;
import org.clyze.persistent.model.Type;

/**
 * Symbol used for classes, interfaces, and enum types.
 */
public class JvmClass extends Type {

	private String packageName;

	/**
	 * Various flags determining the symbol type
	 * (possibly change this to bitmap)
	 */
	private boolean isInterface;
	private boolean isEnum;
	private boolean isStatic;
	private boolean isInner;
	private boolean isAnonymous;
	private boolean isAbstract;
	private boolean isFinal;
	private boolean isPublic;
	private boolean isProtected;
	private boolean isPrivate;

	/**
	 * The id of the type or method where this type is declared.
	 */
	private String declaringSymbolId;

	private long sizeInBytes;

	/**
	 * A list of fully qualified type names (classes, interfaces) that
	 * this type (class, enum or interface) extends or implements.
	 */
	private List<String> superTypes = new ArrayList<>();

	/** No-arg constructor, use setters or fromMap() to populate the object. */
	public JvmClass() {}

	/**
	 * Single-arg constructor, use setters or fromMap() to populate the object.
	 * Used during deserialization.
	 * @param id      a unique deserialization id
	 */
	public JvmClass(String id) {
		this.id = id;
	}
	
	public JvmClass(Position position,
					String sourceFileName,
					boolean source,
					String artifactName,
					String name,
					String packageName,
					String symbolId,
					boolean isInterface,
					boolean isEnum,
					boolean isStatic,
					boolean isInner,
					boolean isAnonymous,
					boolean isAbstract,
					boolean isFinal,
					boolean isPublic,
					boolean isProtected,
					boolean isPrivate) {
		super(position, sourceFileName, source, artifactName, symbolId, name);
		this.packageName = packageName;
		this.isInterface = isInterface;
		this.isEnum = isEnum;
		this.isStatic = isStatic;
		this.isInner = isInner;
		this.isAnonymous = isAnonymous;
		this.isAbstract = isAbstract;
		this.isFinal = isFinal;
		this.isPublic = isPublic;
		this.isProtected = isProtected;
		this.isPrivate = isPrivate;
	}	

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}	

	public boolean isInterface() {
		return isInterface;
	}

	public void setInterface(boolean anInterface) {
		isInterface = anInterface;
	}

	public boolean isEnum() {
		return isEnum;
	}

	public void setEnum(boolean anEnum) {
		isEnum = anEnum;
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean aStatic) {
		isStatic = aStatic;
	}

	public boolean isInner() {
		return isInner;
	}

	public void setInner(boolean inner) {
		isInner = inner;
	}

	public boolean isAnonymous() {
		return isAnonymous;
	}

	public void setAnonymous(boolean anonymous) {
		isAnonymous = anonymous;
	}

	public boolean isAbstract() {
		return isAbstract;
	}

	public void setAbstract(boolean anAbstract) {
		isAbstract = anAbstract;
	}

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean aFinal) {
		isFinal = aFinal;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean aPublic) {
		isPublic = aPublic;
	}

	public boolean isProtected() {
		return isProtected;
	}

	public void setProtected(boolean aProtected) {
		isProtected = aProtected;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean aPrivate) {
		isPrivate = aPrivate;
	}

	public String getDeclaringSymbolId() {
		return declaringSymbolId;
	}

	public void setDeclaringSymbolId(String declaringSymbolId) {
		this.declaringSymbolId = declaringSymbolId;
	}

	public long getSizeInBytes() {
		return sizeInBytes;
	}

	public void setSizeInBytes(long sizeInBytes) {
		this.sizeInBytes = sizeInBytes;
	}

	public List<String> getSuperTypes() {
		return superTypes;
	}

	public void setSuperTypes(List<String> superTypes) {
		this.superTypes = superTypes;
	}

	@Override
	protected void saveTo(Map<String, Object> map) {
		super.saveTo(map);
		map.put("packageName", getPackageName());
		map.put("isInterface", isInterface());
		map.put("isEnum", isEnum());
		map.put("isStatic", isStatic());
		map.put("isInner", isInner());
		map.put("isAnonymous", isAnonymous());
		map.put("isAbstract", isAbstract());
		map.put("isFinal", isFinal());
		map.put("isPublic", isPublic());
		map.put("isProtected", isProtected());
		map.put("isPrivate", isPrivate());
		map.put("declaringSymbolId", getDeclaringSymbolId());
		map.put("sizeInBytes", getSizeInBytes());
		map.put("superTypes", getSuperTypes());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void fromMap(Map<String, Object> map){
		super.fromMap(map);
		setPackageName((String) map.get("packageName"));
		setInterface((Boolean) map.get("isInterface"));
		setEnum((Boolean) map.get("isEnum"));
		setStatic((Boolean) map.get("isStatic"));
		setInner((Boolean) map.get("isInner"));
		setAnonymous((Boolean) map.get("isAnonymous"));
		setAbstract((Boolean) map.get("isAbstract"));
		setFinal((Boolean) map.get("isFinal"));
		setPublic((Boolean) map.get("isPublic"));
		setProtected((Boolean) map.get("isProtected"));
		setPrivate((Boolean) map.get("isPrivate"));
		setDeclaringSymbolId((String) map.get("declaringSymbolId"));
		setSizeInBytes(((Number) map.get("sizeInBytes")).longValue());
		setSuperTypes((List<String>) map.get("superTypes"));
	}	
}
