package ru.okoneva.meteostation.service.city;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author Olga Koneva
 * @version 1.0 03.Feb.2014
 */
public class YandexCityParser implements CityParser{

    public int retrieveCityId(final String name)
        throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {

        InputStream inputStream = null;
        try {

            inputStream = new URL("http://weather.yandex.ru/static/cities.xml").openStream();

            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(inputStream);
            XPath xPath =  XPathFactory.newInstance().newXPath();
            final String expression = "//city[. = '" + name + "']/@id";
            String node = xPath.compile(expression).evaluate(document, XPathConstants.STRING).toString();
            if ("".equals(node)) {
                throw new IllegalArgumentException("City " + name + " isn't found in yandex list");
            }
            return Integer.valueOf(node);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}
