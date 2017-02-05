package org.zezutom.schematic.service.parser;

import org.junit.Before;
import org.junit.Test;
import org.zezutom.schematic.model.Node;
import org.zezutom.schematic.model.Tree;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class JsonSchemaParserTest {

    private Tree tree;

    @Before
    public void before() throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("example_schema.json");
        tree = new JsonSchemaParser().parse(inputStream);
    }

    @Test
    public void allValueFieldsArePopulated() {
        assertNotNull(tree);
        assertHasValueNode("firstName");
        assertHasValueNode("lastName");
        assertHasValueNode("age");
    }

    private void assertHasValueNode(String name) {
        assertTrue(tree
                .getNodes()
                .stream()
                .filter(Objects::nonNull)
                .filter(Node::hasValue)
                .filter(node -> name.equals(node.getName()))
                .count() == 1);
    }
}
