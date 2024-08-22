package testtask.botscrew.testtask.tarasov.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import testtask.botscrew.testtask.tarasov.exception.UnknownDegreeException;

@RequiredArgsConstructor
@Getter
public enum Degree {
    ASSISTANT("Assistant"),
    ASSOCIATE_PROFESSOR("Associate Professor"),
    PROFESSOR("Professor");

    private final String name;

    public static Degree fromString(String name) {
        for(var degree : values()) {
            if(degree.getName().equals(name)) return degree;
        } throw new UnknownDegreeException(name);
    }

    @Override
    public String toString() {
        return name;
    }
}