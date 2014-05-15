package ru.okoneva.meteostation.service.command.parameters;

/**
 * @author Olga Koneva
 * @version 1.0 13 Mar 2014
 */
public class StringParameter extends ParameterValue {

    private String value;

    public StringParameter(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
