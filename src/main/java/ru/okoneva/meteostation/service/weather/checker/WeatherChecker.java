package ru.okoneva.meteostation.service.weather.checker;

import org.xml.sax.SAXException;
import ru.okoneva.meteostation.model.Weather;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by Olga Koneva
 * Date: 16.09.13:1:02
 * Version 1.0
 */
public interface WeatherChecker {

    public Weather retrieveWeather(final int cityId) throws IOException, ParserConfigurationException, SAXException;
}
