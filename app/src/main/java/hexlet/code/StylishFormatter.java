package hexlet.code;

import java.util.Map;

public class StylishFormatter {
    public static String format(Map<String, DiffNode> diff) {
        StringBuilder result = new StringBuilder("{\n");

        for (Map.Entry<String, DiffNode> entry : diff.entrySet()) {
            String key = entry.getKey();
            DiffNode node = entry.getValue();
            DiffStatus status = node.getStatus();

            if (status == null) {
                throw new IllegalStateException("DiffNode status cannot be null for key: " + key);
            }

            switch (status) {
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
                default:
                    throw new IllegalStateException("Unknown status: " + status);
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