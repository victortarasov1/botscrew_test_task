package testtask.botscrew.testtask.tarasov.service.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testtask.botscrew.testtask.tarasov.exception.LectorWasNotFoundException;
import testtask.botscrew.testtask.tarasov.model.Degree;
import testtask.botscrew.testtask.tarasov.model.Department;
import testtask.botscrew.testtask.tarasov.model.Lector;
import testtask.botscrew.testtask.tarasov.repository.LectorRepository;
import testtask.botscrew.testtask.tarasov.exception.ParseException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DepartmentParserImplTest {

   private LectorRepository lectorRepository;
   private DepartmentParser departmentParser;

    @BeforeEach
    void setUp() {
        lectorRepository = mock(LectorRepository.class);
        departmentParser = new DepartmentParserImpl(lectorRepository);

    }

    @Test
    void testParse_ValidInput() {
        String template = "(name=Computer Science, headId=1)";
        Lector head = new Lector();
        head.setId(1L);
        head.setName("John Doe");
        head.setDegree(Degree.ASSISTANT);
        head.setSalary(50000L);

        when(lectorRepository.findLectorAndDepartmentsById(1L)).thenReturn(Optional.of(head));

        Department department = departmentParser.parse(template);

        assertNotNull(department);
        assertEquals("Computer Science", department.getName());
        assertEquals(head, department.getHead());
    }

    @Test
    void testParse_InvalidInput() {
        String template = "(name=Computer Science, headId=invalid)";

        assertThrows(ParseException.class, () -> departmentParser.parse(template));
    }

    @Test
    void testParse_InvalidHeadId() {
        String template = "(name=Computer Science, headId=1)";

        when(lectorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(LectorWasNotFoundException.class, () -> departmentParser.parse(template));
    }
}