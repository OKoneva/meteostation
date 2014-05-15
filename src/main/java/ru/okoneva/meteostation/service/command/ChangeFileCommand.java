package ru.okoneva.meteostation.service.command;

import ru.okoneva.meteostation.data.ApplicationData;
import ru.okoneva.meteostation.service.command.parameters.InputParameterType;
import ru.okoneva.meteostation.service.command.parameters.ParameterValue;

import java.io.Console;
import java.util.Map;

/**
 * Created by Olga Koneva
 * Date: 09.04.2014:1:32
 * Version 1.0
 */
public class ChangeFileCommand extends Action {

    public ChangeFileCommand(
        final ApplicationData applicationData,
        final Console console,
        final Map<InputParameterType, ParameterValue> params
    ) {
        super(applicationData, console, params);
    }

    @Override
    public void execute() {
        final String cityName = (String) params.get(InputParameterType.CITY_NAME).getValue();
        final String fileName = (String) params.get(InputParameterType.FILE_NAME).getValue();
        applicationData.changeFileName(cityName, fileName);
    }
}
