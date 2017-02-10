package org.zezutom.schematic.model;

import org.zezutom.schematic.service.generator.value.ValueGenerator;

/**
 * This is a leaf node, ie it bears a value.
 */
public abstract class LeafNode<T, G extends ValueGenerator<T>> extends Node {

    private G valueGenerator;

    public LeafNode(String name, G valueGenerator) {
        super(name);
        this.valueGenerator = valueGenerator;
    }

    public G getValueGenerator() {
        return valueGenerator;
    }

    public T getValue() {
        return valueGenerator.next();
    }
}
