package org.zezutom.schematic.model;

import org.zezutom.schematic.service.generator.value.NullGenerator;

import javax.lang.model.type.NullType;

/**
 * Represents a node containing null value.
 */
public class NullNode extends LeafNode<NullType, NullGenerator> {

    public NullNode(String name, NullGenerator valueGenerator) {
        super(name, valueGenerator);
    }
}
