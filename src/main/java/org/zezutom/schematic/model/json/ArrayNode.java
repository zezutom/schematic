package org.zezutom.schematic.model.json;

import org.zezutom.schematic.service.generator.json.ArrayGenerator;

import java.util.List;

public class ArrayNode extends Node<List<Object>, ArrayGenerator> {

    public ArrayNode(String name, ArrayGenerator valueGenerator) {
        super(name, valueGenerator);
    }
}
