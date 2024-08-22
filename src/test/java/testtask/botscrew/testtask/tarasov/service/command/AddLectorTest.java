package testtask.botscrew.testtask.tarasov.service.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import testtask.botscrew.testtask.tarasov.exception.DepartmentAlreadyExistException;
import testtask.botscrew.testtask.tarasov.model.Department;
import testtask.botscrew.testtask.tarasov.model.Lector;
import testtask.botscrew.testtask.tarasov.repository.DepartmentRepository;
import testtask.botscrew.testtask.tarasov.repository.LectorRepository;
import testtask.botscrew.testtask.tarasov.service.parser.DepartmentParser;
import testtask.botscrew.testtask.tarasov.service.parser.LectorParser;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static testtask.botscrew.testtask.tarasov.model.Degree.ASSISTANT;
import static testtask.botscrew.testtask.tarasov.model.Degree.ASSOCIATE_PROFESSOR;

public class AddLectorTest {
    private LectorRepository lectorRepository;
    private LectorParser lectorParser;
    private Command command;
    private final static String VALID_QUERY = "Add lector (name = Ivan Petrenko, degree = Assistant, salary = 5000)";
    private final static String INVALID_QUERY = "addd department";

    @BeforeEach
    public void setUp() {
        lectorParser = mock(LectorParser.class);
        lectorRepository = mock(LectorRepository.class);
        command = new AddLector(lectorParser, lectorRepository);
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


        when(lectorParser.parse(anyString())).thenReturn(lector1);

        command.execute(VALID_QUERY);

        verify(lectorRepository, times(1)).save(any());


    }

}
