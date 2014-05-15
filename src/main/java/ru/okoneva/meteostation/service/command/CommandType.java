package ru.okoneva.meteostation.service.command;

/**
 * Created by Olga Koneva
 * Date: 09.09.13:23:56
 * Version 2.0
 */

import ru.okoneva.meteostation.service.command.parameters.InputParameterType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum CommandType {
    ADD_CITY(Arrays.asList(InputParameterType.CITY_NAME, InputParameterType.SURVEY_INTERVAL)),
    GET_WEATHER(Arrays.asList(InputParameterType.CITY_NAME)),
    EXIT(new ArrayList<InputParameterType>()),
    STOP_CITY(Arrays.asList(InputParameterType.CITY_NAME)),
    CHANGE_PERIOD(Arrays.asList(InputParameterType.CITY_NAME, InputParameterType.SURVEY_INTERVAL)),
    CHANGE_FILE(Arrays.asList(InputParameterType.CITY_NAME, InputParameterType.FILE_NAME)),
    CITY(Arrays.asList(InputParameterType.CITY_NAME)),
    CITIES(new ArrayList<InputParameterType>());

    private List<InputParameterType> parameters;

    CommandType(final List<InputParameterType> parameters) {
        this.parameters = parameters;
    }

    public List<InputParameterType> getParameters() {
        return parameters;
    }
}
