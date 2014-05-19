package ru.okoneva.meteostation.service.command;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import ru.okoneva.meteostation.data.ApplicationData;

import java.io.Console;

/**
 * Created by Olga Koneva
 * Date: 09.04.2014:1:31
 * Version 1.0
 */
@Parameters(commandDescription = "Change survey interval for the city", commandNames = "change_period")
public class ChangePeriodCommand extends Action {

    @Parameter(names = "-n", description = "City name", required = true)
    private String cityName;

    @Parameter(names = "-p", description = "Survey interval", required = true)
    private int surveyInterval;

    public ChangePeriodCommand(
        final ApplicationData applicationData,
        final Console console
    ) {
        super(applicationData, console);
    }

    @Override
    public void execute() {
        applicationData.changeSurveyInterval(cityName, surveyInterval);
    }
}
