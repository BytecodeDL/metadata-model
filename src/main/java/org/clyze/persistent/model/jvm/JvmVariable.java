package org.clyze.persistent.model.jvm;

import java.util.Map;
import org.clyze.persistent.model.Position;
import org.clyze.persistent.model.Variable;

/**
 * This class models variables in JVM methods. These are either formal method
 * parameters or local variables. In bytecode, these "local variables" come
 * from appropriate analysis of bytecode (such as Soot and its Jimple IR). In
 * the Dalvik Executable format (dex), these "variables" come from the
 * "registers" in the IR.
 */
public class JvmVariable extends Variable {

	/** The type of the variable. */
	private String type;
	/** The id of the method declaring this variable. */
	private String declaringMethodId;
	/** True if this variable is inside an instance initializer block. */
	private boolean inIIB = false;

	/** No-arg constructor, use setters or fromMap() to populate the object. */
	public JvmVariable() {}

	/**
	 * Single-arg constructor, use setters or fromMap() to populate the object.
	 * Used during deserialization.
	 * @param id      a unique deserialization id
	 */
    public JvmVariable(String id) {
        this.id = id;
    }

	public JvmVariable(Position position,
					   String sourceFileName,
					   boolean source,
					   String artifactName,
					   String name,
					   String symbolId,
					   String type,
					   String declaringMethodId,
					   boolean isLocal,
					   boolean isParameter,
					   boolean inIIB) {
		super(position, sourceFileName, source, artifactName, symbolId, name, isLocal, isParameter);
		this.type = type;
		this.declaringMethodId = declaringMethodId;
		this.inIIB = inIIB;
	}


    public String getType() {
        return type;
    }

    public String getDeclaringMethodId() {
        return declaringMethodId;
    }

    public void setDeclaringMethodId(String declaringMethodId) {
        this.declaringMethodId = declaringMethodId;
    }

    public boolean isInIIB() {
        return inIIB;
    }

    public void setInIIB(boolean inIIB) {
        this.inIIB = inIIB;
    }

    @Override
    protected void saveTo(Map<String, Object> map) {
		super.saveTo(map);
		map.put("type", getType());
		map.put("declaringMethodId", getDeclaringMethodId());
		map.put("inIIB", isInIIB());
	}

	@Override
	public void fromMap(Map<String, Object> map){
		super.fromMap(map);
		setType((String) map.get("type"));
		setDeclaringMethodId((String) map.get("declaringMethodId"));
		setInIIB((Boolean) map.get("inIIB"));
	}

	public String getSymbolId() {
		return symbolId;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "VAR-[" + getType() + " " + getName() + "]" +
				"/isLocal=" + isLocal() +
				"/isParameter=" + isParameter() +
				"/symbolId=" + getSymbolId() +
				"@" + getSourceFileName();
	}
}
