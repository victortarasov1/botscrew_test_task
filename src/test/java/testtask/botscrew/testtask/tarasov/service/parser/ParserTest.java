package testtask.botscrew.testtask.tarasov.service.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testtask.botscrew.testtask.tarasov.exception.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    private Parser parser;
    @BeforeEach
    public void setUp() {
        parser = v -> v;
    }

    @Test
    void testParseLong_ValidNumber() {
        assertEquals(123L, parser.parseLong("id=123"));
    }

    @Test
    void testParseLong_InvalidNumber() {
        assertThrows(ParseException.class, () -> parser.parseLong("id=abc"));
    }

    @Test
    void testGetValue_ValidFormat() {
        assertEquals("value", parser.getValue("key=value"));
    }

    @Test
    void testSplitTemplate_ValidFormat() {
        String[] result = parser.splitTemplate("(name=John, age=30)");
        assertArrayEquals(new String[]{"name=John", "age=30"}, result);
    }

    @Test
    void testSplitTemplate_InvalidFormat() {
        assertThrows(ParseException.class, () -> parser.splitTemplate("name=John, age=30"));
    }
}