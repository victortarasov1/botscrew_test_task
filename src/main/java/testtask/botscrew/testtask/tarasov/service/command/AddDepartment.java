package testtask.botscrew.testtask.tarasov.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Component;
import testtask.botscrew.testtask.tarasov.exception.DepartmentAlreadyExistException;
import testtask.botscrew.testtask.tarasov.repository.DepartmentRepository;
import testtask.botscrew.testtask.tarasov.service.parser.DepartmentParser;

@Component
@RequiredArgsConstructor
public class AddDepartment implements Command {
    private final static String QUERY_PREFIX = "Add department ";
    private final DepartmentParser parser;
    private final DepartmentRepository repository;
    @Override
    public void execute(String query) {
        var department = parser.parse(query);
        try {
            repository.save(department);
        } catch (InvalidDataAccessApiUsageException ex){
            throw new DepartmentAlreadyExistException(department.getName());
        }
    }

    @Override
    public boolean isCommandFits(String query) {
        return query.contains(QUERY_PREFIX);
    }
}
