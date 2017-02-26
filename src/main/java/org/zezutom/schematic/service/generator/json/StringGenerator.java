package org.zezutom.schematic.service.generator.json;

import com.mifmif.common.regex.Generex;
import org.springframework.beans.factory.annotation.Autowired;
import org.zezutom.schematic.model.json.StringNode;
import org.zezutom.schematic.model.json.schema.JsonStringFormat;
import org.zezutom.schematic.model.json.schema.properties.JsonStringProperty;
import org.zezutom.schematic.service.DataService;
import org.zezutom.schematic.service.PrototypedService;
import org.zezutom.schematic.service.parser.json.node.JsonNodeParserFactory;
import org.zezutom.schematic.service.parser.json.node.StringParser;
import org.zezutom.schematic.util.AppUtil;
import org.zezutom.schematic.util.RandomUtil;

import java.util.List;

/**
 * Generates a string value according to the provided schema constraints.
 * @see JsonStringProperty
 */
@PrototypedService
public class StringGenerator extends BaseSchemaGenerator<String, StringNode, StringGenerator, StringParser> {

    private JsonStringFormat format;

    private Integer maxLength;

    private Integer minLength;

    private String pattern;

    private DataService dataService;

    @Autowired
    public StringGenerator(JsonNodeParserFactory parserFactory, DataService dataService) {
        super(parserFactory);
        this.dataService = dataService;
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

            List<String> preLoadedValues = dataService.get(format);
            if (preLoadedValues != null) {
                int randomIndex = RandomUtil.nextInt(preLoadedValues.size());
                value = preLoadedValues.get(randomIndex);
            } else {
                value = AppUtil.getValueSupplier(format).get();
            }
        } else if (hasProperty(pattern)) {
            value = new Generex(pattern).random();
        } else if (combinationRule != null) {
           value = getCombinationValue(() -> RandomUtil.nextString(minLength, maxLength));
        } else {
            value = RandomUtil.nextString(minLength, maxLength);
        }
        return value;
    }

    boolean hasProperty(String value) {
        return value != null && !value.isEmpty();
    }
}
