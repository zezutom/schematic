package org.zezutom.schematic.model;

import org.junit.Test;
import org.zezutom.schematic.service.generator.value.StringGeneratorToDelete;
import org.zezutom.schematic.service.generator.value.ValueGenerator;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NodeTest {

    @Test
    public void constructor() {
        String expectedName = "test";
        LeafNode<String> node = new LeafNode<>(expectedName, mock(StringGeneratorToDelete.class));
        assertEquals(expectedName, node.getName());
    }

    @Test
    public void nonValueNode() {
//        assertFalse(new LeafNode<String>("test", null).hasValue());
    }

    @Test
    public void getValue() {
        String expectedValue = "test value";
        ValueGenerator<String> generator = mock(StringGeneratorToDelete.class);
        when(generator.next()).thenReturn(expectedValue);

        LeafNode<String> node = new LeafNode<>("test", generator);
        assertEquals(expectedValue, node.getValue());
    }
}
