package testtask.botscrew.testtask.tarasov.exception;

public class DepartmentWasNotFoundException extends BusinessLogicException {
    public DepartmentWasNotFoundException(String name) {
        super("department with this name: " + name + " was not found");
    }
}
