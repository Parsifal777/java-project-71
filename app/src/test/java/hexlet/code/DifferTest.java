package hexlet.code;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final ObjectMapper YAML_MAPPER = new YAMLMapper();
    private static final String RESOURCES_DIR = "src/test/resources/";

    @Test
    public void testJsonCompareStylish() throws Exception {
        Map<String, Object> map1 = readJson("file1.json");
        Map<String, Object> map2 = readJson("file2.json");
        String expected = readFixture("expected_stylish.txt");

        assertEquals(expected, Differ.generate(map1, map2, "stylish"));
    }

    @Test
    public void testJsonComparePlain() throws Exception {
        Map<String, Object> map1 = readJson("file1.json");
        Map<String, Object> map2 = readJson("file2.json");
        String expected = readFixture("expected_plain.txt");

        assertEquals(expected, Differ.generate(map1, map2, "plain"));
    }

    @Test
    public void testJsonCompareJson() throws Exception {
        Map<String, Object> map1 = readJson("file1.json");
        Map<String, Object> map2 = readJson("file2.json");
        String expected = readFixture("expected_json.txt");

        assertEquals(expected, Differ.generate(map1, map2, "json"));
    }

    @Test
    public void testJsonCompareDefault() throws Exception {
        Map<String, Object> map1 = readJson("file1.json");
        Map<String, Object> map2 = readJson("file2.json");
        String expected = readFixture("expected_stylish.txt");

        assertEquals(expected, Differ.generate(map1, map2));
    }

    @Test
    public void testYamlCompareStylish() throws Exception {
        Map<String, Object> map1 = readYaml("filepath1.yml");
        Map<String, Object> map2 = readYaml("filepath2.yml");
        String expected = readFixture("expected_stylish.txt");

        assertEquals(expected, Differ.generate(map1, map2, "stylish"));
    }

    @Test
    public void testYamlComparePlain() throws Exception {
        Map<String, Object> map1 = readYaml("filepath1.yml");
        Map<String, Object> map2 = readYaml("filepath2.yml");
        String expected = readFixture("expected_plain.txt");

        assertEquals(expected, Differ.generate(map1, map2, "plain"));
    }

    @Test
    public void testYamlCompareJson() throws Exception {
        Map<String, Object> map1 = readYaml("filepath1.yml");
        Map<String, Object> map2 = readYaml("filepath2.yml");
        String expected = readFixture("expected_json.txt");

        assertEquals(expected, Differ.generate(map1, map2, "json"));
    }

    @Test
    public void testYamlCompareDefault() throws Exception {
        Map<String, Object> map1 = readYaml("filepath1.yml");
        Map<String, Object> map2 = readYaml("filepath2.yml");
        String expected = readFixture("expected_stylish.txt");

        assertEquals(expected, Differ.generate(map1, map2));
    }

    private Map<String, Object> readJson(String fileName) throws Exception {
        String content = Files.readString(Paths.get(RESOURCES_DIR + fileName));
        return JSON_MAPPER.readValue(content, Map.class);
    }

    private Map<String, Object> readYaml(String fileName) throws Exception {
        String content = Files.readString(Paths.get(RESOURCES_DIR + fileName));
        return YAML_MAPPER.readValue(content, Map.class);
    }

    private String readFixture(String fileName) throws Exception {
        return Files.readString(Paths.get(RESOURCES_DIR + fileName)).trim();
    }
}