package testtask.botscrew.testtask.tarasov.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testtask.botscrew.testtask.tarasov.service.command.Command;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CommandHandlerImplTest {
    private CommandHandler handler;
    private Command command;

    private final static String QUERY = "some query";

    @BeforeEach
    public void setUp() {
        command = mock(Command.class);
        handler = new CommandHandlerImpl(List.of(command));
    }

    @Test
    public void testExecute() {
        when(command.isCommandFits(anyString())).thenReturn(true);

        handler.execute(QUERY);

        verify(command, times(1)).execute(anyString());

    }


    @Test
    public void testExecute_shouldMakeQueryValid() {
        var unvalidatedQuery = " some     query";
        var validatedQuery = "some query";

        when(command.isCommandFits(anyString())).thenReturn(true);

        handler.execute(unvalidatedQuery);

        verify(command, times(1)).execute(eq(validatedQuery));

    }

    @Test
    public void testExecute_shouldHandleException() {
        when(command.isCommandFits(anyString())).thenReturn(false);

        handler.execute(QUERY);

    }


}