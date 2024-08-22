package testtask.botscrew.testtask.tarasov.service.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testtask.botscrew.testtask.tarasov.exception.DepartmentWasNotFoundException;
import testtask.botscrew.testtask.tarasov.model.Department;
import testtask.botscrew.testtask.tarasov.model.Lector;
import testtask.botscrew.testtask.tarasov.repository.DepartmentRepository;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static testtask.botscrew.testtask.tarasov.model.Degree.ASSISTANT;
import static testtask.botscrew.testtask.tarasov.model.Degree.ASSOCIATE_PROFESSOR;

class GetCountOfEmployeesForDepartmentTest {
    private DepartmentRepository departmentRepository;
    private Command command;
    private final static String VALID_QUERY = "Show count of employees for Mathematics";
    private final static String INVALID_QUERY = "Show coudddt of employeesdd for Mathematics";

    @BeforeEach
    public void setUp() {
        departmentRepository = mock(DepartmentRepository.class);
        command = new GetCountOfEmployeesForDepartment(departmentRepository);
    }

    @Test
    public void testIfCommandFits_shouldReturnTrue() {
        var result = command.isCommandFits(VALID_QUERY);
        assertTrue(result);
    }

    @Test
    public void testIfCommandFits_shouldReturnFalse() {
        var result = command.isCommandFits(INVALID_QUERY);
        assertFalse(result);
    }
    @Test
    public void testExecute() {
        var lector1 = new Lector(null, "Ivan Petrenko", ASSISTANT, 5000L, new HashSet<>());
        var lector2 = new Lector(null, "Petro Ivanov", ASSOCIATE_PROFESSOR, 7000L, new HashSet<>());
        var department1 = new Department("Computer Science", lector2);
        department1.getLectors().add(lector1);


        when(departmentRepository.findDepartmentAndLectorsByName(anyString())).thenReturn(Optional.of(department1));

        command.execute(VALID_QUERY);

        verify(departmentRepository, times(1)).findDepartmentAndLectorsByName(anyString());
    }
    @Test
    public void testExecute_shouldThrowDepartmentWasNotFoundException() {
        when(departmentRepository.findDepartmentAndLectorsByName(anyString())).thenReturn(Optional.empty());

        assertThrows(DepartmentWasNotFoundException.class, () -> command.execute(VALID_QUERY));
    }


}