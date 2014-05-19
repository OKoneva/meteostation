package ru.okoneva.meteostation.service.command;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import ru.okoneva.meteostation.data.ApplicationData;

import java.io.Console;

/**
 * Created by Olga Koneva
 * Date: 09.04.2014:1:29
 * Version 1.0
 */
@Parameters(commandDescription = "Stops survey the city", commandNames = "stop_city")
public class StopCityCommand extends Action {

    @Parameter(names = "-n", description = "City name", required = true)
    private String cityName;

    public StopCityCommand(
        final ApplicationData applicationData,
        final Console console
    ) {
        super(applicationData, console);
    }

    @Override
    public void execute() {

        applicationData.removeCity(cityName);
    }
}
