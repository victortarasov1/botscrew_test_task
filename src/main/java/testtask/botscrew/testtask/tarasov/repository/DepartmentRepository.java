package testtask.botscrew.testtask.tarasov.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import testtask.botscrew.testtask.tarasov.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, String> {
    @EntityGraph(attributePaths = "head")
    Optional<Department> findDepartmentAndHeadByName(String departmentName);

    @EntityGraph(attributePaths = "lectors")
    Optional<Department> findDepartmentAndLectorsByName(String departmentName);

    List<Department> findByNameContaining(String template);
}
