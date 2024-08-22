package testtask.botscrew.testtask.tarasov.exception;

public class ParseException extends BusinessLogicException {
    public ParseException(String value) {
        super("cant parse query: " + value);
    }
}
