package testtask.botscrew.testtask.tarasov.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import testtask.botscrew.testtask.tarasov.exception.DepartmentWasNotFoundException;
import testtask.botscrew.testtask.tarasov.model.Department;
import testtask.botscrew.testtask.tarasov.model.Lector;
import testtask.botscrew.testtask.tarasov.repository.DepartmentRepository;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetDepartmentStatistics implements Command {
    private final static String QUERY_PREFIX = "Show ";
    private final static String QUERY_SUFFIX = " statistics";
    private final DepartmentRepository departmentRepository;
    @Override
    public void execute(String query) {
        var departmentName = query.substring(QUERY_PREFIX.length(), query.length() - QUERY_SUFFIX.length());
        var lectors = departmentRepository.findDepartmentAndLectorsByName(departmentName).map(Department::getLectors)
                .orElseThrow(() -> new DepartmentWasNotFoundException(departmentName));
        var statistics = lectors.stream().collect(Collectors.toMap(Lector::getDegree, v -> 1L, Long::sum));
        statistics.forEach( (degree, count) -> System.out.println(degree.getName() + " - " + count));
    }

    @Override
    public boolean isCommandFits(String query) {
        return query.contains(QUERY_PREFIX) && query.contains(QUERY_SUFFIX);
    }
}
