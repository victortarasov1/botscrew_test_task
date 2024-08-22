package testtask.botscrew.testtask.tarasov.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import testtask.botscrew.testtask.tarasov.exception.DepartmentWasNotFoundException;
import testtask.botscrew.testtask.tarasov.model.Department;
import testtask.botscrew.testtask.tarasov.repository.DepartmentRepository;

@Component
@RequiredArgsConstructor
public class ShowLectorsByDepartment implements Command {
    private final static String QUERY_PREFIX = "Show lectors by department ";
    private final DepartmentRepository departmentRepository;
    @Override
    public void execute(String query) {
        var departmentName = query.substring(QUERY_PREFIX.length());
        var lectors = departmentRepository.findDepartmentAndLectorsByName(departmentName).map(Department::getLectors)
                .orElseThrow(() -> new DepartmentWasNotFoundException(departmentName));
        lectors.forEach(System.out::println);
    }

    @Override
    public boolean isCommandFits(String query) {
        return query.contains(QUERY_PREFIX);
    }
}
