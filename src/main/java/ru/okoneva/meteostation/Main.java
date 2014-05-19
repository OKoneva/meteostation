package ru.okoneva.meteostation;

import ru.okoneva.meteostation.data.ApplicationData;
import ru.okoneva.meteostation.service.city.XmlCityParser;
import ru.okoneva.meteostation.service.command.AddCityCommand;
import ru.okoneva.meteostation.service.command.ChangeFileCommand;
import ru.okoneva.meteostation.service.command.ChangePeriodCommand;
import ru.okoneva.meteostation.service.command.CitiesCommand;
import ru.okoneva.meteostation.service.command.CityCommand;
import ru.okoneva.meteostation.service.command.ExitCommand;
import ru.okoneva.meteostation.service.command.GetWeatherCommand;
import ru.okoneva.meteostation.service.command.JCommanderProvider;
import ru.okoneva.meteostation.service.command.StopCityCommand;
import ru.okoneva.meteostation.service.timermanager.TimerManager;
import ru.okoneva.meteostation.service.weather.checker.XmlWeatherChecker;
import ru.okoneva.meteostation.service.shell.Shell;

import java.io.Console;


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

        final JCommanderProvider provider = new JCommanderProvider();
        provider.addCommand(new AddCityCommand(applicationData, console));
        provider.addCommand(new ChangeFileCommand(applicationData, console));
        provider.addCommand(new ChangePeriodCommand(applicationData, console));
        provider.addCommand(new CitiesCommand(applicationData, console));
        provider.addCommand(new CityCommand(applicationData, console));
        provider.addCommand(new ExitCommand(applicationData, console));
        provider.addCommand(new GetWeatherCommand(applicationData, console));
        provider.addCommand(new StopCityCommand(applicationData, console));

        final Shell shell = new Shell(provider);
        shell.start(console);
    }
}
