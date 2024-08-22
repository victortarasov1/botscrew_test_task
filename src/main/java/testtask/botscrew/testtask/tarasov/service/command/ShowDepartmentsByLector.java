package testtask.botscrew.testtask.tarasov.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import testtask.botscrew.testtask.tarasov.exception.LectorWasNotFoundException;
import testtask.botscrew.testtask.tarasov.model.Lector;
import testtask.botscrew.testtask.tarasov.repository.LectorRepository;
import testtask.botscrew.testtask.tarasov.service.parser.LectorParser;

@Component
@RequiredArgsConstructor
public class ShowDepartmentsByLector implements Command {
    private final static String QUERY_PREFIX = "Show departments by lector ";
    private final LectorRepository lectorRepository;
    private final LectorParser parser;
    @Override
    public void execute(String query) {
        var id  = parser.parseLong(query.substring(QUERY_PREFIX.length()));
        var departments = lectorRepository.findLectorAndDepartmentsById(id).map(Lector::getDepartments)
                .orElseThrow(() -> new LectorWasNotFoundException(id));
        departments.forEach(System.out::println);
    }

    @Override
    public boolean isCommandFits(String query) {
        return query.contains(QUERY_PREFIX);
    }
}
