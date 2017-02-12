package org.zezutom.schematic.model;

import org.zezutom.schematic.service.generator.IntegerGenerator;

public class IntegerNode extends Node<Integer, IntegerGenerator> {

    public IntegerNode(String name, IntegerGenerator valueGenerator) {
        super(name, valueGenerator);
    }
}
