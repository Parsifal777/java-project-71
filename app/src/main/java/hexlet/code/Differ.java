package hexlet.code;

import hexlet.code.JsonFormatter;
import hexlet.code.PlainFormatter;
import hexlet.code.StylishFormatter;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.Objects;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> map1 = FileReader.readAndParse(filePath1);
        Map<String, Object> map2 = FileReader.readAndParse(filePath2);
        return generate(map1, map2, format);
    }

    public static String generate(Map<String, Object> map1, Map<String, Object> map2, String format) {
        Map<String, DiffNode> diff = generateDiff(map1, map2);

        switch (format) {
            case "json":
                return JsonFormatter.format(diff);
            case "plain":
                return PlainFormatter.format(diff);
            case "stylish":
                return StylishFormatter.format(diff);
            default:
                throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }

    private static Map<String, DiffNode> generateDiff(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> allKeys = new HashSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        Map<String, DiffNode> diff = new TreeMap<>();

        for (String key : allKeys) {
            if (!map2.containsKey(key)) {
                diff.put(key, new DiffNode(DiffStatus.REMOVED, map1.get(key), null));
            } else if (!map1.containsKey(key)) {
                diff.put(key, new DiffNode(DiffStatus.ADDED, null, map2.get(key)));
            } else if (!Objects.equals(map1.get(key), map2.get(key))) {
                diff.put(key, new DiffNode(DiffStatus.CHANGED, map1.get(key), map2.get(key)));
            } else {
                diff.put(key, new DiffNode(DiffStatus.UNCHANGED, map1.get(key), map2.get(key)));
            }
        }

        return diff;
    }
}
