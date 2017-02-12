package org.zezutom.schematic.model;

import org.zezutom.schematic.service.generator.StringGenerator;

public class StringNode extends Node<String, StringGenerator> {

    public StringNode(String name, StringGenerator valueGenerator) {
        super(name, valueGenerator);
    }
}
