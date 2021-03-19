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

    /** No-arg constructor, use setters or fromMap() to populate the object. */
    public Function() { }

    /**
     * Single-arg constructor, use setters or fromMap() to populate the object.
     * Used during deserialization.
     * @param id      a unique deserialization id
     */
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
        map.put("name", getName());
        map.put("params", arrayToList(getParams()));
        putPosition(map, "outerPosition", getOuterPosition());
    }

    @Override
    public void fromMap(Map<String, Object> map) {
        super.fromMap(map);
        setName((String) map.get("name"));
        setParams(listToArray(map.get("params")));
        Position outerPosition = fromPositionMap(map, "outerPosition");
        if (outerPosition != null)
            setOuterPosition(outerPosition);
    }

    /**
     * Helper method to load string lists.
     * @param o   an object that should be a string list
     * @return    the string list or null if o is null
     */
    protected static String[] listToArray(Object o) {
        if (o == null) {
            return null;
        }

        @SuppressWarnings("unchecked") List<String> values = (List<String>) o;
        return values.toArray(new String[0]);
    }

    /**
     * Helper method to convert string arrays to lists.
     * @param array  the input string array
     * @return       the corresponding string list or null if the array is null
     */
    protected static List<String> arrayToList(String[] array) {
        return array == null ? null : Arrays.asList(array);
    }
}
