package hexlet.code;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;

class DiffBuilder {
    public static Map<String, DiffNode> build(Map<String, Object> data1, Map<String, Object> data2) {
        Objects.requireNonNull(data1, "First data map cannot be null");
        Objects.requireNonNull(data2, "Second data map cannot be null");

        Map<String, DiffNode> diff = new TreeMap<>();
        Set<String> allKeys = new TreeSet<>();

        allKeys.addAll(data1.keySet());
        allKeys.addAll(data2.keySet());

        for (String key : allKeys) {
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);

            if (!data1.containsKey(key)) {
                diff.put(key, new DiffNode(DiffStatus.ADDED, null, value2));
            } else if (!data2.containsKey(key)) {
                diff.put(key, new DiffNode(DiffStatus.REMOVED, value1, null));
            } else if (deepEquals(value1, value2)) {
                diff.put(key, new DiffNode(DiffStatus.UNCHANGED, value1, value2));
            } else {
                diff.put(key, new DiffNode(DiffStatus.CHANGED, value1, value2));
            }
        }

        return diff;
    }

    private static boolean deepEquals(Object a, Object b) {
        if (a == b) return true;
        if (a == null || b == null) return false;
        return Objects.deepEquals(a, b);
    }
}
