package org.zezutom.schematic.model;

import org.zezutom.schematic.service.generator.ArrayGenerator;

import java.util.List;

public class ArrayNode extends Node<List<Object>, ArrayGenerator> {

    public ArrayNode(String name, ArrayGenerator valueGenerator) {
        super(name, valueGenerator);
    }
}
