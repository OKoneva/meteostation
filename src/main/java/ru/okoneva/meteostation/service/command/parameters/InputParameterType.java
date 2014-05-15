package ru.okoneva.meteostation.service.command.parameters;

import ru.okoneva.meteostation.model.City;

/**
 * @author Olga Koneva
 * @version 1.0 30.Jan.2014
 */
public enum InputParameterType {
    CITY_NAME(
        "City name",
        new ParameterConstructor() {
            @Override
            public ParameterValue transformStringToParameter(final String inputValue) throws ParameterParsingException {
                if (inputValue == null) {
                    throw new ParameterParsingException("Parameter value shouldn't be null");
                } else {
                    return new StringParameter(City.transformCityName(inputValue));
                }
            }
        }
    ),
    FILE_NAME(
        "File name",
        new ParameterConstructor() {
            @Override
            public ParameterValue transformStringToParameter(final String inputValue) throws ParameterParsingException {
                if (inputValue == null) {
                    throw new ParameterParsingException("Parameter value shouldn't be null");
                } else {
                    return new StringParameter(inputValue);
                }
            }
        }
    ),
    SURVEY_INTERVAL(
        "Survey Interval",
        new ParameterConstructor() {
            @Override
            public ParameterValue transformStringToParameter(final String inputValue) throws ParameterParsingException {
                try {
                    if (inputValue == null) {
                        throw new IllegalArgumentException("Parameter value shouldn't be null");
                    } else {
                        return new IntegerParameter(Integer.valueOf(inputValue));
                    }
                } catch (IllegalArgumentException e) {
                    throw new ParameterParsingException("'" + inputValue + "' value of 'Survey Interval' parameter " +
                        "should have Integer type", e);
                }
            }
        }
    );

    private String name;

    private ParameterConstructor parameterConstructor;

    InputParameterType(final String name, final ParameterConstructor parameterConstructor) {
        this.name = name;
        this.parameterConstructor = parameterConstructor;
    }

    public String getName() {
        return name;
    }

    public ParameterValue transformStringToParameter(final String inputValue) throws ParameterParsingException {
        return parameterConstructor.transformStringToParameter(inputValue);
    }

    public static interface ParameterConstructor {
        public ParameterValue transformStringToParameter(String inputValue) throws ParameterParsingException;
    }
}
