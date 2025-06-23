package hexlet.code;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class DiffNode {
    private final DiffStatus status;
    private final Object oldValue;
    private final Object newValue;

    public DiffNode(DiffStatus status, Object oldValue, Object newValue) {
        this.status = status;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public DiffNode(DiffStatus status, Object value) {
        this(status, value, value);
    }

    public DiffStatus getStatus() {
        return status;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    @JsonIgnore
    public Object getValue() {
        return switch (status) {
            case ADDED -> newValue;
            case REMOVED -> oldValue;
            case UNCHANGED -> oldValue;
            case CHANGED -> throw new IllegalStateException("Для CHANGED используйте getOldValue()/getNewValue()");
        };
    }

    public boolean is(DiffStatus testStatus) {
        return this.status == testStatus;
    }
}