package org.zezutom.schematic.model.json;

import org.zezutom.schematic.service.generator.json.StringGenerator;

public class StringNode extends Node<String, StringGenerator> {

    public StringNode(String name, StringGenerator valueGenerator) {
        super(name, valueGenerator);
    }
}
