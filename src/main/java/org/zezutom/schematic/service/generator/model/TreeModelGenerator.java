package org.zezutom.schematic.service.generator.model;

import org.zezutom.schematic.model.Node;

import java.util.Map;

/**
 * Flattens a graph into a map of leaf nodes.
 */
public class TreeModelGenerator implements ModelGenerator<Map<String, Object>> {

    private Node tree;

    public TreeModelGenerator(Node tree) {
        this.tree = tree;
    }

    @Override
    public Map<String, Object> next() {
        return null;
//        return tree
//                .getNodes()
//                .stream()
//                .filter(LeafNode::hasValue)
//                .collect(Collectors.toMap(Node::getName, Node::getValue));
    }
}
