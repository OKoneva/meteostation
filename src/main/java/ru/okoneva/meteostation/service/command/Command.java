package ru.okoneva.meteostation.service.command;

/**
 * Created by Olga Koneva
 * Date: 09.09.13:23:56
 * Version 1.0
 */

import org.apache.log4j.Logger;
import ru.okoneva.meteostation.data.ApplicationData;
import ru.okoneva.meteostation.model.Weather;
import ru.okoneva.meteostation.service.command.parameters.InputParameterType;
import ru.okoneva.meteostation.service.command.parameters.ParameterValue;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public enum Command {
    ADD_CITY(
        new Action() {
            @Override
            public void execute(Console console, Map<InputParameterType, ParameterValue> params) {

                String cityName = (String) params.get(InputParameterType.CITY_NAME).getValue();
                final int surveyInterval = (Integer) params.get(InputParameterType.SURVEY_INTERVAL).getValue();
                applicationData.addCity(cityName, surveyInterval);
            }
        },
        Arrays.asList(InputParameterType.CITY_NAME, InputParameterType.SURVEY_INTERVAL)
    ),
    GET_WEATHER(
        new Action() {
            @Override
            public void execute(Console console, Map<InputParameterType, ParameterValue> params) {
                final String cityName = (String) params.get(InputParameterType.CITY_NAME).getValue();
                final Weather weather = applicationData.retrieveWeather(cityName);
                console.printf("Current weather in the " + cityName + ": " + weather + "%n");
            }
        },
        Arrays.asList(InputParameterType.CITY_NAME)
    ),
    EXIT(
        new Action() {
            @Override
            public void execute(Console console, Map<InputParameterType, ParameterValue> params) {
                console.printf("Bye%n");
                System.exit(0);
            }
        },
        new ArrayList<InputParameterType>()
    ),
    STOP_CITY(
        new Action() {
            @Override
            public void execute(Console console, Map<InputParameterType, ParameterValue> params) {
                final String cityName = (String) params.get(InputParameterType.CITY_NAME).getValue();
                applicationData.removeCity(cityName);
            }
        },
        Arrays.asList(InputParameterType.CITY_NAME)
    ),
    CHANGE_PERIOD(
        new Action() {
            @Override
            public void execute(Console console, Map<InputParameterType, ParameterValue> params) {
                final String cityName = (String) params.get(InputParameterType.CITY_NAME).getValue();
                final int surveyInterval = (Integer) params.get(InputParameterType.SURVEY_INTERVAL).getValue();
                applicationData.changeSurveyInterval(cityName, surveyInterval);
            }
        },
        Arrays.asList(InputParameterType.CITY_NAME, InputParameterType.SURVEY_INTERVAL)
    ),
    CHANGE_FILE(
        new Action() {
            @Override
            public void execute(Console console, Map<InputParameterType, ParameterValue> params) {
                final String cityName = (String) params.get(InputParameterType.CITY_NAME).getValue();
                final String fileName = (String) params.get(InputParameterType.FILE_NAME).getValue();
                applicationData.changeFileName(cityName, fileName);
            }
        },
        Arrays.asList(InputParameterType.CITY_NAME, InputParameterType.FILE_NAME)
    ),
    CITY(
        new Action() {
            @Override
            public void execute(Console console, Map<InputParameterType, ParameterValue> params) {
                final String cityName = (String) params.get(InputParameterType.CITY_NAME).getValue();
                console.printf(applicationData.retrieveCity(cityName) + "%n");

            }
        },
        Arrays.asList(InputParameterType.CITY_NAME)
    ),
    CITIES(
        new Action() {
            @Override
            public void execute(Console console, Map<InputParameterType, ParameterValue> params) {
                console.printf(applicationData.retrieveCitiesInfo() + "%n");

            }
        },
        new ArrayList<InputParameterType>()
    );

    private Action action;

    private List<InputParameterType> parameters;

    private static final Logger LOG = Logger.getLogger(Command.class);

    private static ApplicationData applicationData;

    public static void setApplicationData(final ApplicationData data) {
        applicationData = data;
    }

    Command(final Action action, final List<InputParameterType> parameters) {
        this.action = action;
        this.parameters = parameters;
    }

    public interface Listener {
        public void exception(Exception e);
    }

    public void execute(final Console c, final Map<InputParameterType, ParameterValue> params, final Listener listener) {
        try {
            action.execute(c, params);
        } catch (Exception e){
            listener.exception(e);
        }
    }

    /**
     * @author Olga Koneva
     * @version 1.0 03.Feb.2014
     */
    public static interface Action {
        public void execute(Console console, Map<InputParameterType, ParameterValue> params);
    }

    public List<InputParameterType> getParameters() {
        return parameters;
    }
}
