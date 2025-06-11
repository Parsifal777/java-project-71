package hexlet.code;

public class DiffNode {
    private DiffStatus status = null;;
    private final Object oldValue;
    private final Object newValue;
    private final Object value;

    public DiffNode(DiffStatus added, String type, Object value) {
        this.status = status;
        this.value = value;
        this.oldValue = null;
        this.newValue = null;
    }

    public DiffNode(DiffStatus status, Object oldValue, Object newValue) {
        this.status = status;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.value = null;
    }

    // Getters
    public DiffStatus getStatus() {
        return status;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public Object getValue() {
        return value;
    }
}