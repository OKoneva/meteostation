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

/**
 * @author Olga Koneva
 * @version 1.0 19 Mar 2014
 */
public class XmlCityParser implements CityParser {

    @Override
    public synchronized int retrieveCityId(final String name)
        throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        InputStream inputStream = null;
        try {

            inputStream = CityParser.class.getResourceAsStream("/yandexCity.xml");
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(inputStream);
            XPath xPath = XPathFactory.newInstance().newXPath();
            final String expression = "//city[. = '" + name + "']/@id";
            String node = xPath.compile(expression).evaluate(document, XPathConstants.STRING).toString();
            if ("".equals(node)) {
                throw new IllegalArgumentException("City " + name + " isn't found in xml file");
            }
            return Integer.valueOf(node);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}
