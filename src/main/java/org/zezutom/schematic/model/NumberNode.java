package org.zezutom.schematic.model;

import org.zezutom.schematic.service.generator.NumberGenerator;

public class NumberNode extends Node<Number, NumberGenerator> {

    public NumberNode(String name, NumberGenerator valueGenerator) {
        super(name, valueGenerator);
    }
}
