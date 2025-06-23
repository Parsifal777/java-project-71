package hexlet.code;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class JsonFormatter {
    public static String format(Map<String, DiffNode> diff) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            // Создаем специальный сериализатор для DiffNode
            mapper.addMixIn(DiffNode.class, DiffNodeMixin.class);
            return mapper.writeValueAsString(diff);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error formatting to JSON", e);
        }
    }

    // Миксин для правильной сериализации DiffNode
    @JsonIgnoreProperties({"value"}) // Игнорируем стандартный getValue()
    private abstract static class DiffNodeMixin {
        @JsonProperty("status") abstract DiffStatus getStatus();
        @JsonProperty("oldValue") abstract Object getOldValue();
        @JsonProperty("newValue") abstract Object getNewValue();
    }
}