package org.zezutom.schematic.service.generator.json;

import org.zezutom.schematic.model.json.StringNode;
import org.zezutom.schematic.service.parser.json.StringParser;

/**
 * Generates a string value according to the provided schema constraints.
 * @see org.zezutom.schematic.model.json.schema.properties.JsonStringProperty
 */
public class StringGenerator extends BaseSchemaGenerator<String, StringNode, StringGenerator, StringParser> {

    private String format;

    private Integer maxLength;

    private Integer minLength;

    private String pattern;

    public StringGenerator(StringParser parser) {
        super(parser);
    }

    public String getFormat() {
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

    public void setFormat(String format) {
        this.format = format;
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
        return null;
    }
}
