package ru.okoneva.meteostation.service.command;

import com.beust.jcommander.Parameters;
import org.apache.log4j.Logger;
import com.beust.jcommander.Parameter;
import ru.okoneva.meteostation.data.ApplicationData;

import java.io.Console;

/**
 * Created by Olga Koneva
 * Date: 09.04.2014:1:25
 * Version 1.0
 */
@Parameters(commandDescription = "Add city", commandNames = "add_city")
public class AddCityCommand extends Action {

    private static final Logger LOG = Logger.getLogger(AddCityCommand.class);

    @Parameter(names = "-n", description = "City name", required = true)
    private String cityName;

    @Parameter(names = "-p", description = "Survey interval", required = true)
    private int surveyInterval;

    public AddCityCommand(
        final ApplicationData applicationData,
        final Console console
    ) {
        super(applicationData, console);
    }

    @Override
    public void execute() {
        LOG.info("AddCityCommand start");
        applicationData.addCity(cityName, surveyInterval);
        LOG.info("AddCityCommand end");
    }
}
