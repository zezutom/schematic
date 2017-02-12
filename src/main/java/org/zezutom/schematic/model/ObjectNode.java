package org.zezutom.schematic.model;

import org.zezutom.schematic.service.generator.json.ObjectGenerator;

public class ObjectNode extends Node<Object, ObjectGenerator> {

    public ObjectNode(String name, ObjectGenerator valueGenerator) {
        super(name, valueGenerator);
    }
}
