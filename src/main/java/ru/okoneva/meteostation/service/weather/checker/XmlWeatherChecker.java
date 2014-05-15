package ru.okoneva.meteostation.service.weather.checker;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.okoneva.meteostation.model.Weather;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/**
 * @author Olga Koneva
 * @version 1.0 19 Mar 2014
 */
public class XmlWeatherChecker implements WeatherChecker {

    private Random random = new Random();

    public synchronized Weather retrieveWeather(final int cityId) throws IOException, ParserConfigurationException, SAXException {

        Weather result = new Weather();

        InputStream inputStream = null;
        try {
            inputStream = YandexWeatherChecker.class.getResourceAsStream("/weatherExample.xml");
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(inputStream);
            document.getDocumentElement().normalize();
            NodeList nodeList = getFactWeather(document);
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if ("temperature".equals(node.getNodeName())) {
                    result.setTemperature(Integer.valueOf(node.getTextContent()));
                } else if ("weather_type".equals(node.getNodeName())) {
                    result.setWeatherCondition(node.getTextContent());
                } else if ("wind_speed".equals(node.getNodeName())) {
                    result.setWindSpeed(Float.valueOf(node.getTextContent()));
                } else if ("humidity".equals(node.getNodeName())) {
                    result.setHumidity(Integer.valueOf(node.getTextContent()));
                } else if ("pressure".equals(node.getNodeName())) {
                    result.setPressure(Integer.valueOf(node.getTextContent()));
                }
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return result;
    }

    private NodeList getFactWeather(final Document document) {
        NodeList nodeList = document.getElementsByTagName("forecast").item(0).getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if ("fact".equals(node.getNodeName())) {
                return node.getChildNodes();
            }
        }
        return null;
    }
}
