package org.zezutom.schematic.service.parser;

import org.zezutom.schematic.model.Node;

import java.io.IOException;
import java.io.InputStream;

/**
 * Converts a schema into a graph, which can then be traversed and processed.
 */
public interface SchemaParser {

    <T extends Node> T parse(String schemaPath) throws IOException;

    <T extends Node> T parse(InputStream schema) throws IOException;
}
