package hexlet.code;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Test
    public void testJsonCompare() throws Exception {
        Map<String, Object> map1 = readJson("file1.json");
        Map<String, Object> map2 = readJson("file2.json");

        String expected = """
            {
              - follow: false
                host: hexlet.io
              - proxy: 123.234.53.22
              - timeout: 50
              + timeout: 20
              + verbose: true
            }
            """.trim();

        assertEquals(expected, Differ.generate(map1, map2, "stylish"));
    }

    private Map<String, Object> readJson(String fileName) throws Exception {
        String content = Files.readString(Paths.get("src", "test", "resources", fileName));
        return OBJECT_MAPPER.readValue(content, Map.class);
    }
}
