package org.zezutom.schematic.model.json;

import org.zezutom.schematic.service.generator.json.IntegerGenerator;

public class IntegerNode extends Node<Integer, IntegerGenerator> {

    public IntegerNode(String name, IntegerGenerator valueGenerator) {
        super(name, valueGenerator);
    }
}
