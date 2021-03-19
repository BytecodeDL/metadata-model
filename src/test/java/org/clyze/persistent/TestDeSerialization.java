package org.clyze.persistent;

import java.io.File;
import java.io.IOException;
import java.util.*;
import org.clyze.persistent.metadata.*;
import org.clyze.persistent.metadata.jvm.JvmFileReporter;
import org.clyze.persistent.metadata.jvm.JvmMetadata;
import org.clyze.persistent.model.*;
import org.clyze.persistent.model.jvm.*;
import org.junit.jupiter.api.Test;

/**
 * Test metadata serialization and deserialization.
 */
public class TestDeSerialization {

    /**
     * Test JVM metadata.
     */
    @Test
    public void testJvmModel() {
        String sourceFileName = "sourceFileName.java";

        Position pos = new Position(0, 1, 2, 3);
        JvmClass jvmClass1 = new JvmClass(pos, sourceFileName, "name",
                "packageName", "symbolId", false, false, false, false, false,
                true, false, true, false, false);
        jvmClass1.setAnnotations(new HashSet<>(Arrays.asList("c-annotation1", "c-annotation2")));
        jvmClass1.setDeclaringSymbolId("declaring-symbol");
        Map<String, Object> map1 = jvmClass1.toMap();
        JvmClass jvmClass2 = new JvmClass();
        jvmClass2.fromMap(map1);

        assert jvmClass1.equals(jvmClass2);
        assert itemEquals(jvmClass1, jvmClass2);

        JvmField jvmField1 = new JvmField(pos, sourceFileName, "name",
                "symbolId", "type", "declaringClassId", true);
        jvmField1.setAnnotations(new HashSet<>(Arrays.asList("f-annotation1", "f-annotation2")));
        jvmField1.setDeclaringClassId("declaring-class");
        JvmField jvmField2 = new JvmField();
        jvmField2.fromMap(jvmField1.toMap());

        assert jvmField1.equals(jvmField2);
        assert itemEquals(jvmField1, jvmField2);

        JvmMethod jvmMethod1 = new JvmMethod(pos, sourceFileName, "name",
                "declaringClassId", "java.lang.String", "method-symbolId",
                new String[] { "param0", "param1" },
                new String[] { "int", "java.lang.Integer" }, false, false,
                false, false, false, false, true, false, true, false,
                new Position(1, 2, 3, 4));
        jvmMethod1.setAnnotations(new HashSet<>(Arrays.asList("m-annotation1", "m-annotation2")));
        jvmMethod1.setDeclaringClassId("declaring-class");
        JvmMethod jvmMethod2 = new JvmMethod();
        jvmMethod2.fromMap(jvmMethod1.toMap());

        assert jvmMethod1.equals(jvmMethod2);
        assert itemEquals(jvmMethod1, jvmMethod2);

        Usage class1Usage = new Usage(new Position(5, 5, 8, 9), sourceFileName, jvmClass1.getSymbolId(), UsageKind.TYPE);
        Usage class1Usage_ = new Usage();
        class1Usage_.fromMap(class1Usage.toMap());

        assert class1Usage.equals(class1Usage_);

        Usage field1ReadUsage = new Usage(new Position(5, 5, 1, 2), sourceFileName, jvmField2.getSymbolId(), UsageKind.DATA_READ);
        Usage field1ReadUsage_ = new Usage();
        field1ReadUsage_.fromMap(field1ReadUsage.toMap());

        assert field1ReadUsage.equals(field1ReadUsage_);

        Usage field1WriteUsage = new Usage(new Position(5, 5, 4, 5), sourceFileName, jvmField2.getSymbolId(), UsageKind.DATA_WRITE);
        Usage field1WriteUsage_ = new Usage();
        field1WriteUsage_.fromMap(field1WriteUsage.toMap());

        assert field1WriteUsage.equals(field1WriteUsage_);

        Usage method1Usage = new Usage(new Position(6, 6, 1, 2), sourceFileName, jvmMethod1.getSymbolId(), UsageKind.FUNCTION);
        Usage method1Usage_ = new Usage();
        method1Usage_.fromMap(method1Usage.toMap());

        assert method1Usage.equals(method1Usage_);

        Configuration configuration = new Configuration(new Printer(true));
        JvmMetadata metadata = new JvmMetadata();
        metadata.jvmClasses.add(jvmClass1);
        metadata.jvmFields.add(jvmField1);
        metadata.jvmMethods.add(jvmMethod2);
        metadata.usages.add(class1Usage);
        metadata.usages.add(field1ReadUsage);
        metadata.usages.add(field1WriteUsage);
        metadata.usages.add(method1Usage);
        FileInfo fileInfo = new FileInfo("package.name", "inputName", "input/file/path", "test source", metadata);
        JvmFileReporter reporter = new JvmFileReporter(configuration, fileInfo);
        String metadataPath = "build/test-jvm-metadata.json";
        try {
            Map<String, Object> map = serializeToJsonAndGetMap(reporter, metadataPath);
            List<Map<String, Object>> jvmClasses = (List<Map<String, Object>>) map.get(JvmClass.class.getSimpleName());
            assert jvmClasses != null;
            assert mapEquals(map1, jvmClasses.get(0));
            JvmMetadata deserializedMetadata = JvmMetadata.fromMap(map);
            assert deserializedMetadata.jvmClasses.size() == 1;
            assert deserializedMetadata.jvmFields.size() == 1;
            assert deserializedMetadata.jvmMethods.size() == 1;
            assert deserializedMetadata.jvmHeapAllocations.size() == 0;
            assert deserializedMetadata.jvmStringConstants.size() == 0;
            assert deserializedMetadata.usages.size() == 4;
            assert deserializedMetadata.jvmInvocations.size() == 0;
            assert deserializedMetadata.jvmVariables.size() == 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Test language-agnostic metadata.
     */
    @Test
    public void testLanguageAgnosticModel() {
        String sourceFileName = "sourceFileName.c";
        Position pos = new Position(0, 1, 2, 3);
        Type type1 = new Type(pos, sourceFileName, "unique-symbolId", "name");
        Type type2 = new Type();
        Map<String, Object> map1 = type1.toMap();
        type2.fromMap(map1);

        assert type1.equals(type2);
        assert itemEquals(type1, type2);

        Position fPos = new Position(12, 23, 13, 24);
        Function func1 = new Function(fPos, sourceFileName, "symbol-id", "func1", new String[] { "a", "b" }, fPos);
        Function func2 = new Function();
        func2.fromMap(func1.toMap());

        assert func1.equals(func2);
        assert itemEquals(func1, func2);

        SourceMetadata elements = new SourceMetadata();
        elements.types.add(type1);
        elements.functions.add(func1);
        SourceFileReporter reporter = new SourceFileReporter(getConfiguration(), elements);
        try {
            Map<String, Object> map = serializeToJsonAndGetMap(reporter, "build/test-metadata.json");
            List<Map<String, Object>> types = (List<Map<String, Object>>) map.get(Type.class.getSimpleName());
            assert types != null;
            assert mapEquals(map1, types.get(0));
            SourceMetadata sourceMetadata = SourceMetadata.fromMap(map);
            assert sourceMetadata.functions.size() == 1;
            assert sourceMetadata.types.size() == 1;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Serialize metadata to JSON, deserialize, and convert to Map.
     * @param reporter      the metadata reporter to generate the JSON
     * @param outPath       the output path
     * @return              the deserialized Map representation
     * @throws IOException  on deserialization error
     */
    private Map<String, Object> serializeToJsonAndGetMap(FileReporter reporter, String outPath) throws IOException {
        reporter.createReportFile(outPath);
        reporter.printReportStats();
        return JSONUtil.toMap((new File(outPath)).toPath());
    }

    /**
     * Get a basic configuration to use for testing.
     * @return   the configuration object to use during metadata generation
     */
    private Configuration getConfiguration() {
        return new Configuration(new Printer(true));
    }

    /**
     * An expensive way to compare items by comparing their serialized maps.
     * @param item1   the first item
     * @param item2   the second item
     * @return        true if both items are equal (as maps)
     */
    private boolean itemEquals(Item item1, Item item2) {
        Map<String, Object> map1 = item1.toMap();
        Map<String, Object> map2 = item2.toMap();
        return mapEquals(map1, map2);
    }

    /**
     * Test if a map has a key that a second map misses.
     * @param key     the key
     * @param map1    the first Map
     * @param map2    the second Map
     * @return        true on key mismatch
     */
    private boolean keyMismatchNotEqual(String key, Map<String, Object> map1, Map<String, Object> map2) {
        return map1.containsKey(key) && !map2.containsKey(key) && map1.get(key) != null;
    }

    /**
     * Test if two Map objects (representing JSON data) are equal.
     * @param map1  the first object to compare
     * @param map2  the second object to compare
     * @return      true if the objects are equal, false otherwise
     */
    @SuppressWarnings("unchecked")
    private boolean mapEquals(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> keys1 = map1.keySet();
        Set<String> keys2 = map2.keySet();
        Set<String> allKeys = new HashSet<>(keys1);
        allKeys.addAll(keys2);
        for (String key : allKeys)
            if (keyMismatchNotEqual(key, map1, map2) || keyMismatchNotEqual(key, map2, map1)) {
                System.err.println("ERROR: maps have different key '" + key + "': " + new TreeSet<>(keys1) + " vs. " + new TreeSet<>(keys2));
                return false;
            }
        boolean result = true;
        for (Map.Entry<String, Object> entry1 : map1.entrySet()) {
            String key = entry1.getKey();
            Object obj1 = entry1.getValue();
            Object obj2 = map2.get(key);
            if (obj1 == null && obj2 == null)
                continue;
            else if (obj1 != null && obj2 != null) {
                if (obj1 instanceof Map && obj2 instanceof Map) {
                    result &= mapEquals((Map<String, Object>) obj1, (Map<String, Object>) obj2);
                } else if (obj1 instanceof List && obj2 instanceof List) {
                    if (!listEquals((List<Object>)obj1, (List<Object>)obj2))
                        return false;
                } else if (!jsonValueEquals(obj1, obj2)) {
                    System.err.println("ERROR: maps are different at key '" + key +"': " + obj1 + ", " + obj2);
                    return false;
                }
            } else {
                System.err.println("ERROR: one of the maps is null: " + map1 + ", " + map2);
                return false;
            }
        }
        return result;
    }

    /**
     * Test list equality, taking into account Map representations of values.
     * @param list1  the first list to compare
     * @param list2  the second list to compare
     * @return       true if the lists are equal
     */
    @SuppressWarnings("unchecked")
    private boolean listEquals(List<Object> list1, List<Object> list2) {
        if (list1 == null && list2 == null)
            return true;
        if (list1 != null && list2 != null) {
            if (list1.size() != list2.size()) {
                System.err.println("ERROR: lists have different size: " + list1 + ", " + list2);
                return false;
            } else {
                for (int i = 0; i < list1.size(); i++) {
                    Object obj1 = list1.get(i);
                    Object obj2 = list2.get(i);
                    if (obj1 instanceof Map && obj2 instanceof Map) {
                        if (!mapEquals((Map<String, Object>) obj1, (Map<String, Object>) obj2))
                            return false;
                    } else if (obj1 instanceof List && obj2 instanceof List) {
                        if (!listEquals((List<Object>)obj1, (List<Object>)obj2))
                            return false;
                    } else if (!jsonValueEquals(obj1, obj2)) {
                        System.err.println("ERROR: lists are different at index " + i +": " + obj1 + ", " + obj2);
                        return false;
                    }
                }
                return true;
            }
        } else {
            System.err.println("ERROR: one of the lists is null: " + list1 + ", " + list2);
            return false;
        }
    }

    /**
     * Check two JSON values for equality. If the two values are numbers,
     * compare via conversion to double to address any implicit conversions.
     * Collections are compared by first converting them to sorted lists.
     * @param obj1    the first value
     * @param obj2    the second value
     * @return        true if the values are equal
     */
    private boolean jsonValueEquals(Object obj1, Object obj2) {
        if (obj1.equals(obj2))
            return true;
        if (obj1 instanceof Number && obj2 instanceof Number)
            return getDouble((Number)obj1) == getDouble((Number)obj2);
        if (obj1 instanceof Collection && obj2 instanceof Collection) {
            TreeSet<Object> set1 = new TreeSet<>((Collection<?>) obj1);
            TreeSet<Object> set2 = new TreeSet<>((Collection<?>) obj2);
            return listEquals(Arrays.asList(set1.toArray()), Arrays.asList(set2.toArray()));
        }
        return false;
    }

    /**
     * Convert numbers to double for maximum precision.
     * @param num   the number to convert
     * @return      the equivalent double
     */
    private double getDouble(Number num) {
        String str = num.toString();
        return Double.parseDouble(str);
    }
}
