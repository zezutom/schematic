package org.zezutom.schematic.service.generator.json;

import org.junit.Test;
import org.zezutom.schematic.TestUtil;
import org.zezutom.schematic.model.json.schema.JsonStringFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.junit.Assert.*;

public class StringGeneratorTest extends ValueGeneratorTestCase<String, StringGenerator> {

    private static final String EXPECTED_DATE_FORMAT = "dd-mm-yyyy";

    @Override
    StringGenerator newInstance() {
        return new StringGenerator(TestUtil.mockParserFactory());
    }

    @Override
    public void before() {
        super.before();
        StringGenerator.clearPreLoadedValues();
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

    @Test
    public void supportsEmail() {
        assertSupportedFormat(JsonStringFormat.EMAIL);
    }

    @Test
    public void supportsHostname() {
        assertSupportedFormat(JsonStringFormat.HOSTNAME);
    }

    @Test
    public void supportsURI() {
        assertSupportedFormat(JsonStringFormat.URI);
    }

    @Test
    public void supportsIPv4() {
        assertSupportedFormat(JsonStringFormat.IPV4);
    }

    @Test
    public void supportsIPv6() {
        assertSupportedFormat(JsonStringFormat.IPV6);
    }

    @Test
    public void setFormatOnNullStringInputIsNotCalled() {
        final String nullStringFormat = null;
        generator.setFormat(nullStringFormat);
        assertNull(generator.getFormat());
    }

    @Test
    public void setFormatOnEmptyStringInputIsNotCalled() {
        generator.setFormat("");
        assertNull(generator.getFormat());
    }

    @Test
    public void hasPropertyOnNullInputReturnsFalse() {
        assertFalse(generator.hasProperty(null));
    }

    @Test
    public void hasPropertyOnEmptyInputReturnsFalse() {
        assertFalse(generator.hasProperty(""));
    }

    @Test
    public void preLoad() {
        StringGenerator.preLoad(10, Arrays.asList(JsonStringFormat.values()));
        for (JsonStringFormat format : JsonStringFormat.values()) {
            assertNotNull(StringGenerator.getValueMap().get(format));
        }
    }

    @Test
    public void preLoadOnZeroVolumeHasNoEffect() {
        StringGenerator.preLoad(0, Collections.singletonList(JsonStringFormat.EMAIL));
        assertTrue(StringGenerator.getValueMap().isEmpty());
    }

    @Test
    public void preLoadOnNegativeVolumeHasNoEffect() {
        StringGenerator.preLoad(-1, Collections.singletonList(JsonStringFormat.EMAIL));
        assertTrue(StringGenerator.getValueMap().isEmpty());
    }

    @Test
    public void preLoadOnNullFormatListHasNoEffect() {
        StringGenerator.preLoad(10, null);
        assertTrue(StringGenerator.getValueMap().isEmpty());
    }

    @Test
    public void preLoadOnZeroVolumeAndNullFormatListHasNoEffect() {
        StringGenerator.preLoad(0, null);
        assertTrue(StringGenerator.getValueMap().isEmpty());
    }

    @Test
    public void preLoadOnNegativeVolumeAndNullFormatListHasNoEffect() {
        StringGenerator.preLoad(-1, null);
        assertTrue(StringGenerator.getValueMap().isEmpty());
    }

    @Test
    public void preLoadOnNullFormatHasNoEffect() {
        StringGenerator.preLoad(10, Collections.singletonList(null));
        assertTrue(StringGenerator.getValueMap().isEmpty());
    }

    @Test
    public void nextWithPreLoadedValues() {
        JsonStringFormat preLoadedFormat = JsonStringFormat.EMAIL;
        StringGenerator.preLoad(10, Collections.singletonList(preLoadedFormat));
        generator.setFormat(preLoadedFormat);
        assertNotNull(getValue());
    }

    private void assertSupportedFormat(JsonStringFormat format) {
        generator.setFormat(format);
        assertNotNull(getValue());
    }
}
