package testtask.botscrew.testtask.tarasov.service.parser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import testtask.botscrew.testtask.tarasov.exception.LectorWasNotFoundException;
import testtask.botscrew.testtask.tarasov.exception.ParseException;
import testtask.botscrew.testtask.tarasov.model.Department;
import testtask.botscrew.testtask.tarasov.repository.LectorRepository;

@Component
@RequiredArgsConstructor
public class DepartmentParserImpl implements DepartmentParser {
    private final LectorRepository repository;
    @Override
    public Department parse(String template) {
        var values = splitTemplate(template);
        if(values.length == 2) {
            var name = getValue(values[0]);
            var headId = parseLong(values[1]);
            var head = repository.findLectorAndDepartmentsById(headId).orElseThrow(() -> new LectorWasNotFoundException(headId));
            return new Department(name, head);
        } throw new ParseException(template);
    }
}
