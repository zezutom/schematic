package org.zezutom.schematic.model;

import org.zezutom.schematic.service.generator.value.IntegerGenerator;

public class IntegerNode extends LeafNode<Integer, IntegerGenerator> {

    public IntegerNode(String name, IntegerGenerator valueGenerator) {
        super(name, valueGenerator);
    }
}
