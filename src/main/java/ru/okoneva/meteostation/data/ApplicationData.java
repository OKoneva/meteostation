package ru.okoneva.meteostation.data;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import ru.okoneva.meteostation.service.city.CityParser;
import ru.okoneva.meteostation.service.weather.checker.WeatherChecker;
import ru.okoneva.meteostation.service.weather.WeatherDisplayer;
import ru.okoneva.meteostation.service.weather.WeatherMonitor;
import ru.okoneva.meteostation.service.timermanager.TimerManager;
import ru.okoneva.meteostation.model.City;
import ru.okoneva.meteostation.model.Weather;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
 * @author Olga Koneva
 * @version 1.0 03 Mar 2014
 */
public class ApplicationData {

    private static final Logger LOG = Logger.getLogger(ApplicationData.class);

    private final Object mutex = new Object();

    private final ConcurrentMap<String, Weather> weatherMap = new ConcurrentHashMap<String, Weather>();

    private final ConcurrentMap<String, City> cityMap = new ConcurrentHashMap<String, City>();

    private final TimerManager timerManager;

    private final CityParser cityParser;

    private final WeatherChecker weatherChecker;

    public ApplicationData(
        final CityParser cityParser,
        final WeatherChecker weatherChecker,
        final TimerManager timerManager
    ) {
        this.cityParser = cityParser;
        this.weatherChecker = weatherChecker;
        this.timerManager = timerManager;
    }

    public void stop() {
        timerManager.stop();
    }

    public void putWeather(final String cityName, final Weather weather) {

        synchronized (mutex) {
            final City city = cityMap.get(cityName);
            if (city != null) {
                weatherMap.put(cityName, weather);
                weatherChanged(cityName, weather);
            } else {
                LOG.info("City " + cityName + " doesn't presented in the list");
                throw new IllegalArgumentException("City " + cityName + " doesn't presented in the list");
            }
        }
    }

    private void weatherChanged(final String cityName, final Weather weather) {
        final String fileName = getFileName(cityName);
        final WeatherDisplayer weatherDisplayer = new WeatherDisplayer(fileName, weather.toFileRow());
        timerManager.execute(weatherDisplayer);
    }

    public Weather retrieveWeather(final String cityName) {
        return weatherMap.get(cityName);
    }

    public void addCity(final String cityName, final int surveyInterval) {

        final String transformedCityName = City.transformCityName(cityName);
        final int cityId;
        try {
            cityId = cityParser.retrieveCityId(transformedCityName);
            final City city = new City(transformedCityName, cityId, surveyInterval);
            synchronized (mutex) {
                if (cityMap.putIfAbsent(transformedCityName, city) == null) {
                    final WeatherMonitor monitor = new WeatherMonitor(city.getCityId(), cityName, this);
                    timerManager.scheduleWithFixedRate(monitor, city.getSurveyInterval(), cityName);
                    LOG.info("City " + transformedCityName + " is added");
                } else {
                    LOG.info("City " + transformedCityName + " already presented in the list");
                    throw new IllegalArgumentException("City " + transformedCityName +
                        " already presented in the list");
                }
            }
        } catch (ParserConfigurationException e) {
            LOG.error(e.getMessage());
        } catch (IOException e) {
            LOG.error(e.getMessage());
        } catch (SAXException e) {
            LOG.error(e.getMessage());
        } catch (XPathExpressionException e) {
            LOG.error(e.getMessage());
        }
    }

    public void removeCity(final String cityName) {

        synchronized (mutex) {
            final City city = cityMap.remove(cityName);
            if (city != null) {
                timerManager.cancelTask(cityName);
                weatherMap.remove(cityName);
                LOG.info("City " + cityName + " is removed from the list");
            } else {
                LOG.info("City " + cityName + " doesn't presented in the list");
                throw new IllegalArgumentException("City " + cityName + " doesn't presented in the list");
            }
        }
    }

    public void changeSurveyInterval(final String cityName, final int surveyInterval) {

        synchronized (mutex) {
            final City city = cityMap.get(cityName);
            if (city != null) {
                city.setSurveyInterval(surveyInterval);
                final WeatherMonitor monitor =
                    new WeatherMonitor(city.getCityId(), cityName, this);
                timerManager.scheduleWithFixedRate(monitor, city.getSurveyInterval(), cityName);
                LOG.info("SurveyInterval is set to " + surveyInterval + " for the City " + cityName);
            } else {
                LOG.info("City " + cityName + " doesn't presented in the list");
                throw new IllegalArgumentException("City " + cityName + " doesn't presented in the list");
            }
        }
    }

    public void changeFileName(final String cityName, final String fileName) {

        final City city = cityMap.get(cityName);
        if (city != null) {
            city.setFileName(fileName);
            LOG.info("File name for city " + cityName + " is set to " + fileName);
        } else {
            LOG.info("City " + cityName + " doesn't presented in the list");
            throw new IllegalArgumentException("City " + cityName + " doesn't presented in the list");
        }
    }

    public City retrieveCity(final String cityName) {

        final City city = cityMap.get(cityName);
        if (city != null) {
            return city;
        } else {
            LOG.info("City " + cityName + " doesn't presented in the list");
            throw new IllegalArgumentException("City " + cityName + " doesn't presented in the list");
        }
    }

    public String retrieveCitiesInfo() {

        synchronized (mutex) {
            final StringBuilder sb = new StringBuilder("City List:\n");
            for (City city : cityMap.values()) {
                sb.append(city).append("\n");
            }
            LOG.debug("retrieveCitiesInfo" + sb);
            return sb.toString();
        }
    }


    public String getFileName(final String cityName) {

        final City city = cityMap.get(cityName);
        if (city != null) {
            return city.getFileName();
        } else {
            LOG.info("City " + cityName + " doesn't presented in the list");
            throw new IllegalArgumentException("City " + cityName + " doesn't presented in the list");
        }
    }

    public WeatherChecker getWeatherChecker() {
        return weatherChecker;
    }
}