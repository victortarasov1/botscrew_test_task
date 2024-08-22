package testtask.botscrew.testtask.tarasov.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import testtask.botscrew.testtask.tarasov.exception.DepartmentWasNotFoundException;
import testtask.botscrew.testtask.tarasov.model.Department;
import testtask.botscrew.testtask.tarasov.repository.DepartmentRepository;

@Component
@RequiredArgsConstructor
public class GetCountOfEmployeesForDepartment implements Command {
    private final DepartmentRepository departmentRepository;
    private final static String QUERY_PREFIX = "Show count of employees for ";
    @Override
    public void execute(String query) {
        var departmentName = query.substring(QUERY_PREFIX.length());
        var lectors = departmentRepository.findDepartmentAndLectorsByName(departmentName).map(Department::getLectors)
                .orElseThrow(() -> new DepartmentWasNotFoundException(departmentName));
        System.out.println(lectors.size());
    }

    @Override
    public boolean isCommandFits(String query) {
        return query.contains(QUERY_PREFIX);
    }
}
