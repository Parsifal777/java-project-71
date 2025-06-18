package hexlet.code;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

class DiffBuilder {
    public static Map<String, DiffNode> build(Map<String, Object> data1, Map<String, Object> data2) {
        Objects.requireNonNull(data1, "First data map cannot be null");
        Objects.requireNonNull(data2, "Second data map cannot be null");

        Map<String, DiffNode> diff = new TreeMap<>();
        Map<String, Boolean> allKeys = new HashMap<>();

        data1.keySet().forEach(key -> allKeys.put(key, true));
        data2.keySet().forEach(key -> allKeys.put(key, true));

        for (String key : allKeys.keySet()) {
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);

            if (!data1.containsKey(key)) {
                diff.put(key, new DiffNode(DiffStatus.ADDED, null, value2));
            } else if (!data2.containsKey(key)) {
                diff.put(key, new DiffNode(DiffStatus.REMOVED, value1, null));
            } else if (Objects.equals(value1, value2)) {
                diff.put(key, new DiffNode(DiffStatus.UNCHANGED, value1, value1));
            } else {
                diff.put(key, new DiffNode(DiffStatus.CHANGED, value1, value2));
            }
        }

        return diff;
    }
}