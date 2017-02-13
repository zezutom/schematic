package org.zezutom.schematic.service.parser.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.zezutom.schematic.model.json.Node;
import org.zezutom.schematic.service.parser.SchemaParser;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonSchemaParser implements SchemaParser {

    @Override
    public <T extends Node> T parse(String schemaPath) throws IOException {
        return parse(Files.newInputStream(Paths.get(schemaPath)));
    }

    @Override
    public <T extends Node> T parse(InputStream schema) throws IOException {
        return parse(loadJson(schema));
    }

    private JsonNode loadJson(InputStream inputStream) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(JsonParser.Feature.ALLOW_COMMENTS);
        return objectMapper.readTree(inputStream);
    }

    private <T extends Node> T parse(JsonNode rootNode) {
        JsonNodeParser<T> parser = JsonNodeParserFactory.newInstance(rootNode);
        return parser == null ? null : parser.parse(rootNode);
    }
}
