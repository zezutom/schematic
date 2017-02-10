package org.zezutom.schematic.model;

import org.zezutom.schematic.service.generator.value.NumberGenerator;

public class NumberNode extends LeafNode<Number, NumberGenerator> {

    public NumberNode(String name, NumberGenerator valueGenerator) {
        super(name, valueGenerator);
    }
}
