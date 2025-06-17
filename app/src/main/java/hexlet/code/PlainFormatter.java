package hexlet.code;

import java.util.Map;

public class PlainFormatter {
    public static String format(Map<String, DiffNode> diff) {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, DiffNode> entry : diff.entrySet()) {
            String key = entry.getKey();
            DiffNode node = entry.getValue();

            switch (node.getStatus()) {
                case ADDED:
                    result.append(String.format("Property '%s' was added with value: %s\n",
                            key, formatValue(node.getNewValue())));
                    break;
                case REMOVED:
                    result.append(String.format("Property '%s' was removed\n", key));
                    break;
                case CHANGED:
                    result.append(String.format("Property '%s' was updated. From %s to %s\n",
                            key, formatValue(node.getOldValue()), formatValue(node.getNewValue())));
                    break;
                case UNCHANGED:
                    break;
                default:
                    throw new RuntimeException("Unknown status: " + node.getStatus());
            }
        }

        return result.toString().trim();
    }

    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof Map || value instanceof Iterable) {
            return "[complex value]";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        return value.toString();
    }
}
