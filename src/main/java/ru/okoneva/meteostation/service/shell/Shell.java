package ru.okoneva.meteostation.service.shell;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.MissingCommandException;
import com.beust.jcommander.ParameterException;
import org.apache.log4j.Logger;
import ru.okoneva.meteostation.service.command.JCommanderProvider;
import ru.okoneva.meteostation.service.command.Action;

import java.io.Console;
import java.util.Date;

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

    private JCommanderProvider provider;

    public Shell(final JCommanderProvider provider) {
        this.provider = provider;
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

            final JCommander commander = provider.create();
            try {
                final String commandLine = console.readLine(PROMPT, new Date());
                commander.parse(commandLine.split(" "));
                ((Action) commander.getCommands().get(commander.getParsedCommand()).getObjects().get(0)).execute();
            } catch (MissingCommandException e){
                console.printf(UNKNOWN_COMMAND, e.getMessage());
                commander.usage();
                LOG.error(e.getMessage());
            } catch (ParameterException e){
                console.printf(WRONG_PARAMETER, e.getMessage());
                commander.usage(commander.getParsedCommand());
                LOG.error(e.getMessage());
            } catch (Exception e){
                console.printf(COMMAND_ERROR, commander.getParsedCommand(), e.getMessage());
                LOG.error(e.getMessage());
            }
        }
    }
}
