package org.zezutom.schematic.model;

/**
 * A generic node.
 * @see org.zezutom.schematic.model.LeafNode
 */
public abstract class Node {

    private String name;

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
