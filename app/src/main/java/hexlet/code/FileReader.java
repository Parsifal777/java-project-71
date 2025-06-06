package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class FileReader {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static Map<String, Object> readAndParse(String filePath) throws IOException {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new IOException("File not found: " + path);
        }

        String content = Files.readString(path);

        return OBJECT_MAPPER.readValue(content,
                new com.fasterxml.jackson.core.type.TypeReference<Map<String, Object>>() {});
    }
}