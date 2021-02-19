package org.clyze.persistent.model;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;

/**
 * A symbol that has a unique id (for the whole program).
 */
public abstract class SymbolWithId extends Symbol implements Comparable<SymbolWithId> {

    protected String symbolId;

    public SymbolWithId() {}

    public SymbolWithId(Position position, String sourceFileName, String symbolId) {
        super(position, sourceFileName);
        this.symbolId = symbolId;
    }

    public String getSymbolId() {
        return symbolId;
    }

    public void setSymbolId(String symbolId) {
        this.symbolId = symbolId;
    }

    @Override
    public boolean equals(Object object) {
        if ((!(object instanceof SymbolWithId)))
            return false;
        SymbolWithId that = (SymbolWithId) object;
        return super.equals(object) && Objects.equals(this.symbolId, that.symbolId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), symbolId);
    }

    @Override
    public int compareTo(SymbolWithId that) {
        return Comparator.comparing(SymbolWithId::getSymbolId).compare(this, that);
    }

    @Override
    protected void saveTo(Map<String, Object> map) {
        super.saveTo(map);
        map.put("symbolId", this.symbolId);
    }

    @Override
    public void fromMap(Map<String, Object> map){
        super.fromMap(map);
        this.symbolId = (String) map.get("symbolId");
    }
}
