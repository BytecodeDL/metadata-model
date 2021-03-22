package org.clyze.persistent.model;

import java.util.Map;
import java.util.Objects;

/** A usage of an existing element. */
public class Usage extends SymbolWithId {
    /** The kind of this usage. */
	private UsageKind usageKind;

    /** No-arg constructor, use setters or fromMap() to populate the object. */
    public Usage() {}

    /**
     * Single-arg constructor, use setters or fromMap() to populate the object.
     * Used during deserialization.
     * @param id      a unique deserialization id
     */
    public Usage(String id) {
        this.id = id;
    }

	public Usage(Position position, String sourceFileName, boolean source,
                 String symbolId, UsageKind usageKind) {
		super(position, sourceFileName, source, symbolId);
		this.usageKind = usageKind;
	}

    public UsageKind getUsageKind() {
        return usageKind;
    }

    public void setUsageKind(UsageKind usageKind) {
        this.usageKind = usageKind;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Usage)) return false;
        Usage usage = (Usage) object;

        return super.equals(object)
            && Objects.equals(usageKind, usage.usageKind);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), usageKind);
    }

    @Override
    protected void saveTo(Map<String, Object> map) {
		super.saveTo(map);
		map.put("usageKind", getUsageKind().name());
	}

	@Override
	public void fromMap(Map<String, Object> map){
		super.fromMap(map);
		setUsageKind(UsageKind.valueOf((String)map.get("usageKind")));
	}

    @Override
    public String toString() {
        return "Usage[" + usageKind + "]: " + symbolId + "@" + getSourceFileName() + "/" + getPosition();
    }
}
