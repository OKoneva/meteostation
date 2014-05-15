package ru.okoneva.meteostation;

import ru.okoneva.meteostation.data.ApplicationData;
import ru.okoneva.meteostation.service.city.XmlCityParser;
import ru.okoneva.meteostation.service.timermanager.TimerManager;
import ru.okoneva.meteostation.service.weather.checker.XmlWeatherChecker;
import ru.okoneva.meteostation.service.command.CommandFactory;
import ru.okoneva.meteostation.service.shell.Shell;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Olga Koneva
 * Date: 16.09.13:0:27
 * Version 1.0
 */
public class Main {

    public static void main(String[] args) {
//        Map<String, Integer> cities = new HashMap<String, Integer>();
//        cities.put("омск", 10);
//        cities.put("новосибирск", 20);

        final ApplicationData applicationData = new ApplicationData(
            new XmlCityParser(),
            new XmlWeatherChecker(),
            new TimerManager()
        );

//
//        for (Map.Entry<String, Integer> entry: cities.entrySet()) {
//            applicationData.addCity(entry.getKey(), entry.getValue());
//        }

        Console console = System.console();

        final CommandFactory commandFactory = new CommandFactory(applicationData, console);

        final Shell shell = new Shell(commandFactory);
        shell.start(console);
    }
}
