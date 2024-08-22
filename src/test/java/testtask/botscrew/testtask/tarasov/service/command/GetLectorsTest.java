package testtask.botscrew.testtask.tarasov.service.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testtask.botscrew.testtask.tarasov.model.Department;
import testtask.botscrew.testtask.tarasov.model.Lector;
import testtask.botscrew.testtask.tarasov.repository.DepartmentRepository;
import testtask.botscrew.testtask.tarasov.repository.LectorRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class GetLectorsTest {

        private LectorRepository lectorRepository;
        private Command command;
        private final static String VALID_QUERY = "Show all lectors";
        private final static String INVALID_QUERY = "Show lectors all";

        @BeforeEach
        public void setUp() {
            lectorRepository = mock(LectorRepository.class);
            command = new GetLectors(lectorRepository);
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
            var lectors = List.of(new Lector(), new Lector());


            when(lectorRepository.findAll()).thenReturn(lectors);

            command.execute(VALID_QUERY);

            verify(lectorRepository, times(1)).findAll();
        }


}