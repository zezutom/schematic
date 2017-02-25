package org.zezutom.schematic.model.json;

import org.junit.Before;
import org.junit.Test;
import org.zezutom.schematic.service.generator.ValueGenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class NodeTestCase<T, G extends ValueGenerator<T>, N extends Node<T, G>> {

    abstract N newInstance(String name, G generator);

    abstract Class<G> getGeneratorClass();

    abstract T getTestValue();

    private final String nodeName = "Test Node";

    N node;

    @Before
    public void before() {
        node = newInstance(nodeName, getGenerator());
    }

    @Test
    public void constructor() {
        assertNotNull(node);
        assertEquals(nodeName, node.getName());
        assertNotNull(node.getValueGenerator());
    }

    @Test
    public void getValue() {
        assertEquals(getTestValue(), node.getValue());
    }

    private G getGenerator() {
        G generator = mock(getGeneratorClass());
        when(generator.next()).thenReturn(getTestValue());
        return generator;
    }


}
