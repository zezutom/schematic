package org.zezutom.schematic.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Nests other nodes, both compound or leaf ones.
 * @see LeafNode
 * @see Node
 */
public class CompoundNode extends Node {

    private final List<Node> nodes = new ArrayList<>();

    public CompoundNode() {
        this(null);
    }

    public CompoundNode(String name) {
        super(name);
    }

    public void addNode(Node node) {
        if (node == null) return;
        nodes.add(node);
    }

    public List<Node> getNodes() {
        return Collections.unmodifiableList(nodes);
    }
}
