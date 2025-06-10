package hexlet.code;

import hexlet.code.DiffNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class JsonFormatter {
    public static String format(Map<String, DiffNode> diff) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(diff);
        } catch (Exception e) {
            throw new RuntimeException("Error formatting to JSON", e);
        }
    }
}
