package testtask.botscrew.testtask.tarasov.service.command;

public interface Command {
    void execute(String query);

    boolean isCommandFits(String query);
}
