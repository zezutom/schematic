package org.zezutom.schematic.model.json;

import org.zezutom.schematic.service.generator.json.ObjectGenerator;

import java.util.Map;

public class ObjectNode extends Node<Map<String, Object>, ObjectGenerator> {

    public ObjectNode(String name, ObjectGenerator valueGenerator) {
        super(name, valueGenerator);
    }
}
