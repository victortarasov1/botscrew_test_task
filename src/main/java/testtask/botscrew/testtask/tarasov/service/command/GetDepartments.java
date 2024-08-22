package testtask.botscrew.testtask.tarasov.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import testtask.botscrew.testtask.tarasov.repository.DepartmentRepository;

@Component
@RequiredArgsConstructor
public class GetDepartments implements Command {
    private final DepartmentRepository departmentRepository;
    private final static String QUERY_PREFIX = "Show all departments";

    @Override
    public void execute(String query) {
       departmentRepository.findAll().forEach(System.out::println);
    }

    @Override
    public boolean isCommandFits(String query) {
        return query.contains(QUERY_PREFIX);
    }
}
