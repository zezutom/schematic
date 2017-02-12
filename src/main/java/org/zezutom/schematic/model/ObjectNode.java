package org.zezutom.schematic.model;

import org.zezutom.schematic.service.generator.ObjectGenerator;

public class ObjectNode extends Node<Object, ObjectGenerator> {

    public ObjectNode(String name, ObjectGenerator valueGenerator) {
        super(name, valueGenerator);
    }
}
