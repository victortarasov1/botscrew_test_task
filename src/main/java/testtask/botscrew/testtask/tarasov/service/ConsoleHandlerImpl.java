package testtask.botscrew.testtask.tarasov.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;

@Service
@RequiredArgsConstructor
public class ConsoleHandlerImpl implements ConsoleHandler {
    private final BufferedReader reader;
    private final CommandHandler handler;

    @Override
    @SneakyThrows
    public void readQuery() {
        var line = "menu";
        while (!line.equals("exit")) {
            handler.execute(line);
            line = reader.readLine();
        }
    }

}
