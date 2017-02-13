package org.zezutom.schematic.model.json;

import org.zezutom.schematic.service.generator.json.NullGenerator;

import javax.lang.model.type.NullType;

/**
 * Represents a node containing null value.
 */
public class NullNode extends Node<NullType, NullGenerator> {

    public NullNode(String name, NullGenerator valueGenerator) {
        super(name, valueGenerator);
    }
}
