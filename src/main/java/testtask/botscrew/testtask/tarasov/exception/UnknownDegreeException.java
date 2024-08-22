package testtask.botscrew.testtask.tarasov.exception;

public class UnknownDegreeException extends BusinessLogicException {
    public UnknownDegreeException(String name) {
        super("Unknown degree: " + name);
    }
}
