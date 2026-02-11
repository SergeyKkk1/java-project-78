package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema() {
        super(Map.class);
    }

    public MapSchema required() {
        markRequired();
        return this;
    }

    public MapSchema sizeof(int size) {
        if (size < 0) {
            return this;
        }

        addCheck("sizeof", value -> value.size() == size);
        return this;
    }

    public MapSchema shape(Map<?, ? extends BaseSchema<?>> keyToValidator) {
        if (keyToValidator == null || keyToValidator.isEmpty()) {
            return this;
        }

        addCheck("shape", map -> keyToValidator.entrySet().stream()
                .allMatch(entry -> entry.getValue().isValid(map.get(entry.getKey()))));
        return this;
    }
}
