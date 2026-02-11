package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberSchemaTest {
    private static final int LOWER_BOUND = 5;
    private static final int UPPER_BOUND = 10;
    private static final int NEGATIVE_NUMBER = -10;
    private static final int ZERO = 0;
    private static final int OUT_OF_RANGE_LOW = 4;
    private static final int OUT_OF_RANGE_HIGH = 11;

    @Test
    void testNumberSchemaFlow() {
        Validator validator = new Validator();
        NumberSchema schema = validator.number();

        assertTrue(schema.isValid(LOWER_BOUND));
        assertTrue(schema.isValid(null));
        assertTrue(schema.positive().isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(UPPER_BOUND));
        assertFalse(schema.isValid(NEGATIVE_NUMBER));
        assertFalse(schema.isValid(ZERO));

        schema.range(LOWER_BOUND, UPPER_BOUND);

        assertTrue(schema.isValid(LOWER_BOUND));
        assertTrue(schema.isValid(UPPER_BOUND));
        assertFalse(schema.isValid(OUT_OF_RANGE_LOW));
        assertFalse(schema.isValid(OUT_OF_RANGE_HIGH));
    }
}
