package org.zezutom.schematic.model.json;

import org.zezutom.schematic.service.generator.ValueGenerator;

/**
 * This is a base for different node types.
 */
public abstract class Node<T, G extends ValueGenerator<T>> {

    private final String name;

    private final G valueGenerator;

    public Node(String name, G valueGenerator) {
        this.name = name;
        this.valueGenerator = valueGenerator;
    }

    public String getName() {
        return name;
    }

    public G getValueGenerator() {
        return valueGenerator;
    }

    public T getValue() {
        return valueGenerator.next();
    }
}
