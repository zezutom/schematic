package org.zezutom.schematic.model;

import org.zezutom.schematic.service.generator.value.StringGenerator;

public class StringNode extends LeafNode<String, StringGenerator> {

    public StringNode(String name, StringGenerator valueGenerator) {
        super(name, valueGenerator);
    }
}
