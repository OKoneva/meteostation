package ru.okoneva.meteostation.model;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Olga Koneva
 * Date: 15.09.13:1:53
 * Version 1.0
 */
public class City {

    private final String cityName;
    private final int cityId;
    private volatile String fileName;
    private volatile int surveyInterval = 30;

    public City(final String cityName, final int cityId, final int surveyInterval) {
        this.cityName = cityName;
        this.cityId = cityId;
        this.fileName = cityName + ".csv";
        this.surveyInterval = surveyInterval;
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    public void setSurveyInterval(final int surveyInterval) {
        this.surveyInterval = surveyInterval;
    }

    public String getCityName() {
        return cityName;
    }

    public String getFileName() {
        return fileName;
    }

    public int getSurveyInterval() {
        return surveyInterval;
    }

    public int getCityId() {
        return cityId;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("City{");
        sb.append("cityName='").append(cityName).append('\'');
        sb.append(", cityId=").append(cityId);
        sb.append(", fileName='").append(fileName).append('\'');
        sb.append(", surveyInterval=").append(surveyInterval);
        sb.append('}');
        return sb.toString();
    }

    public static String transformCityName(final String name) {
        return StringUtils.capitalize(StringUtils.lowerCase(name));
    }
}
