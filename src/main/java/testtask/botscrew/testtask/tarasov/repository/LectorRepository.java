package testtask.botscrew.testtask.tarasov.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import testtask.botscrew.testtask.tarasov.model.Lector;

import java.util.List;
import java.util.Optional;

public interface LectorRepository extends JpaRepository<Lector, Long> {
    List<Lector> findByNameContaining(String template);

    @EntityGraph(attributePaths = "departments")
    Optional<Lector> findLectorAndDepartmentsById(Long id);
}
