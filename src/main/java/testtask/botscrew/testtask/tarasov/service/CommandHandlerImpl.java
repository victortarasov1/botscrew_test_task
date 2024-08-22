package testtask.botscrew.testtask.tarasov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import testtask.botscrew.testtask.tarasov.exception.BusinessLogicException;
import testtask.botscrew.testtask.tarasov.exception.CommandNotFoundException;
import testtask.botscrew.testtask.tarasov.service.command.Command;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandHandlerImpl implements CommandHandler {
    private final List<Command> commands;
    @Override
    public void execute(String query) {
        try {
            executeQuery(query);
        } catch (BusinessLogicException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void executeQuery(String query) {
        var validatedQuery = query.trim().replaceAll("\\s+", " ");
        var command = commands.stream().filter(c -> c.isCommandFits(validatedQuery)).findFirst().orElseThrow(() -> new CommandNotFoundException(validatedQuery));
        command.execute(validatedQuery);
    }



}
