package hexlet.code;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {

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
}
