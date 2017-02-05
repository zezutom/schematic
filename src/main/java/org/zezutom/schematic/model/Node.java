package org.zezutom.schematic.model;

import org.zezutom.schematic.service.generator.value.ValueGenerator;

/**
 * This is a value node. It bears a field name as well as a value generator.
 */
public class Node<T> {

    private String name;

    private ValueGenerator<T> valueGenerator;

    public Node(String name, ValueGenerator<T> valueGenerator) {
        this.name = name;
        this.valueGenerator = valueGenerator;
    }

    public String getName() {
        return name;
    }

    public boolean hasValue() {
        return valueGenerator != null;
    }

    public T getValue() {
        return valueGenerator.next();
    }
}
