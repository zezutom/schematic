package org.zezutom.schematic.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Schema representation. It essentially reduces the schema to a bunch of value nodes.
 * @see Node
 */
public class Tree {

    private List<Node> nodes = new ArrayList<>();

    public List<Node> getNodes() {
        return Collections.unmodifiableList(nodes);
    }

    public void addNode(Node node) {
        if (node == null) return;
        nodes.add(node);
    }

    static Tree empty() {
        return new Tree();
    }
}
