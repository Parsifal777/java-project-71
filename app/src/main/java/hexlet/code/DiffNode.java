package hexlet.code;

public class DiffNode {
    private final DiffStatus status;
    private final Object oldValue;
    private final Object newValue;

    public DiffNode(DiffStatus status, Object oldValue, Object newValue) {
        this.status = status;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    // Getters
    public DiffStatus getStatus() { return status; }
    public Object getOldValue() { return oldValue; }
    public Object getNewValue() { return newValue; }
}