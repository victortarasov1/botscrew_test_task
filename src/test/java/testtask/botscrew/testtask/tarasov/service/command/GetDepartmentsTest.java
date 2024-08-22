package testtask.botscrew.testtask.tarasov.service.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testtask.botscrew.testtask.tarasov.model.Department;
import testtask.botscrew.testtask.tarasov.repository.DepartmentRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class GetDepartmentsTest {
    private DepartmentRepository departmentRepository;
    private Command command;
    private final static String VALID_QUERY = "Show all departments";
    private final static String INVALID_QUERY = "Show departments all";

    @BeforeEach
    public void setUp() {
        departmentRepository = mock(DepartmentRepository.class);
        command = new GetDepartments(departmentRepository);
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
        var departments = List.of(new Department(), new Department());


        when(departmentRepository.findAll()).thenReturn(departments);

        command.execute(VALID_QUERY);

        verify(departmentRepository, times(1)).findAll();
    }

}