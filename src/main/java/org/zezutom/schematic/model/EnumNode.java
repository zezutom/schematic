package org.zezutom.schematic.model;

import org.zezutom.schematic.service.generator.value.EnumGenerator;

public class EnumNode extends LeafNode<Object, EnumGenerator> {

    public EnumNode(String name, EnumGenerator valueGenerator) {
        super(name, valueGenerator);
    }
}
