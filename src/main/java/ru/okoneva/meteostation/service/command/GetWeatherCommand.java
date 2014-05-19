package ru.okoneva.meteostation.service.command;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import ru.okoneva.meteostation.data.ApplicationData;
import ru.okoneva.meteostation.model.Weather;

import java.io.Console;

/**
 * Created by Olga Koneva
 * Date: 09.04.2014:1:26
 * Version 1.0
 */
@Parameters(commandDescription = "Get current weather for the city", commandNames = "get_weather")
public class GetWeatherCommand extends Action {

    @Parameter(names = "-n", description = "City name", required = true)
    private String cityName;

    public GetWeatherCommand(
        final ApplicationData applicationData,
        final Console console
    ) {
        super(applicationData, console);
    }

    @Override
    public void execute() {
        final Weather weather = applicationData.retrieveWeather(cityName);
        console.printf("Current weather in the " + cityName + ": " + weather + "%n");
    }
}
