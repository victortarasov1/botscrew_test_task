package testtask.botscrew.testtask.tarasov.service.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testtask.botscrew.testtask.tarasov.exception.LectorWasNotFoundException;
import testtask.botscrew.testtask.tarasov.model.Department;
import testtask.botscrew.testtask.tarasov.model.Lector;
import testtask.botscrew.testtask.tarasov.repository.LectorRepository;
import testtask.botscrew.testtask.tarasov.service.parser.LectorParser;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static testtask.botscrew.testtask.tarasov.model.Degree.ASSISTANT;
import static testtask.botscrew.testtask.tarasov.model.Degree.ASSOCIATE_PROFESSOR;

class ShowDepartmentsByLectorTest {
    private final static String VALID_QUERY = "Show departments by lector 1";
    private final static String INVALID_QUERY = "Show departments b";
    private LectorRepository lectorRepository;
    private LectorParser lectorParser;

    private Command command;

    @BeforeEach
    public void setUp() {
        lectorParser = mock(LectorParser.class);
        lectorRepository = mock(LectorRepository.class);

        command = new ShowDepartmentsByLector(lectorRepository,lectorParser);
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


        when(lectorParser.parseLong(anyString())).thenReturn(1L);
        when(lectorRepository.findLectorAndDepartmentsById(any())).thenReturn(Optional.of(lector1));

        command.execute(VALID_QUERY);

        verify(lectorRepository, times(1)).findLectorAndDepartmentsById(any());


    }

    @Test
    public void testExecute_shouldThrowLectorWasNotFoundException() {

        when(lectorParser.parseLong(anyString())).thenReturn(1L);
        when(lectorRepository.findLectorAndDepartmentsById(any())).thenReturn(Optional.empty());

        assertThrows(LectorWasNotFoundException.class, () ->command.execute(VALID_QUERY));

        verify(lectorRepository, times(1)).findLectorAndDepartmentsById(any());


    }


}