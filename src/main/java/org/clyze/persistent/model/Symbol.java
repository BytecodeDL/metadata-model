package org.clyze.persistent.model;

import java.util.Map;
import java.util.HashMap;
import java.util.Objects;

/**
 * A symbol element that exists in source code.
 */
public abstract class Symbol extends Element {

    /** The source code position. */
    private Position position;

    /** The source file name. */
    private String sourceFileName;

    /** No-arg constructor, use setters or fromMap() to populate the object. */
    public Symbol() {}

    /**
     * Create a symbol object.
     * @param position The symbol position in the source code
     * @param sourceFileName The source code file name
     */
    public Symbol(Position position, String sourceFileName) {
        this.position = position;
        this.sourceFileName = sourceFileName;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getSourceFileName() {
        return sourceFileName;
    }

    public void setSourceFileName(String sourceFileName) {
        this.sourceFileName = sourceFileName;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Symbol)) return false;
        Symbol symbol = (Symbol) object;

        return super.equals(symbol)
            && Objects.equals(sourceFileName, symbol.sourceFileName)
            && Objects.equals(position, symbol.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sourceFileName, position);
    }

    protected static void putPosition(Map<String, Object> map, String key, Position position) {
        if (position != null) {
            Map<String, Object> posMap = new HashMap<>();
            posMap.put("startLine", position.getStartLine());
            posMap.put("endLine", position.getEndLine());
            posMap.put("startColumn", position.getStartColumn());
            posMap.put("endColumn", position.getEndColumn());
            map.put(key, posMap);
        }
    }

    protected static Position fromPositionMap(Map<String, Object> map, String key) {
        @SuppressWarnings("unchecked") Map<String, Object> position = (Map<String, Object>)map.get(key);
        if (position == null)
            return null;
        return new Position(((Number) position.get("startLine")).longValue(),
            ((Number) position.get("endLine")).longValue(),
            ((Number) position.get("startColumn")).longValue(),
            ((Number) position.get("endColumn")).longValue()
        );
    }

    @Override
    protected void saveTo(Map<String, Object> map) {
        super.saveTo(map);
        putPosition(map, "position", position);
        map.put("sourceFileName", sourceFileName);
    }

    @Override
    public void fromMap(Map<String, Object> map){
        super.fromMap(map);
        Position position = fromPositionMap(map, "position");
        if (position != null)
            this.position = position;
        this.sourceFileName = (String) map.get("sourceFileName");
    }
}
