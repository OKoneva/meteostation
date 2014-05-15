package ru.okoneva.meteostation.service.filewriter;


public class SaveToFileException extends Exception {

    public SaveToFileException(final String message) {
        super(message);
    }

    public SaveToFileException(final String message, final Exception cause) {
        super(message, cause);
    }
}
