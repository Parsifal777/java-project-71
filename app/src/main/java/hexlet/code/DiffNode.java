package hexlet.code;

public class DiffNode {
    private final DiffStatus status;
    private final Object oldValue;
    private final Object newValue;

    // Основной конструктор для всех случаев
    public DiffNode(DiffStatus status, Object oldValue, Object newValue) {
        this.status = status;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    // Упрощенный конструктор для ADDED/REMOVED/UNCHANGED
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

    // Универсальный метод получения значения
    public Object getValue() {
        return switch (status) {
            case ADDED -> newValue;
            case REMOVED -> oldValue;
            case UNCHANGED -> oldValue;  // или newValue - они равны
            case CHANGED -> throw new IllegalStateException("Для CHANGED используйте getOldValue()/getNewValue()");
        };
    }

    // Вспомогательный метод для проверки статуса
    public boolean is(DiffStatus status) {
        return this.status == status;
    }
}
