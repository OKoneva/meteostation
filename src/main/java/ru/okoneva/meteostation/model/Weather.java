package ru.okoneva.meteostation.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Olga Koneva
 * Date: 15.09.13:2:15
 * Version 1.0
 */
public class Weather {

    public int temperature;
    public String weatherCondition;
    public Float windSpeed;
    public int humidity;
    public int pressure;

    public void setWhether(final int temperature,
                           final String weatherCondition,
                           final Float windSpeed,
                           final int humidity,
                           final int pressure) {
        this.temperature = temperature;
        this.weatherCondition = weatherCondition;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(final int temperature) {
        this.temperature = temperature;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(final String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public Float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(final Float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(final int humidity) {
        this.humidity = humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(final int pressure) {
        this.pressure = pressure;
    }


    public String toFileRow() {
        final StringBuilder sb = new StringBuilder(new SimpleDateFormat("dd.MM.yyyy_HH:mm:ss").format(Calendar.getInstance().getTime()));
        sb.append(",").append(temperature);
        sb.append(",").append(weatherCondition);
        sb.append(",").append(windSpeed);
        sb.append(",").append(humidity);
        sb.append(",").append(pressure);
        return sb.toString();
    }

    public static String getHeader() {
        final StringBuilder sb = new StringBuilder("Survey time");
        sb.append(",").append("Temperature");
        sb.append(",").append("Weather Condition");
        sb.append(",").append("Wind Speed");
        sb.append(",").append("Humidity");
        sb.append(",").append("Pressure");
        return sb.toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Weather{");
        sb.append("temperature=").append(temperature);
        sb.append(", weatherCondition='").append(weatherCondition).append('\'');
        sb.append(", windSpeed=").append(windSpeed);
        sb.append(", humidity=").append(humidity);
        sb.append(", pressure=").append(pressure);
        sb.append('}');
        return sb.toString();
    }
}
