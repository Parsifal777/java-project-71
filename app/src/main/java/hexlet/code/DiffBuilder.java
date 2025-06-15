package hexlet.code;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

class DiffBuilder {
    public static Map<String, DiffNode> build(Map<String, Object> data1, Map<String, Object> data2) {
        Map<String, DiffNode> diff = new TreeMap<>();

        Map<String, Boolean> allKeys = new HashMap<>();
        data1.keySet().forEach(key -> allKeys.put(key, true));
        data2.keySet().forEach(key -> allKeys.put(key, true));

        for (String key : allKeys.keySet()) {
            if (!data1.containsKey(key)) {
                diff.put(key, new DiffNode(DiffStatus.ADDED, null, data2.get(key)));
            } else if (!data2.containsKey(key)) {
                diff.put(key, new DiffNode(DiffStatus.REMOVED, data1.get(key), null));
            } else if (Objects.equals(data1.get(key), data2.get(key))) {
                diff.put(key, new DiffNode(DiffStatus.UNCHANGED, data1.get(key), data1.get(key)));
            } else {
                diff.put(key, new DiffNode(DiffStatus.CHANGED, data1.get(key), data2.get(key)));
            }
        }

        return diff;
    }
}
