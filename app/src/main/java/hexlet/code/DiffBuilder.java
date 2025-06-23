package hexlet.code;

import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;

public class DiffBuilder {
    public static Map<String, DiffNode> build(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, DiffNode> diff = new TreeMap<>();

        // Get all unique keys from both maps
        var allKeys = new ArrayList<String>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        for (String key : allKeys) {
            boolean inFirst = map1.containsKey(key);
            boolean inSecond = map2.containsKey(key);

            if (inFirst && inSecond) {
                Object value1 = map1.get(key);
                Object value2 = map2.get(key);

                if (isEqual(value1, value2)) {
                    diff.put(key, new DiffNode(DiffStatus.UNCHANGED, value1));
                } else {
                    diff.put(key, new DiffNode(DiffStatus.CHANGED, value1, value2));
                }
            } else if (inFirst) {
                diff.put(key, new DiffNode(DiffStatus.REMOVED, map1.get(key)));
            } else {
                diff.put(key, new DiffNode(DiffStatus.ADDED, map2.get(key)));
            }
        }

        return diff;
    }

    private static boolean isEqual(Object value1, Object value2) {
        if (value1 == null && value2 == null) {
            return true;
        }
        if (value1 == null || value2 == null) {
            return false;
        }
        return value1.equals(value2);
    }
}