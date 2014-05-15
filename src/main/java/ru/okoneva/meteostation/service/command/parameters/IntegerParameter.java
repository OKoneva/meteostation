package ru.okoneva.meteostation.service.command.parameters;

/**
 * @author Olga Koneva
 * @version 1.0 13 Mar 2014
 */
public class IntegerParameter extends ParameterValue {

    private Integer value;

    public IntegerParameter(final Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
