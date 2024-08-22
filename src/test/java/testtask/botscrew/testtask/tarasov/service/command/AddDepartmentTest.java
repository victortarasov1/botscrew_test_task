package testtask.botscrew.testtask.tarasov.service.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import testtask.botscrew.testtask.tarasov.exception.DepartmentAlreadyExistException;
import testtask.botscrew.testtask.tarasov.exception.DepartmentWasNotFoundException;
import testtask.botscrew.testtask.tarasov.model.Department;
import testtask.botscrew.testtask.tarasov.model.Lector;
import testtask.botscrew.testtask.tarasov.repository.DepartmentRepository;
import testtask.botscrew.testtask.tarasov.service.parser.DepartmentParser;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static testtask.botscrew.testtask.tarasov.model.Degree.ASSISTANT;
import static testtask.botscrew.testtask.tarasov.model.Degree.ASSOCIATE_PROFESSOR;

class AddDepartmentTest {
    private DepartmentRepository departmentRepository;
    private DepartmentParser departmentParser;
    private Command command;
    private final static String VALID_QUERY = "Add department (name = Computer Science, headId = 1)";
    private final static String INVALID_QUERY = "addd department";

    @BeforeEach
    public void setUp() {
        departmentParser = mock(DepartmentParser.class);
        departmentRepository = mock(DepartmentRepository.class);
        command = new AddDepartment(departmentParser, departmentRepository);
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


        when(departmentParser.parse(anyString())).thenReturn(department1);

        command.execute(VALID_QUERY);

        verify(departmentRepository, times(1)).save(any());


    }

    @Test
    public void testExecute_shouldThrowDepartmentAlreadyExistException() {
        var lector1 = new Lector(null, "Ivan Petrenko", ASSISTANT, 5000L, new HashSet<>());
        var lector2 = new Lector(null, "Petro Ivanov", ASSOCIATE_PROFESSOR, 7000L, new HashSet<>());
        var department1 = new Department("Computer Science", lector2);
        department1.getLectors().add(lector1);


        when(departmentParser.parse(anyString())).thenReturn(department1);
        when(departmentRepository.save(any())).thenThrow(InvalidDataAccessApiUsageException.class);

        assertThrows( DepartmentAlreadyExistException.class, () -> command.execute(VALID_QUERY));
    }

}