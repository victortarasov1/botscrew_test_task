package testtask.botscrew.testtask.tarasov.service.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testtask.botscrew.testtask.tarasov.exception.ParseException;
import testtask.botscrew.testtask.tarasov.exception.UnknownDegreeException;
import testtask.botscrew.testtask.tarasov.model.Degree;

import static org.junit.jupiter.api.Assertions.*;

class LectorParserImplTest {



    private LectorParser lectorParser;
    @BeforeEach
    void setUp() {
        lectorParser = new LectorParserImpl();
    }

    @Test
    void testParse_ValidInput() {
        var template = "(name=John Doe, degree=Assistant, salary=50000)";
        var lector = lectorParser.parse(template);

        assertNotNull(lector);
        assertEquals("John Doe", lector.getName());
        assertEquals(Degree.ASSISTANT, lector.getDegree());
        assertEquals(50000L, lector.getSalary());
    }

    @Test
    void testParse_InvalidInput() {
        var template = "(name=John Doe, degree=Assistant)"; // Missing salary

        assertThrows(ParseException.class, () -> lectorParser.parse(template));
    }

    @Test
    void testParse_InvalidDegree() {
        var template = "(name=John Doe, degree=UnknownDegree, salary=50000)";

        assertThrows(UnknownDegreeException.class, () -> lectorParser.parse(template));
    }
}