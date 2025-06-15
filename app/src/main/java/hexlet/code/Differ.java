package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    private static JsonFormatter Formatter;

    public static String generate(String filePath1, String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws IOException {
        // Чтение файлов
        String content1 = Files.readString(Paths.get(filePath1));
        String content2 = Files.readString(Paths.get(filePath2));

        // Определение форматов
        String format1 = getFileFormat(filePath1);
        String format2 = getFileFormat(filePath2);

        // Парсинг содержимого
        Map<String, Object> data1 = Parser.parse(content1, format1);
        Map<String, Object> data2 = Parser.parse(content2, format2);

        // Построение различий
        Map<String, DiffNode> diff = DiffBuilder.build(data1, data2);

        // Форматирование результата
        return Formatter.format(diff, format);
    }

    public static String generate(Map<String, Object> data1, Map<String, Object> data2, String format) throws IOException {
        Map<String, DiffNode> diff = DiffBuilder.build(data1, data2);
        return Formatter.format(diff, format);
    }

    private static String getFileFormat(String filePath) {
        if (filePath.endsWith(".json")) {
            return "json";
        } else if (filePath.endsWith(".yml") || filePath.endsWith(".yaml")) {
            return "yaml";
        }
        throw new IllegalArgumentException("Unsupported file format: " + filePath);
    }
}
