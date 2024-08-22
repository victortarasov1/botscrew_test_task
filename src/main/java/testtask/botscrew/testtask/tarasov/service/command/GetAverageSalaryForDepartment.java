package testtask.botscrew.testtask.tarasov.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import testtask.botscrew.testtask.tarasov.exception.DepartmentWasNotFoundException;
import testtask.botscrew.testtask.tarasov.model.Department;
import testtask.botscrew.testtask.tarasov.model.Lector;
import testtask.botscrew.testtask.tarasov.repository.DepartmentRepository;

@Component
@RequiredArgsConstructor
public class GetAverageSalaryForDepartment implements Command {
    private final DepartmentRepository departmentRepository;
    private final static String QUERY_PREFIX = "Show the average salary for the department ";
    @Override
    public void execute(String query) {
        var departmentName = query.substring(QUERY_PREFIX.length());
        var lectors = departmentRepository.findDepartmentAndLectorsByName(departmentName).map(Department::getLectors)
                .orElseThrow(() -> new DepartmentWasNotFoundException(departmentName));
        var averageSalary = lectors.stream().mapToLong(Lector::getSalary).average().orElse(0);
        System.out.println("The average salary of " + departmentName + " is " + String.format("%.2f",averageSalary));
    }

    @Override
    public boolean isCommandFits(String query) {
        return query.contains(QUERY_PREFIX);
    }
}
