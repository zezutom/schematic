package org.zezutom.schematic;

import org.zezutom.schematic.service.generator.ValueGenerator;

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

    private static String getResourceFile(String dir, String fileName) {
        return String.format(JSON_RESOURCE_PATH, dir == null ? "" : dir, fileName);
    }
}
