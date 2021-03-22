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

    /**
     * If true, this symbol appears in the sources. If false, this is a
     * compiler-generated/synthetic element or it appears in binary dependencies.
     */
    private boolean source;

    /** No-arg constructor, use setters or fromMap() to populate the object. */
    public Symbol() {}

    /**
     * Create a symbol object.
     * @param position        the symbol position in the source code
     * @param sourceFileName  the source code file name
     * @param source          if false, the symbol is compiler-generated or external/binary
     */
    public Symbol(Position position, String sourceFileName, boolean source) {
        this.position = position;
        this.sourceFileName = sourceFileName;
        this.source = source;
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

    public boolean isSource() {
        return source;
    }

    public void setSource(boolean source) {
        this.source = source;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Symbol)) return false;
        Symbol symbol = (Symbol) object;

        return super.equals(symbol)
            && Objects.equals(sourceFileName, symbol.sourceFileName)
            && Objects.equals(position, symbol.position)
            && Objects.equals(source, symbol.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sourceFileName, position, source);
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
        map.put("source", isSource());
    }

    @Override
    public void fromMap(Map<String, Object> map){
        super.fromMap(map);
        Position position = fromPositionMap(map, "position");
        if (position != null)
            setPosition(position);
        setSourceFileName((String) map.get("sourceFileName"));
        setSource((boolean) map.get("source"));
    }
}
