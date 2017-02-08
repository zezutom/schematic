package org.zezutom.schematic.service.parser.json;

import org.zezutom.schematic.model.LeafNode;
import org.zezutom.schematic.service.generator.value.ValueGenerator;

/**
 * Serves as a template for parsing a leaf-level JSON node.
 */
abstract class BaseJsonLeafNodeParser<T, P, G extends ValueGenerator<T>> extends BaseJsonNodeParser<T, P> {

    final G generator;

    BaseJsonLeafNodeParser(G generator) {
        this.generator = generator;
    }

    @Override
    LeafNode<T> getNode(String nodeName) {
        return new LeafNode<>(nodeName, generator);
    }
}
