package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws IOException {
        Objects.requireNonNull(filePath1);
        Objects.requireNonNull(filePath2);

        String content1 = Files.readString(Path.of(filePath1));
        String content2 = Files.readString(Path.of(filePath2));

        String format1 = getFileFormat(filePath1);
        String format2 = getFileFormat(filePath2);

        Map<String, Object> data1 = Parser.parse(content1, format1);
        Map<String, Object> data2 = Parser.parse(content2, format2);

        return generate(data1, data2, format);
    }

    public static String generate(Map<String, Object> data1, Map<String, Object> data2) {
        return generate(data1, data2, "stylish");
    }

    public static String generate(Map<String, Object> data1, Map<String, Object> data2, String format) {
        Objects.requireNonNull(data1);
        Objects.requireNonNull(data2);
        Objects.requireNonNull(format);

        Map<String, DiffNode> diff = DiffBuilder.build(data1, data2);

        switch (format.toLowerCase()) {
            case "stylish":
                return StylishFormatter.format(diff);
            case "plain":
                return PlainFormatter.format(diff);
            case "json":
                return JsonFormatter.format(diff);
            default:
                throw new IllegalArgumentException("Unsupported output format: " + format);
        }
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
