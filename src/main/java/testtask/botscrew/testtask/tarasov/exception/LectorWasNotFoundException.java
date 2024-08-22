package testtask.botscrew.testtask.tarasov.exception;

public class LectorWasNotFoundException extends BusinessLogicException {
    public LectorWasNotFoundException(Long id) {
        super("lector with id: " + id + " was not found");
    }
}
