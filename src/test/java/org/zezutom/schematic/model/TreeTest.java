package org.zezutom.schematic.model;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

public class TreeTest {

    private Tree tree;

    @Before
    public void before() {
        tree = new Tree();
    }

    @Test
    public void emptyTree() {
        Tree emptyTree = Tree.empty();
        assertNotNull(emptyTree);

        List<Node> nodes = emptyTree.getNodes();
        assertTrue(nodes.isEmpty());
    }

    @Test
    public void nodesAreNotNull() {
        assertNotNull(tree.getNodes());
        assertNotNull(Tree.empty().getNodes());
    }

    @Test
    public void addNode() {
        Node node = mock(Node.class);
        tree.addNode(node);
        assertTrue(tree.getNodes().contains(node));
    }

    @Test
    public void nullNodeIsNotAdded() {
        tree.addNode(null);
        assertTrue(tree.getNodes().isEmpty());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void nodesAreImmutable() {
        tree.getNodes().add(mock(Node.class));
        fail("Nodes are supposed to be immutable!");
    }
}
