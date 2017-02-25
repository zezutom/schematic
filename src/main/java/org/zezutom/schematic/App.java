package org.zezutom.schematic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.zezutom.schematic.model.json.Node;
import org.zezutom.schematic.model.json.schema.JsonDataType;
import org.zezutom.schematic.model.json.schema.JsonStringFormat;
import org.zezutom.schematic.service.generator.json.StringGenerator;
import org.zezutom.schematic.service.parser.json.JsonNodeParser;
import org.zezutom.schematic.service.parser.json.JsonSchemaParser;
import org.zezutom.schematic.service.parser.json.node.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Starts an embedded web container and provides access to API.
 */
@SpringBootApplication
public class App {

    /**
     * The maximum number of pre-generated values. Anything beyond this threshold is deemed to have
     * severe impact on application startup time.
     */
    private static final int MAX_PRELOAD_VOLUME = 10000;

    @Value("${schema.path}")
    private String schemaPath;

    @Value("classpath:schema/sample_schema.json")
    private Resource defaultSchema;

    @Value("${preload.volume}")
    private Integer preloadVolume;

    @Value(("${preload.types}"))
    private String preloadTypes;

    @Bean
    public static JsonNodeParserFactory jsonNodeParserFactory() {
        Map<JsonDataType, Class<? extends JsonNodeParser>> nodeParserMap = new EnumMap<>(JsonDataType.class);
        nodeParserMap.put(JsonDataType.ARRAY, ArrayParser.class);
        nodeParserMap.put(JsonDataType.BOOLEAN, BooleanParser.class);
        nodeParserMap.put(JsonDataType.ENUM, EnumParser.class);
        nodeParserMap.put(JsonDataType.INTEGER, IntegerParser.class);
        nodeParserMap.put(JsonDataType.NULL, NullParser.class);
        nodeParserMap.put(JsonDataType.NUMBER, NumberParser.class);
        nodeParserMap.put(JsonDataType.OBJECT, ObjectParser.class);
        nodeParserMap.put(JsonDataType.STRING, StringParser.class);
        return new JsonNodeParserFactory(nodeParserMap);
    }

    @Bean
    @Autowired
    public static JsonSchemaParser jsonSchemaParser(JsonNodeParserFactory parserFactory) {
        return new JsonSchemaParser(parserFactory);
    }

    @Bean
    @Autowired
    public StringGenerator stringGenerator(JsonNodeParserFactory parserFactory) {
        StringGenerator generator = new StringGenerator(parserFactory);

        if (preloadVolume != null && preloadVolume > 0 && !(preloadTypes == null || preloadTypes.isEmpty())) {
            if (preloadVolume > MAX_PRELOAD_VOLUME) preloadVolume = MAX_PRELOAD_VOLUME;

            List<JsonStringFormat> formats = Arrays.stream(preloadTypes.split(","))
                    .map(String::trim)
                    .filter(x -> !x.isEmpty())
                    .map(String::toLowerCase)
                    .filter(JsonStringFormat::contains)
                    .map(JsonStringFormat::get)
                    .collect(Collectors.toList());
            StringGenerator.preLoad(preloadVolume, formats);
        }
        return generator;
    }

    @Bean
    @Autowired
    public Node parseRootNode(JsonSchemaParser schemaParser) throws IOException {
        return (schemaPath.isEmpty()) ?
                schemaParser.parse(defaultSchema.getInputStream()) :
                schemaParser.parse(schemaPath);
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }
}
