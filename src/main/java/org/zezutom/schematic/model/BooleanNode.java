package org.zezutom.schematic.model;

import org.zezutom.schematic.service.generator.value.BooleanGenerator;

public class BooleanNode extends LeafNode<Boolean, BooleanGenerator> {

    public BooleanNode(String name, BooleanGenerator valueGenerator) {
        super(name, valueGenerator);
    }
}
