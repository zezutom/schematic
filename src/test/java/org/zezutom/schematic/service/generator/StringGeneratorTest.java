package org.zezutom.schematic.service.generator;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class StringGeneratorTest extends EnumValueGeneratorTest<String, StringGeneratorToDelete> {

    @Override
    StringGeneratorToDelete getInstance() {
        return new StringGeneratorToDelete();
    }

    @Override
    List<String> getChoices() {
        return Arrays.asList("one", "two", "three");
    }

    @Test
    public void valueConformsToRegex() {
        String pattern = "[0-9]+";
        generator.addValue(pattern, true);
        assertTrue(generator.next().matches(pattern));
    }
}
