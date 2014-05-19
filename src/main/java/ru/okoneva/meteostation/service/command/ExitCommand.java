package ru.okoneva.meteostation.service.command;

import com.beust.jcommander.Parameters;
import ru.okoneva.meteostation.data.ApplicationData;

import java.io.Console;

/**
 * Created by Olga Koneva
 * Date: 09.04.2014:1:27
 * Version 1.0
 */
@Parameters(commandDescription = "Exit", commandNames = "exit")
public class ExitCommand extends Action {

    public ExitCommand(
        final ApplicationData applicationData,
        final Console console
    ) {
        super(applicationData, console);
    }

    @Override
    public void execute() {
        applicationData.stop();
        console.printf("Bye%n");
        System.exit(0);
    }
}
