package ru.okoneva.meteostation.service.command;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import ru.okoneva.meteostation.data.ApplicationData;

import java.io.Console;

/**
 * Created by Olga Koneva
 * Date: 09.04.2014:1:32
 * Version 1.0
 */
@Parameters(commandDescription = "Change file name for the city", commandNames = "change_file")
public class ChangeFileCommand extends Action {

    @Parameter(names = "-n", description = "City name", required = true)
    private String cityName;

    @Parameter(names = "-f", description = "File name", required = true)
    private String fileName;

    public ChangeFileCommand(
        final ApplicationData applicationData,
        final Console console
    ) {
        super(applicationData, console);
    }

    @Override
    public void execute() {
        applicationData.changeFileName(cityName, fileName);
    }
}
