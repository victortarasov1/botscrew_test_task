package testtask.botscrew.testtask.tarasov.service.parser;

import org.springframework.stereotype.Component;
import testtask.botscrew.testtask.tarasov.exception.ParseException;
import testtask.botscrew.testtask.tarasov.model.Degree;
import testtask.botscrew.testtask.tarasov.model.Lector;

@Component
public class LectorParserImpl implements LectorParser {
    @Override
    public Lector parse(String template) {
        var values = splitTemplate(template);
        if (values.length == 3) {
            var name = getValue(values[0]);
            var degree = Degree.fromString(getValue(values[1]));
            var salary = parseLong(values[2]);
            return new Lector(name, degree, salary);
        } throw new ParseException(template);
    }
}
