package ru.okoneva.meteostation.service.command;

import ru.okoneva.meteostation.data.ApplicationData;
import ru.okoneva.meteostation.service.command.parameters.InputParameterType;
import ru.okoneva.meteostation.service.command.parameters.ParameterValue;

import java.io.Console;
import java.util.Map;

/**
 * Created by Olga Koneva
 * Date: 09.04.2014:1:35
 * Version 1.0
 */
public class CommandFactory {

    private ApplicationData applicationData;
    private Console console;

    public CommandFactory(
        final ApplicationData applicationData,
        final Console console
    ) {
        this.applicationData = applicationData;
        this.console = console;
    }

    public Action createCommand(
        final CommandType commandType,
        final Map<InputParameterType, ParameterValue> params
    ) {
        switch (commandType) {
            case ADD_CITY:
                return new AddCityCommand(applicationData, console, params);
            case CHANGE_FILE:
                return new ChangeFileCommand(applicationData, console, params);
            case CHANGE_PERIOD:
                return new ChangePeriodCommand(applicationData, console, params);
            case CITIES:
                return new CitiesCommand(applicationData, console, params);
            case CITY:
                return new CityCommand(applicationData, console, params);
            case EXIT:
                return new ExitCommand(applicationData, console, params);
            case GET_WEATHER:
                return new GetWeatherCommand(applicationData, console, params);
            case STOP_CITY:
                return new StopCityCommand(applicationData, console, params);
            default:
                throw new IllegalArgumentException("Unknown command " + commandType);
        }
    }
}
