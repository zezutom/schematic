package org.zezutom.schematic.model.json;

import org.zezutom.schematic.service.generator.json.NumberGenerator;

public class NumberNode extends Node<Number, NumberGenerator> {

    public NumberNode(String name, NumberGenerator valueGenerator) {
        super(name, valueGenerator);
    }
}
