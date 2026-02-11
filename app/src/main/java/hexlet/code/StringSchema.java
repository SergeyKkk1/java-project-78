package hexlet.code;

public class StringSchema {
    private boolean required = false;
    private int minLength = 0;
    private String mustContain = null;

    public StringSchema required() {
        this.required = true;
        return this;
    }

    public StringSchema contains(String value) {
        this.mustContain = value;
        return this;
    }

    public StringSchema minLength(int minLength) {
        if (minLength >= 0) {
            this.minLength = minLength;
        }
        return this;
    }

    public boolean isValid(String validatingString) {
        if (validatingString == null) {
            return !required;
        }

        if (required && validatingString.isEmpty()) {
            return false;
        }

        if (minLength > 0 && validatingString.length() < minLength) {
            return false;
        }

        return mustContain == null || validatingString.contains(mustContain);
    }
}
