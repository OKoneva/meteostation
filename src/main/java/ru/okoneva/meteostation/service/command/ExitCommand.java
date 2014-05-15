package ru.okoneva.meteostation.service.command;

import ru.okoneva.meteostation.data.ApplicationData;
import ru.okoneva.meteostation.service.command.parameters.InputParameterType;
import ru.okoneva.meteostation.service.command.parameters.ParameterValue;

import java.io.Console;
import java.util.Map;

/**
 * Created by Olga Koneva
 * Date: 09.04.2014:1:27
 * Version 1.0
 */
public class ExitCommand extends Action {

    public ExitCommand(
        final ApplicationData applicationData,
        final Console console,
        final Map<InputParameterType, ParameterValue> params
    ) {
        super(applicationData, console, params);
    }

    @Override
    public void execute() {
        applicationData.stop();
        console.printf("Bye%n");
        System.exit(0);
    }
}
