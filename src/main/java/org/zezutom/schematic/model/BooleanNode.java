package org.zezutom.schematic.model;

import org.zezutom.schematic.service.generator.BooleanGenerator;

public class BooleanNode extends Node<Boolean, BooleanGenerator> {

    public BooleanNode(String name, BooleanGenerator valueGenerator) {
        super(name, valueGenerator);
    }
}
