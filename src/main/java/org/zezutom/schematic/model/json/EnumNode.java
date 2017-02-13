package org.zezutom.schematic.model.json;

import org.zezutom.schematic.service.generator.json.EnumGenerator;

public class EnumNode extends Node<Object, EnumGenerator> {

    public EnumNode(String name, EnumGenerator valueGenerator) {
        super(name, valueGenerator);
    }
}
