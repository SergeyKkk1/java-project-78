package hexlet.code;

public class NumberSchema extends BaseSchema<Number> {

    public NumberSchema() {
        super(Number.class);
    }

    public NumberSchema required() {
        markRequired();
        return this;
    }

    public NumberSchema positive() {
        addCheck("positive", value -> value.doubleValue() > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        double lowerBound = Math.min(min, max);
        double upperBound = Math.max(min, max);
        addCheck("range", value -> {
            double number = value.doubleValue();
            return number >= lowerBound && number <= upperBound;
        });
        return this;
    }
}
