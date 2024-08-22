package testtask.botscrew.testtask.tarasov.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import testtask.botscrew.testtask.tarasov.repository.LectorRepository;
import testtask.botscrew.testtask.tarasov.service.parser.LectorParser;

@Component
@RequiredArgsConstructor
public class AddLector implements Command {
    private final static String QUERY_PREFIX = "Add lector ";
    private final LectorParser parser;
    private final LectorRepository lectorRepository;

    @Override
    public void execute(String query) {
        var lector = parser.parse(query);
        lectorRepository.save(lector);

    }

    @Override
    public boolean isCommandFits(String query) {
        return query.contains(QUERY_PREFIX);
    }
}
