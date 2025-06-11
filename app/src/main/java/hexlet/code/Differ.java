package hexlet.code;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.HashMap;

public class Differ {
    private static JsonFormatter Formatter;

    public static String generate(String filePath1, String filePath2, String format) throws IOException {
        // Чтение файлов
        String content1 = FileUtils.readFile(filePath1);
        String content2 = FileUtils.readFile(filePath2);

        // Определение форматов
        String format1 = FileUtils.getFormat(filePath1);
        String format2 = FileUtils.getFormat(filePath2);

        // Парсинг содержимого
        Map<String, Object> data1 = Parser.parse(content1, format1);
        Map<String, Object> data2 = Parser.parse(content2, format2);

        // Построение различий
        Map<String, DiffNode> diff = DiffBuilder.build(data1, data2);

        // Форматирование результата
        return Formatter.format(diff, format);
    }
}

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