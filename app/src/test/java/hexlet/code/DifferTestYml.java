package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DifferTestYml {
    private static final int TEST_NUMBER_A = 1;
    private static final int TEST_NUMBER_B_ORIGINAL = 2;
    private static final int TEST_NUMBER_B_CHANGED = 3;
    private static final int TEST_ARRAY_ITEM_1 = 1;
    private static final int TEST_ARRAY_ITEM_2 = 2;
    private static final int TEST_ARRAY_ITEM_3 = 3;

    @Test
    public void testNestedStructureComparison() throws Exception {
        Map<String, Object> map1 = Map.of(
                "key", "value",
                "nested", Map.of("a", TEST_NUMBER_A, "b", TEST_NUMBER_B_ORIGINAL),
                "array", List.of(TEST_ARRAY_ITEM_1, TEST_ARRAY_ITEM_2, TEST_ARRAY_ITEM_3)
        );

        Map<String, Object> map2 = Map.of(
                "key", "changed",
                "nested", Map.of("a", TEST_NUMBER_A, "b", TEST_NUMBER_B_CHANGED),
                "newKey", "value"
        );

        String result = Differ.generate(map1, map2, "stylish");

        assertTrue(result.contains("- key: value"));
        assertTrue(result.contains("+ key: changed"));
        assertTrue(result.contains("+ newKey: value"));
        assertTrue(result.contains("nested: {a=1, b=2}") || result.contains("nested: {b=2, a=1}"));
    }
}
