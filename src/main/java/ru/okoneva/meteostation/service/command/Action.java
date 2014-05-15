package ru.okoneva.meteostation.service.command;

import org.apache.log4j.Logger;
import ru.okoneva.meteostation.data.ApplicationData;
import ru.okoneva.meteostation.service.command.parameters.InputParameterType;
import ru.okoneva.meteostation.service.command.parameters.ParameterValue;

import java.io.Console;
import java.util.Map;

/**
 * Created by Olga Koneva
 * Date: 08.04.2014:1:14
 * Version 1.0
 */
public abstract class Action {

    private static final Logger LOG = Logger.getLogger(Action.class);

    protected ApplicationData applicationData;
    protected Console console;
    protected Map<InputParameterType, ParameterValue> params;

    protected Action(
        final ApplicationData applicationData,
        final Console console,
        final Map<InputParameterType, ParameterValue> params
    ) {
        this.applicationData = applicationData;
        this.console = console;
        this.params = params;
    }

    public abstract void execute();
}
