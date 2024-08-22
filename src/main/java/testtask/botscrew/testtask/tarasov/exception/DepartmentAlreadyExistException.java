package testtask.botscrew.testtask.tarasov.exception;

public class DepartmentAlreadyExistException extends BusinessLogicException {
    public DepartmentAlreadyExistException(String name) {
        super("department with name: "  + name + " already exist");

    }
}
