package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class DifferTestYml {
    @Test
    public void testNestedStructureComparison() throws Exception {
        Map<String, Object> map1 = Map.of(
                "key", "value",
                "nested", Map.of("a", 1, "b", 2),
                "array", List.of(1, 2, 3)
        );

        Map<String, Object> map2 = Map.of(
                "key", "changed",
                "nested", Map.of("a", 1, "b", 3),
                "newKey", "value"
        );

        String result = Differ.generate(map1, map2, "stylish");

        assertTrue(result.contains("- key: value"));
        assertTrue(result.contains("+ key: changed"));
        assertTrue(result.contains("+ newKey: value"));
        assertTrue(result.contains("nested: {a=1, b=2}") || result.contains("nested: {b=2, a=1}"));
    }
}
