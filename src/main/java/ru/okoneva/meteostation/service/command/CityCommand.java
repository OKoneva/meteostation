package ru.okoneva.meteostation.service.command;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import ru.okoneva.meteostation.data.ApplicationData;

import java.io.Console;

/**
 * Created by Olga Koneva
 * Date: 09.04.2014:1:33
 * Version 1.0
 */
@Parameters(commandDescription = "Put out information about the city", commandNames = "city")
public class CityCommand extends Action {

    @Parameter(names = "-n", description = "City name", required = true)
    private String cityName;

    public CityCommand(
        final ApplicationData applicationData,
        final Console console
    ) {
        super(applicationData, console);
    }

    @Override
    public void execute() {
        console.printf(applicationData.retrieveCity(cityName) + "%n");
    }
}
