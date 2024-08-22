package testtask.botscrew.testtask.tarasov.exception;

public class CommandNotFoundException extends BusinessLogicException {
    public CommandNotFoundException(String message) {
        super("didn't find a command that matches: " + message);
    }
}
