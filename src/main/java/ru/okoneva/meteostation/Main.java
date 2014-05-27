package ru.okoneva.meteostation;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import ru.okoneva.meteostation.data.ApplicationData;
import ru.okoneva.meteostation.service.city.CityParser;
import ru.okoneva.meteostation.service.city.XmlCityParser;
import ru.okoneva.meteostation.service.city.YandexCityParser;
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
import ru.okoneva.meteostation.service.weather.checker.WeatherChecker;
import ru.okoneva.meteostation.service.weather.checker.XmlWeatherChecker;
import ru.okoneva.meteostation.service.shell.Shell;
import ru.okoneva.meteostation.service.weather.checker.YandexWeatherChecker;

import java.io.Console;


/**
 * Created by Olga Koneva
 * Date: 16.09.13:0:27
 * Version 1.0
 */
public class Main {

    public static void main(String[] args) {

        final Config config = ConfigFactory.load();

        final WeatherChecker weatherChecker;
        final CityParser cityParser;
        if ("xml".equalsIgnoreCase(config.getString("weatherChecker.type"))) {
            weatherChecker = new XmlWeatherChecker();
        } else {
            weatherChecker = new YandexWeatherChecker();
        }

        if ("xml".equalsIgnoreCase(config.getString("cityParser.type"))) {
            cityParser = new XmlCityParser();
        } else {
            cityParser = new YandexCityParser();
        }


        final ApplicationData applicationData = new ApplicationData(
            cityParser,
            weatherChecker,
            new TimerManager()
        );

        for (Config particularConfig : config.getConfigList("defaultCitiesList")) {
            final String name = particularConfig.getString("name");
            final int period = particularConfig.getInt("period");
            applicationData.addCity(name, period);
        }

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
