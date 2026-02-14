package hexlet.code;


import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringSchemaTest {
    private static final int MIN_LENGTH = 4;
    private static final int MIN_LENGTH_MEDIUM = 5;
    private static final int MIN_LENGTH_LONG = 10;

    @Test
    void testStringSchemaDefault() {
        Validator validator = new Validator();
        StringSchema schema = validator.string();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid("hexlet"));
    }

    @Test
    void testStringSchemaRequired() {
        Validator validator = new Validator();
        StringSchema schema = validator.string().required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("a"));
    }

    @Test
    void testStringSchemaMinLength() {
        Validator validator = new Validator();
        StringSchema schema = validator.string().minLength(MIN_LENGTH);

        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid("hey"));
        assertTrue(schema.isValid("heyy"));
    }

    @Test
    void testStringSchemaContains() {
        Validator validator = new Validator();
        StringSchema schema = validator.string().contains("ex");

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid("hexlet"));
        assertFalse(schema.isValid("hey"));
    }

    @Test
    void testStringSchemaCombined() {
        Validator validator = new Validator();
        StringSchema schema = validator.string()
            .required()
            .minLength(MIN_LENGTH_LONG)
            .minLength(MIN_LENGTH_MEDIUM)
            .contains("let");

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid("hexet"));
        assertFalse(schema.isValid("hex"));
        assertTrue(schema.isValid("hexlet"));
    }
}
