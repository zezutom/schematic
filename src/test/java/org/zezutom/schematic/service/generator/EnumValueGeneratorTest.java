package org.zezutom.schematic.service.generator;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public abstract class EnumValueGeneratorTest<T, G extends EnumValueGenerator<T>> {

    G generator;

    abstract G getInstance();

    abstract List<T> getChoices();

    @Before
    public void before() {
        generator = getInstance();
    }

    @Test
    public void defaultValueExistsAndIsUnique() {
        assertNotNull(generator.next());
        assertNotEquals(generator.next(), generator.next());
    }

    @Test
    public void valueIsConstrainedByListOfChoices() {
        List<T> choices = getChoices();
        choices.forEach(generator::addValue);
        assertTrue(choices.contains(generator.next()));
    }
}
