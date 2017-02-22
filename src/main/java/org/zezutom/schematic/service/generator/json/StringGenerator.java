package org.zezutom.schematic.service.generator.json;

import com.mifmif.common.regex.Generex;
import fabricator.Fabricator;
import org.springframework.beans.factory.annotation.Autowired;
import org.zezutom.schematic.model.json.StringNode;
import org.zezutom.schematic.model.json.schema.JsonStringFormat;
import org.zezutom.schematic.service.PrototypedService;
import org.zezutom.schematic.service.parser.json.node.JsonNodeParserFactory;
import org.zezutom.schematic.service.parser.json.node.StringParser;
import org.zezutom.schematic.util.RandomUtil;

/**
 * Generates a string value according to the provided schema constraints.
 * @see org.zezutom.schematic.model.json.schema.properties.JsonStringProperty
 */
@PrototypedService
public class StringGenerator extends BaseSchemaGenerator<String, StringNode, StringGenerator, StringParser> {

    private JsonStringFormat format;

    private Integer maxLength;

    private Integer minLength;

    private String pattern;

    @Autowired
    public StringGenerator(JsonNodeParserFactory parserFactory) {
        super(parserFactory);
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
        String value = null;
        if (format != null) {
            switch (format) {
                case DATE_TIME:
                    value = Fabricator.calendar().randomDate().asString();
                    break;
                case EMAIL:
                    value = Fabricator.contact().eMail();
                    break;
                case HOSTNAME:
                case URI:
                    value = Fabricator.internet().urlBuilder().toString();
                    break;
                case IPV4:
                    value = Fabricator.internet().ip();
                    break;
                case IPV6:
                    value = Fabricator.internet().ipv6();
                    break;
                }
        } else if (hasProperty(pattern)) {
            value = new Generex(pattern).random();
        } else {
            value = RandomUtil.nextString(minLength, maxLength);
        }
        return value;
    }

    private boolean hasProperty(String value) {
        return value != null && !value.isEmpty();
    }

}
