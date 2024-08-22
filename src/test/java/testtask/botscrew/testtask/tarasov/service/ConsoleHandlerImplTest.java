package testtask.botscrew.testtask.tarasov.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.mockito.Mockito.*;

class ConsoleHandlerImplTest {
    private BufferedReader bufferedReader;
    private CommandHandler commandHandler;

    private ConsoleHandler consoleHandler;

    @BeforeEach
    public void setUp() {
        bufferedReader = mock(BufferedReader.class);
        commandHandler = mock(CommandHandler.class);
        consoleHandler = new ConsoleHandlerImpl(bufferedReader, commandHandler);
    }

    @Test
    public void testReadQuery_shouldExit() throws IOException {
        when(bufferedReader.readLine()).thenReturn("exit");

        consoleHandler.readQuery();

        verify(bufferedReader, times(1)).readLine();

    }

    @Test
    public void testReadQuery_shouldExecuteHandle() throws IOException {
        when(bufferedReader.readLine()).thenReturn("some query", "exit");

        consoleHandler.readQuery();

        verify(bufferedReader, times(2)).readLine();
        verify(commandHandler, times(1)).execute(anyString());
    }

    @Test
    public void testReadQuery_shouldPrintMenu() throws IOException {
        when(bufferedReader.readLine()).thenReturn("menu", "exit");

        consoleHandler.readQuery();

        verify(bufferedReader, times(2)).readLine();
        verify(commandHandler, times(0)).execute(anyString());
    }

}