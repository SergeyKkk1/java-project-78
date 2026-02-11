package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    private final Class<T> type;
    private boolean required;
    private final Map<String, Predicate<T>> checks = new LinkedHashMap<>();

    protected BaseSchema(Class<T> type) {
        this.type = type;
    }

    protected final void addCheck(String key, Predicate<T> predicate) {
        checks.put(key, predicate);
    }

    protected final void markRequired() {
        required = true;
    }

    public final boolean isValid(Object value) {
        if (value == null) {
            return !required;
        }

        if (!type.isInstance(value)) {
            return false;
        }

        T castedValue = type.cast(value);
        for (Predicate<T> check : checks.values()) {
            if (!check.test(castedValue)) {
                return false;
            }
        }

        return true;
    }
}
