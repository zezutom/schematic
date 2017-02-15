package org.zezutom.schematic.service.generator.json;

import org.junit.Before;
import org.junit.Test;
import org.zezutom.schematic.model.json.schema.JsonStringFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class StringGeneratorTest {

    private static final String EXPECTED_DATE_FORMAT = "dd-mm-yyyy";

    private StringGenerator generator;

    @Before
    public void before() {
        generator = new StringGenerator();
    }

    @Test
    public void basic() {
        // Leave with default verification
        getValue();
    }

    @Test
    public void minLength() {
        int minLength = 10;
        generator.setMinLength(minLength);
        assertTrue(getValue().length() >= minLength);
    }

    @Test
    public void maxLength() {
        int maxLength = 10;
        generator.setMaxLength(maxLength);
        assertTrue(getValue().length() <= maxLength);
    }

    @Test
    public void pattern() {
        String pattern = "\\^test[-_][0-9]\\$";
        generator.setPattern(pattern);
        assertTrue(getValue().matches(pattern));
    }

    @Test
    public void dateTime() {
        generator.setFormat(JsonStringFormat.DATE_TIME);
        String value = getValue();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(EXPECTED_DATE_FORMAT);
            Date parsedDate = dateFormat.parse(value);
            assertEquals(value, dateFormat.format(parsedDate));
        } catch (ParseException e) {
            fail(String.format("Not a valid date!: '%s'", value));
        }
    }

    private String getValue() {
        String value = generator.next();
        assertNotNull(value);
        assertFalse(value.isEmpty());
        return value;
    }
}
