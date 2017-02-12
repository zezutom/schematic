package org.zezutom.schematic.model;

import org.zezutom.schematic.service.generator.EnumGenerator;

public class EnumNode extends Node<Object, EnumGenerator> {

    public EnumNode(String name, EnumGenerator valueGenerator) {
        super(name, valueGenerator);
    }
}
