package hexlet.code;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest2 {
    private static final String RESOURCES_DIR = "src/test/resources/";

    @Test
    public void testNestedJsonDiff() throws Exception {
        String filePath1 = getResourcePath("file3.json");
        String filePath2 = getResourcePath("file4.json");
        String expected = readFixture("expected_nested_diff.txt");

        String actual = Differ.generate(filePath1, filePath2);
        assertEquals(expected.trim(), actual.trim());
    }

    @Test
    public void testNestedYamlDiff() throws Exception {
        String filePath1 = getResourcePath("filepath1.yml");
        String filePath2 = getResourcePath("filepath2.yml");
        String expected = readFixture("expected_nested_diff.txt");

        String actual = Differ.generate(filePath1, filePath2);
        assertEquals(expected.trim(), actual.trim());
    }

    private String getResourcePath(String fileName) {
        return Paths.get(RESOURCES_DIR + fileName).toString();
    }

    private String readFixture(String fileName) throws Exception {
        return Files.readString(Paths.get(RESOURCES_DIR + fileName));
    }
}
