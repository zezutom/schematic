package org.zezutom.schematic;

import org.springframework.context.ApplicationContext;
import org.zezutom.schematic.service.generator.ValueGenerator;
import org.zezutom.schematic.service.generator.json.*;
import org.zezutom.schematic.service.parser.json.node.*;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test utilities.
 */
public class TestUtil {

    private static final String JSON_RESOURCE_PATH = "json" + File.separator + "%s" + File.separator + "%s";

    private TestUtil() {}

    public static  <T, G extends ValueGenerator<T>> G mockGenerator(Class<G> generatorClass, T value) {
        G generator = mock(generatorClass);
        when(generator.next()).thenReturn(value);
        return generator;
    }

    public static InputStream getResourceAsStream(String dir, String fileName) {
        return TestUtil.class
                .getClassLoader()
                .getResourceAsStream(getResourceFile(dir, fileName));
    }

    public static String getResourcePath(String dir, String fileName) {
        URL resource = TestUtil.class
                .getClassLoader()
                .getResource(getResourceFile(dir, fileName));
        return (resource == null) ? null : resource.getFile();
    }

    public static JsonNodeParserFactory mockParserFactory() {
        JsonNodeParserFactory parserFactory = App.jsonNodeParserFactory();

        ApplicationContext context = mock(ApplicationContext.class);
        when(context.getBean(ArrayParser.class)).thenAnswer(invocation -> new ArrayParser(new ArrayGenerator(parserFactory), parserFactory));
        when(context.getBean(BooleanParser.class)).thenAnswer(invocation -> new BooleanParser(new BooleanGenerator()));
        when(context.getBean(EnumParser.class)).thenAnswer(invocation -> new EnumParser(new EnumGenerator()));
        when(context.getBean(IntegerParser.class)).thenAnswer(invocation -> new IntegerParser(new IntegerGenerator(parserFactory)));
        when(context.getBean(NullParser.class)).thenAnswer(invocation -> new NullParser(new NullGenerator()));
        when(context.getBean(NumberParser.class)).thenAnswer(invocation -> new NumberParser(new NumberGenerator(parserFactory)));
        when(context.getBean(ObjectParser.class)).thenAnswer(invocation -> new ObjectParser(new ObjectGenerator(parserFactory), parserFactory));
        when(context.getBean(StringParser.class)).thenAnswer(invocation -> new StringParser(new StringGenerator(parserFactory)));

        parserFactory.setContext(context);
        return parserFactory;
    }

    private static String getResourceFile(String dir, String fileName) {
        return String.format(JSON_RESOURCE_PATH, dir == null ? "" : dir, fileName);
    }
}
