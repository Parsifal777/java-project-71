package hexlet.code;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlainFormatTest {
    @Test
    public void testPlainFormat() throws Exception {
        String filePath1 = "src/test/resources/file1.json";
        String filePath2 = "src/test/resources/file2.json";

        String expected = Files.readString(Paths.get("src/test/resources/expected_plain.txt")).trim();
        String actual = Differ.generate(filePath1, filePath2, "plain");

        assertEquals(expected, actual);
    }
}