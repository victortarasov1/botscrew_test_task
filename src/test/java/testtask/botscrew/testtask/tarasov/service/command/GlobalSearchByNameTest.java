package testtask.botscrew.testtask.tarasov.service.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testtask.botscrew.testtask.tarasov.model.Lector;
import testtask.botscrew.testtask.tarasov.repository.DepartmentRepository;
import testtask.botscrew.testtask.tarasov.repository.LectorRepository;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static testtask.botscrew.testtask.tarasov.model.Degree.ASSISTANT;
import static testtask.botscrew.testtask.tarasov.model.Degree.ASSOCIATE_PROFESSOR;

class GlobalSearchByNameTest {
    private DepartmentRepository departmentRepository;
    private LectorRepository lectorRepository;
    private Command command;
    private final static String VALID_QUERY = "Global search by Mathematics";
    private final static String INVALID_QUERY = "Global serch by Mathematics";

    @BeforeEach
    public void setUp() {
        departmentRepository = mock(DepartmentRepository.class);
        lectorRepository = mock(LectorRepository.class);
        command = new GlobalSearchByName(departmentRepository, lectorRepository);
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


        when(departmentRepository.findByNameContaining(anyString())).thenReturn(List.of());
        when(lectorRepository.findByNameContaining(anyString())).thenReturn(List.of(lector1, lector2));

        command.execute(VALID_QUERY);

        verify(departmentRepository, times(1)).findByNameContaining(anyString());
        verify(lectorRepository, times(1)).findByNameContaining(anyString());


    }

}