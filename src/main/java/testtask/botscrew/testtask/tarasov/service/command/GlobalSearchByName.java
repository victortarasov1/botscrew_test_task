package testtask.botscrew.testtask.tarasov.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import testtask.botscrew.testtask.tarasov.model.Department;
import testtask.botscrew.testtask.tarasov.model.Lector;
import testtask.botscrew.testtask.tarasov.repository.DepartmentRepository;
import testtask.botscrew.testtask.tarasov.repository.LectorRepository;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor

public class GlobalSearchByName implements Command{
    private final DepartmentRepository departmentRepository;
    private final LectorRepository lectorRepository;
    private final static String QUERY_PREFIX = "Global search by ";
    @Override
    public void execute(String query) {
        var template = query.substring(QUERY_PREFIX.length());
        var results = new ArrayList<>(departmentRepository.findByNameContaining(template).stream().map(Department::getName).toList());
        results.addAll(lectorRepository.findByNameContaining(template).stream().map(Lector::getName).toList());
        System.out.println(results);
    }

    @Override
    public boolean isCommandFits(String query) {
        return query.contains(QUERY_PREFIX);
    }
}
