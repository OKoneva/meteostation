package ru.okoneva.meteostation.service.weather;

import ru.okoneva.meteostation.service.filewriter.MyFileWriter;
import ru.okoneva.meteostation.service.filewriter.SaveToFileException;

/**
 * Created by Olga Koneva
 * Date: 12.03.14:0:12
 * Version 1.0
 */
public class WeatherDisplayer implements Runnable {

    private String fileName;
    private String result;

    public WeatherDisplayer(final String fileName, final String result) {
        this.fileName = fileName;
        this.result = result;
    }

    @Override
    public void run() {
        final MyFileWriter fileWriter = new MyFileWriter();
        try {
            fileWriter.writeToCsv(fileName, result);
        } catch (SaveToFileException e) {
            System.out.println(e.getMessage());
        }
    }
}
