package org.zezutom.schematic.service.generator.model;

import org.zezutom.schematic.model.Node;
import org.zezutom.schematic.model.Tree;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Flattens a graph into a map of leaf nodes.
 */
public class TreeModelGenerator implements ModelGenerator<Map<String, Object>> {

    private Tree tree;

    public TreeModelGenerator(Tree tree) {
        this.tree = tree;
    }

    @Override
    public Map<String, Object> next() {
        return tree
                .getNodes()
                .stream()
                .filter(Node::hasValue)
                .collect(Collectors.toMap(Node::getName, Node::getValue));
    }
}
