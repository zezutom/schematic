package org.zezutom.schematic.service.parser;

import org.zezutom.schematic.model.Tree;

import java.io.IOException;
import java.io.InputStream;

/**
 * Converts a schema into a graph, which can then be traversed and processed.
 */
public interface SchemaParser {

    Tree parse(String schemaPath) throws IOException;

    Tree parse(InputStream schema) throws IOException;
}
