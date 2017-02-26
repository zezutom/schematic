package org.zezutom.schematic.web;

import org.junit.Before;
import org.junit.Test;
import org.zezutom.schematic.model.json.Node;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AppControllerTest {

    private AppController controller;

    private Node node;

    @Before
    public void before() {
        node = mock(Node.class);
        when(node.getValue()).thenReturn("test");
        controller = new AppController(node);
    }

    @Test
    public void item() {
        assertEquals(node.getValue(), controller.item());
    }

    @Test
    public void items() {
        int count = 10;
        List<Object> items = controller.items(count);

        assertNotNull(items);
        assertTrue(items
                .stream()
                .filter(item -> item.equals(node.getValue()))
                .collect(Collectors.toList())
                .size() == count);
    }

    @Test
    public void itemsOnCountNullDefaultValueIsUsed() {
        List<Object> items = controller.items(null);
        assertNotNull(items);
        assertTrue(items.size() == AppController.DEFAULT_COUNT);
    }
}
