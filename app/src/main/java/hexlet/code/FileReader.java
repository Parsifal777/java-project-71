package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class FileReader {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final ObjectMapper YAML_MAPPER = new ObjectMapper(new YAMLFactory());

    public static Map<String, Object> readAndParse(String filePath) throws IOException {
        String content = Files.readString(Paths.get(filePath));

        // Detect file format by extension
        if (filePath.toLowerCase().endsWith(".json")) {
            return JSON_MAPPER.readValue(content, Map.class);
        } else if (filePath.toLowerCase().endsWith(".yml") || filePath.toLowerCase().endsWith(".yaml")) {
            return YAML_MAPPER.readValue(content, Map.class);
        } else {
            // Try both formats if extension is unknown
            try {
                return JSON_MAPPER.readValue(content, Map.class);
            } catch (Exception e) {
                return YAML_MAPPER.readValue(content, Map.class);
            }
        }
    }
}
