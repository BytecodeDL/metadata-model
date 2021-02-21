package org.clyze.persistent.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * A source-level function (or: "method", "procedure", "lambda").
 */
public class Function extends AnnotatableSymbolWithId {
    /** The function name. */
    private String name;
    /** The parameter names. */
    private String[] params;
    /**
     * The place where the method definition begins (including any annotations and modifiers) and ends (i.e. right after
     * the closing brace). If the starting or ending line contain nothing else except the method definition and whitespaces,
     * then the corresponding column is set to zero
     *
     * Note: Differs from the "position" field inherited from "Symbol" in that the latter refers to the starting and
     *       ending positions of the method name inside its definition
     */
    private Position outerPosition;

    public Function() { }

    public Function(String id) {
        this.id = id;
    }

    public Function(Position position, String sourceFileName, String symbolId,
                    String name, String[] params, Position outerPosition) {
        super(position, sourceFileName, symbolId);
        this.name = name;
        this.params = params;
        this.outerPosition = outerPosition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }

    public Position getOuterPosition() {
        return outerPosition;
    }

    public void setOuterPosition(Position outerPosition) {
        this.outerPosition = outerPosition;
    }

    @Override
    protected void saveTo(Map<String, Object> map) {
        super.saveTo(map);
        map.put("name", this.name);
        map.put("params", this.params == null ? null : Arrays.asList(this.params));
        putPosition(map, "outerPosition", outerPosition);
    }

    @Override
    public void fromMap(Map<String, Object> map) {
        super.fromMap(map);
        this.name = (String) map.get("name");
        this.params = loadArray(map.get("params"));
        Position outerPosition = fromPositionMap(map, "outerPosition");
        if (outerPosition != null)
            this.outerPosition = outerPosition;
    }

    protected static String[] loadArray(Object o) {
        if (o == null) {
            return null;
        }

        List<String> values = (List<String>) o;
        return values.toArray(new String[0]);
    }
}
