package org.zezutom.schematic.model.json;

import org.zezutom.schematic.model.LeafNode;
import org.zezutom.schematic.service.generator.value.ArrayGenerator;

import java.util.List;

public class ArrayNode extends LeafNode<List<Object>, ArrayGenerator> {

    public ArrayNode(String name, ArrayGenerator valueGenerator) {
        super(name, valueGenerator);
    }
}
