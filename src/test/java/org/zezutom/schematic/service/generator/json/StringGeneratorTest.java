package org.zezutom.schematic.service.generator.json;

import org.junit.Test;
import org.zezutom.schematic.TestUtil;
import org.zezutom.schematic.model.json.schema.JsonStringFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class StringGeneratorTest extends ValueGeneratorTestCase<String, StringGenerator> {

    private static final String EXPECTED_DATE_FORMAT = "dd-mm-yyyy";

    @Override
    StringGenerator newInstance() {
        return new StringGenerator(TestUtil.mockParserFactory());
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
}
