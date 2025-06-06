package hexlet.code;

import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.HashSet;

public class Differ {
    public static String generate(Map<String, Object> data1, Map<String, Object> data2, String format) {
        switch (format) {
            case "plain":
                return generatePlain(data1, data2);
            case "json":
                return generateJson(data1, data2);
            case "stylish":
            default:
                return generateStylish(data1, data2);
        }
    }

    private static String generateStylish(Map<String, Object> data1, Map<String, Object> data2) {
        StringBuilder result = new StringBuilder("{\n");
        Set<String> allKeys = new HashSet<>(data1.keySet());
        allKeys.addAll(data2.keySet());

        for (String key : new TreeMap<>(data1).keySet()) {
            if (!data2.containsKey(key)) {
                result.append("  - ").append(key).append(": ").append(data1.get(key)).append("\n");
            } else if (!data1.get(key).equals(data2.get(key))) {
                result.append("  - ").append(key).append(": ").append(data1.get(key)).append("\n");
                result.append("  + ").append(key).append(": ").append(data2.get(key)).append("\n");
            } else {
                result.append("    ").append(key).append(": ").append(data1.get(key)).append("\n");
            }
        }

        for (String key : new TreeMap<>(data2).keySet()) {
            if (!data1.containsKey(key)) {
                result.append("  + ").append(key).append(": ").append(data2.get(key)).append("\n");
            }
        }

        result.append("}");
        return result.toString();
    }

    private static String generatePlain(Map<String, Object> data1, Map<String, Object> data2) {
        StringBuilder result = new StringBuilder();
        return result.toString();
    }

    private static String generateJson(Map<String, Object> data1, Map<String, Object> data2) {
        StringBuilder result = new StringBuilder();
        return result.toString();
    }
}