package org.zezutom.schematic.service.generator.model;

import org.junit.Before;
import org.junit.Test;
import org.zezutom.schematic.model.Node;
import org.zezutom.schematic.model.Tree;
import org.zezutom.schematic.service.generator.value.StringGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TreeModelGeneratorTest {

    private static final List<String> valueNodeNames = Arrays.asList("a", "b", "c");

    private static final List<String> nonValueNodeNames = Arrays.asList("d", "e", "f");

    private TreeModelGenerator generator;

    @Before
    public void before() {
        Tree tree = new Tree();
        valueNodeNames.forEach(name -> tree.addNode(new Node<>(name, new StringGenerator())));
        nonValueNodeNames.forEach(name -> tree.addNode(new Node<>(name, null)));
        generator = new TreeModelGenerator(tree);
    }

    @Test
    public void modelIsNotNull() {
        assertNotNull(generator.next());
    }

    @Test
    public void populatesAllValueNodes() {
        Map<String, Object> model = generator.next();
        assertTrue(model.keySet().containsAll(valueNodeNames));
        valueNodeNames.forEach(key -> assertNotNull(model.get(key)));
    }

    @Test
    public void skipsAllNonValueNodes() {
        Map<String, Object> model = generator.next();
        nonValueNodeNames.forEach(name -> assertFalse(model.keySet().contains(name)));
    }
}
