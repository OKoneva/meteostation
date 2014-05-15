package ru.okoneva.meteostation.service.weather;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import ru.okoneva.meteostation.data.ApplicationData;
import ru.okoneva.meteostation.model.Weather;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


/**
 * @author Olga Koneva
 * @version 1.0 30.Jan.2014
 */
public class WeatherMonitor implements Runnable{

    private int cityId;
    private String cityName;
    private ApplicationData applicationData;

    private static final Logger LOG = Logger.getLogger(WeatherMonitor.class);

    public WeatherMonitor(final int cityId, final String cityName, final ApplicationData applicationData) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.applicationData = applicationData;
    }

    @Override
    public void run() {

        try {
            final Weather weather = applicationData.getWeatherChecker().retrieveWeather(cityId);
            if (weather != null) {
                applicationData.putWeather(cityName, weather);
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error("Application can't receive information from yandex server " + e.getMessage());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        } catch (SAXException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
    }
}
