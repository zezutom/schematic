package org.zezutom.schematic.model;

import org.junit.Test;
import org.zezutom.schematic.service.generator.value.StringGenerator;
import org.zezutom.schematic.service.generator.value.ValueGenerator;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NodeTest {

    @Test
    public void constructor() {
        String expectedName = "test";
        Node<String> node = new Node<>(expectedName, mock(StringGenerator.class));
        assertEquals(expectedName, node.getName());
        assertTrue(node.hasValue());
    }

    @Test
    public void nonValueNode() {
        assertFalse(new Node<String>("test", null).hasValue());
    }

    @Test
    public void getValue() {
        String expectedValue = "test value";
        ValueGenerator<String> generator = mock(StringGenerator.class);
        when(generator.next()).thenReturn(expectedValue);

        Node<String> node = new Node<>("test", generator);
        assertEquals(expectedValue, node.getValue());
    }
}
