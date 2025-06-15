package hexlet.code;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final String RESOURCES_DIR = "src/test/resources/";

    @Test
    public void testJsonCompare() throws Exception {
        Map<String, Object> map1 = readJson("file1.json");
        Map<String, Object> map2 = readJson("file2.json");
        String expected = readFixture("expected_stylish.txt");

        assertEquals(expected, Differ.generate(map1, map2, "stylish"));
    }

    private Map<String, Object> readJson(String fileName) throws Exception {
        String content = Files.readString(Paths.get(RESOURCES_DIR + fileName));
        return OBJECT_MAPPER.readValue(content, Map.class);
    }

    private String readFixture(String fileName) throws Exception {
        return Files.readString(Paths.get(RESOURCES_DIR + fileName)).trim();
    }
}
