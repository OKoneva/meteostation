package ru.okoneva.meteostation.service.command;

import org.apache.log4j.Logger;
import ru.okoneva.meteostation.data.ApplicationData;
import ru.okoneva.meteostation.service.command.parameters.InputParameterType;
import ru.okoneva.meteostation.service.command.parameters.ParameterValue;

import java.io.Console;
import java.util.Map;

/**
 * Created by Olga Koneva
 * Date: 09.04.2014:1:34
 * Version 1.0
 */
public class CitiesCommand extends Action {
    private static final Logger LOG = Logger.getLogger(CitiesCommand.class);
    public CitiesCommand(
        final ApplicationData applicationData,
        final Console console,
        final Map<InputParameterType, ParameterValue> params
    ) {
        super(applicationData, console, params);
    }

    @Override
    public void execute() {
        LOG.info("CitiesCommand start");
        console.printf(applicationData.retrieveCitiesInfo() + "%n");
        console.flush();
        LOG.info("CitiesCommand end");
    }
}
