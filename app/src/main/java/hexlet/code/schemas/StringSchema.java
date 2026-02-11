package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

    public StringSchema() {
        super(String.class);
    }

    public StringSchema required() {
        markRequired();
        addCheck("notEmpty", value -> !value.isEmpty());
        return this;
    }

    public StringSchema contains(String value) {
        if (value == null) {
            addCheck("contains", str -> true);
            return this;
        }

        addCheck("contains", str -> str.contains(value));
        return this;
    }

    public StringSchema minLength(int minLength) {
        if (minLength < 0) {
            return this;
        }

        addCheck("minLength", value -> value.length() >= minLength);
        return this;
    }
}
