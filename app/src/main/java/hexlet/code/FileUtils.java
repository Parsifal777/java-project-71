package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
    public static String readFile(String filePath) throws IOException {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new IOException("File not found: " + path);
        }
        return Files.readString(path);
    }

    public static String getFormat(String filePath) {
        if (filePath.toLowerCase().endsWith(".json")) {
            return "json";
        } else if (filePath.toLowerCase().endsWith(".yml") || filePath.toLowerCase().endsWith(".yaml")) {
            return "yaml";
        }
        throw new IllegalArgumentException("Unknown file format: " + filePath);
    }
}