package ru.okoneva.meteostation.service.city;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

/**
 * @author Olga Koneva
 * @version 1.0 19 Mar 2014
 */
public interface CityParser {

    public int retrieveCityId(final String name)
        throws ParserConfigurationException, IOException, SAXException, XPathExpressionException;
}
