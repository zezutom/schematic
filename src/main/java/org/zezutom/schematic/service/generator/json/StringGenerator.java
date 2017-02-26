package org.zezutom.schematic.service.generator.json;

import com.mifmif.common.regex.Generex;
import fabricator.Fabricator;
import org.springframework.beans.factory.annotation.Autowired;
import org.zezutom.schematic.model.json.StringNode;
import org.zezutom.schematic.model.json.schema.JsonStringFormat;
import org.zezutom.schematic.model.json.schema.properties.JsonStringProperty;
import org.zezutom.schematic.service.PrototypedService;
import org.zezutom.schematic.service.parser.json.node.JsonNodeParserFactory;
import org.zezutom.schematic.service.parser.json.node.StringParser;
import org.zezutom.schematic.util.RandomUtil;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.function.Supplier;

/**
 * Generates a string value according to the provided schema constraints.
 * @see JsonStringProperty
 */
@PrototypedService
public class StringGenerator extends BaseSchemaGenerator<String, StringNode, StringGenerator, StringParser> {

    // Contains pre-generated values
    private static final Map<JsonStringFormat, List<String>> valueMap = new EnumMap<>(JsonStringFormat.class);

    private JsonStringFormat format;

    private Integer maxLength;

    private Integer minLength;

    private String pattern;

    @Autowired
    public StringGenerator(JsonNodeParserFactory parserFactory) {
        super(parserFactory);
    }

    static Map<JsonStringFormat, List<String>> getValueMap() {
        return Collections.unmodifiableMap(valueMap);
    }

    public JsonStringFormat getFormat() {
        return format;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public String getPattern() {
        return pattern;
    }

    public void setFormat(JsonStringFormat format) {
        this.format = format;
    }

    public void setFormat(String format) {
        if (format == null || format.isEmpty()) return;
        this.format = JsonStringFormat.get(format);
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String next() {
        String value;
        if (format != null) {

            List<String> preLoadedValues = valueMap.get(format);
            if (preLoadedValues != null) {
                int randomIndex = RandomUtil.nextInt(preLoadedValues.size());
                value = preLoadedValues.get(randomIndex);
            } else {
                value = getValueSupplier(format).get();
            }
        } else if (hasProperty(pattern)) {
            value = new Generex(pattern).random();
        } else {
            value = RandomUtil.nextString(minLength, maxLength);
        }
        return value;
    }

    boolean hasProperty(String value) {
        return value != null && !value.isEmpty();
    }

    public static void preLoad(int volume, List<JsonStringFormat> formats) {

        if (volume <= 0 || formats == null) return;

        formats.stream().filter(Objects::nonNull).forEach(format -> {
            Supplier<String> supplier = getValueSupplier(format);

            List<String> values = new ArrayList<>();
            for (int i = 0; i < volume; i++) {
                values.add(supplier.get());
            }
            valueMap.put(format, values);
        });
    }

    static void clearPreLoadedValues() {
        valueMap.clear();
    }
    private static Supplier<String> getValueSupplier(@NotNull JsonStringFormat format) {

        Supplier<String> supplier;

        switch (format) {
            case DATE_TIME:
                supplier = () -> Fabricator.calendar().randomDate().asString();
                break;
            case EMAIL:
                supplier = () -> Fabricator.contact().eMail();
                break;
            case HOSTNAME:
            case URI:
                supplier = () -> Fabricator.internet().urlBuilder().toString();
                break;
            case IPV4:
                supplier = () -> Fabricator.internet().ip();
                break;
            case IPV6:
                supplier = () -> Fabricator.internet().ipv6();
                break;
            default:
                supplier = RandomUtil::nextStringFromUUID;
        }

        return supplier;
    }

}
