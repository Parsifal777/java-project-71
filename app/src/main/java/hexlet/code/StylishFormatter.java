package hexlet.code;

import java.util.Map;

public class StylishFormatter {
    public static String format(Map<String, DiffNode> diff) {
        StringBuilder result = new StringBuilder("{\n");

        for (Map.Entry<String, DiffNode> entry : diff.entrySet()) {
            String key = entry.getKey();
            DiffNode node = entry.getValue();

            switch (node.getStatus()) {
                case ADDED:
                    result.append(formatLine("+ ", key, node.getNewValue()));
                    break;
                case REMOVED:
                    result.append(formatLine("- ", key, node.getOldValue()));
                    break;
                case CHANGED:
                    result.append(formatLine("- ", key, node.getOldValue()));
                    result.append(formatLine("+ ", key, node.getNewValue()));
                    break;
                case UNCHANGED:
                    result.append(formatLine("  ", key, node.getOldValue()));
                    break;
            }
        }

        result.append("}");
        return result.toString();
    }

    private static String formatLine(String prefix, String key, Object value) {
        return String.format("  %s%s: %s\n", prefix, key,
                value instanceof Map || value instanceof Iterable ? value : String.valueOf(value));
    }
}