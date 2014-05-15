package ru.okoneva.meteostation.service.command;

import org.apache.log4j.Logger;
import ru.okoneva.meteostation.data.ApplicationData;
import ru.okoneva.meteostation.service.command.parameters.InputParameterType;
import ru.okoneva.meteostation.service.command.parameters.ParameterValue;

import java.io.Console;
import java.util.Map;

/**
 * Created by Olga Koneva
 * Date: 09.04.2014:1:25
 * Version 1.0
 */
public class AddCityCommand extends Action {
    private static final Logger LOG = Logger.getLogger(AddCityCommand.class);
    public AddCityCommand(
        final ApplicationData applicationData,
        final Console console,
        final Map<InputParameterType, ParameterValue> params
    ) {
        super(applicationData, console, params);
    }

    @Override
    public void execute() {
        LOG.info("AddCityCommand start");
        String cityName = (String) params.get(InputParameterType.CITY_NAME).getValue();
        final int surveyInterval = (Integer) params.get(InputParameterType.SURVEY_INTERVAL).getValue();
        applicationData.addCity(cityName, surveyInterval);
        LOG.info("AddCityCommand end");
    }
}
