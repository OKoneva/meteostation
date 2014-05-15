package ru.okoneva.meteostation.service.command.parameters;

/**
 * @author Olga Koneva
 * @version 1.0 17 Mar 2014
 */
public class ParameterParsingException extends Exception {

    public ParameterParsingException(final String message) {
        super(message);
    }

    public ParameterParsingException(final String message, final Exception cause) {
        super(message, cause);
    }
}
