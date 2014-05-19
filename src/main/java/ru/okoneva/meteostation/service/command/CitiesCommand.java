package ru.okoneva.meteostation.service.command;

import com.beust.jcommander.Parameters;
import org.apache.log4j.Logger;
import ru.okoneva.meteostation.data.ApplicationData;

import java.io.Console;

/**
 * Created by Olga Koneva
 * Date: 09.04.2014:1:34
 * Version 1.0
 */
@Parameters(commandDescription = "Put out information about all cities", commandNames = "cities")
public class CitiesCommand extends Action {
    private static final Logger LOG = Logger.getLogger(CitiesCommand.class);
    public CitiesCommand(
        final ApplicationData applicationData,
        final Console console
    ) {
        super(applicationData, console);
    }

    @Override
    public void execute() {
        LOG.info("CitiesCommand start");
        console.printf(applicationData.retrieveCitiesInfo() + "%n");
        console.flush();
        LOG.info("CitiesCommand end");
    }
}
