package org.zezutom.schematic.model;

import org.zezutom.schematic.service.generator.value.ValueGenerator;

/**
 * This is a leaf node, ie it bears a value.
 */
public class LeafNode<T> extends Node {

    private ValueGenerator<T> valueGenerator;

    public LeafNode(String name, ValueGenerator<T> valueGenerator) {
        super(name);
        this.valueGenerator = valueGenerator;
    }

    public ValueGenerator<T> getValueGenerator() {
        return valueGenerator;
    }

    public T getValue() {
        return valueGenerator.next();
    }
}
