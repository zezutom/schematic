package org.zezutom.schematic.service.parser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.zezutom.schematic.model.Node;
import org.zezutom.schematic.model.Tree;
import org.zezutom.schematic.model.json.JsonSchemaAttribute;
import org.zezutom.schematic.service.generator.value.BooleanGenerator;
import org.zezutom.schematic.service.generator.value.ValueGenerator;
import org.zezutom.schematic.util.JsonUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Function;

public class JsonSchemaParser implements SchemaParser {

    @Override
    public Tree parse(String schemaPath) throws IOException {
        return parse(Files.newInputStream(Paths.get(schemaPath)));
    }

    @Override
    public Tree parse(InputStream schema) throws IOException {
        return parse(new Tree(), loadJson(schema));
    }

    private JsonNode loadJson(InputStream inputStream) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(JsonParser.Feature.ALLOW_COMMENTS);
        return objectMapper.readTree(inputStream);
    }

    private Tree parse(Tree tree, JsonNode rootNode) {
        Iterator<String> fieldNameIterator = rootNode.fieldNames();
        while (fieldNameIterator.hasNext()) {
            String fieldName = fieldNameIterator.next();
            JsonNode node = rootNode.get(fieldName);
            if (JsonUtil.hasAttribute(node, JsonSchemaAttribute.TYPE)) {
                if (JsonUtil.isBoolean(node)) {
                    tree.addNode(new Node<>(fieldName, new BooleanGenerator()));
                } else {
                    tree.addNode(parseValueNode(fieldName, node));
                }
            } else {
                tree = parse(tree, node);
            }
        }
        return tree;
    }

    private<T> Node<T> parseValueNode(String fieldName, JsonNode node) {

        Function<JsonNode, ValueGenerator> generatorFunction;
        if (JsonUtil.isNumber(node)) {
            generatorFunction = JsonUtil::createNumberGenerator;
        } else {
            generatorFunction = JsonUtil::createStringGenerator;
        }

        Optional<JsonNode> enumNode = JsonUtil.getAny(node, JsonSchemaAttribute.ENUM, JsonSchemaAttribute.ONE_OF);

        ValueGenerator<T> valueGenerator;
        if (enumNode.isPresent() && JsonUtil.isArray(enumNode.get())) {
            // We are looking at a list of values to choose from
            valueGenerator = generatorFunction.apply(enumNode.get());
        } else {
            // It's a single-value node
            valueGenerator = generatorFunction.apply(node);
        }
        return new Node<>(fieldName, valueGenerator);
    }
}
