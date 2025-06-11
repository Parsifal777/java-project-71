package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.IOException;
import java.util.Map;

public class Parser {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final ObjectMapper YAML_MAPPER = new ObjectMapper(new YAMLFactory());

    public static Map<String, Object> parse(String content, String format) throws IOException {
        return switch (format.toLowerCase()) {
            case "json" -> JSON_MAPPER.readValue(content, Map.class);
            case "yaml", "yml" -> YAML_MAPPER.readValue(content, Map.class);
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        };
    }
}