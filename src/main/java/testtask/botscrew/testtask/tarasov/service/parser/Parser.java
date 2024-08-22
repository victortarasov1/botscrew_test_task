package testtask.botscrew.testtask.tarasov.service.parser;

import testtask.botscrew.testtask.tarasov.exception.ParseException;

public interface Parser <T> {
    T parse(String template);

    default Long parseLong(String id) {
        try {
            return Long.valueOf(getValue(id));
        } catch (NumberFormatException ex) {
            throw new ParseException(id);
        }
    }
    default String getValue(String template) {
        return template.substring(template.indexOf("=") + 1).trim();
    }
    default String[] splitTemplate(String template) {
        try {
            return template.substring(template.indexOf("(") + 1, template.indexOf(")")).split(", ");
        } catch (IndexOutOfBoundsException ex) {
            throw new ParseException(template);
        }
    }
}
