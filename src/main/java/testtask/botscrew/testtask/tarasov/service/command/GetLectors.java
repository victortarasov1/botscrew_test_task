package testtask.botscrew.testtask.tarasov.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import testtask.botscrew.testtask.tarasov.repository.LectorRepository;

@Component
@RequiredArgsConstructor
public class GetLectors implements Command {
    private final LectorRepository lectorRepository;
    private final static String QUERY_PREFIX = "Show all lectors";
    @Override
    public void execute(String query) {
        lectorRepository.findAll().forEach(System.out::println);
    }

    @Override
    public boolean isCommandFits(String query) {
        return query.contains(QUERY_PREFIX);
    }
}
