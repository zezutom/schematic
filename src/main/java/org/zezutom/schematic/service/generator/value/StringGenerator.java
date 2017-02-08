package org.zezutom.schematic.service.generator.value;

/**
 * Generates a string value according to the provided schema constraints.
 * @see org.zezutom.schematic.model.json.properties.JsonStringProperty
 */
public class StringGenerator implements ValueGenerator<String> {

    private String format;

    private Integer maxLength;

    private Integer minLength;

    private String pattern;

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
