package testtask.botscrew.testtask.tarasov.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import testtask.botscrew.testtask.tarasov.exception.DepartmentWasNotFoundException;
import testtask.botscrew.testtask.tarasov.repository.DepartmentRepository;

@Component
@RequiredArgsConstructor
public class GetHeadOfDepartment implements Command{
    private final DepartmentRepository departmentRepository;
    private final static String QUERY_PREFIX = "Who is head of department ";
    @Override
    public void execute(String query) {
        var departmentName = query.substring(QUERY_PREFIX.length());
        var department = departmentRepository.findDepartmentAndHeadByName(departmentName)
                .orElseThrow(() -> new DepartmentWasNotFoundException(departmentName));
        System.out.println("Head of " + departmentName + " department is " + department.getHead().getName());
    }

    @Override
    public boolean isCommandFits(String query) {
        return query.contains(GetHeadOfDepartment.QUERY_PREFIX);
    }
}
