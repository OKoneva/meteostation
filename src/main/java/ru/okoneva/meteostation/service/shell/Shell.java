package ru.okoneva.meteostation.service.shell;

import org.apache.log4j.Logger;
import ru.okoneva.meteostation.service.command.Action;
import ru.okoneva.meteostation.service.command.CommandFactory;
import ru.okoneva.meteostation.service.command.CommandType;
import ru.okoneva.meteostation.service.command.parameters.InputParameterType;
import ru.okoneva.meteostation.service.command.parameters.ParameterParsingException;
import ru.okoneva.meteostation.service.command.parameters.ParameterValue;

import java.io.Console;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Olga Koneva
 * Date: 09.09.13:23:54
 * Version 1.0
 */


public class Shell {
    private static final String NO_CONSOLE = "Error: Console unavailable";
    private static final String UNKNOWN_COMMAND = "Unknown command [%1$s]%n";
    private static final String WRONG_PARAMETER = "Wrong parameter [%1$s]%n";
    private static final String COMMAND_ERROR = "Action error [%1$s]: [%2$s]%n";

    private static final String TIME_FORMAT = "%1$tH:%1$tM:%1$tS";
    private static final String PROMPT = TIME_FORMAT + " $ ";

    private static final Logger LOG = Logger.getLogger(Action.class);

    private CommandFactory commandFactory;

    public Shell(final CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    public void start(final Console console) {
        if (console != null) {
            execCommandLoop(console);
        } else {
            throw new RuntimeException(NO_CONSOLE);
        }
    }

    private void execCommandLoop(final Console console) {
        while (true) {
            String commandLine = console.readLine(PROMPT, new Date());
            Scanner scanner = new Scanner(commandLine);

            if (scanner.hasNext()) {
                final String commandName = scanner.next().toUpperCase();
                LOG.info(commandName);
                try {
                    final CommandType commandType = Enum.valueOf(CommandType.class, commandName);
                    final Map<InputParameterType, ParameterValue> params =
                        new HashMap<InputParameterType, ParameterValue>();

                    for (InputParameterType param : commandType.getParameters()) {
                        final String nextInputVal = scanner.hasNext() ? scanner.next() : null;
                        params.put(param, param.transformStringToParameter(nextInputVal));
                    }

                    Action action = commandFactory.createCommand(
                        commandType,
                        params
                    );
                    try {
                        action.execute();
                    } catch (Exception e){
                        console.printf(COMMAND_ERROR, commandType, e.getMessage());
                        LOG.error(e.getMessage());
                    }

                } catch (IllegalArgumentException e) {
                    console.printf(UNKNOWN_COMMAND, commandName);
                } catch (ParameterParsingException e) {
                    console.printf(WRONG_PARAMETER, e.getMessage());
                }
            }
            scanner.close();
        }
    }
}
