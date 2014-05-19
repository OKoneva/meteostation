package ru.okoneva.meteostation.service.command;

import ru.okoneva.meteostation.data.ApplicationData;

import java.io.Console;

/**
 * Created by Olga Koneva
 * Date: 08.04.2014:1:14
 * Version 1.0
 */
public abstract class Action {

    protected ApplicationData applicationData;
    protected Console console;

    protected Action(
        final ApplicationData applicationData,
        final Console console
    ) {
        this.applicationData = applicationData;
        this.console = console;
    }

    public abstract void execute();
}
