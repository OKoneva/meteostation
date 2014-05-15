package ru.okoneva.meteostation.service.filewriter;

import org.apache.log4j.Logger;
import ru.okoneva.meteostation.model.Weather;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class MyFileWriter {

    private static final Logger LOG = Logger.getLogger(MyFileWriter.class);

    public void writeToCsv(final String fileName, final String results)
    throws SaveToFileException {

        LOG.debug("Start saving statistic to the file");
        try{
            final File file = retrieveFile(Weather.getHeader(), fileName);
            writeData(results, file);
            LOG.info("Statistic is saved to the file " + file.getName());
        } catch (FileNotFoundException e) {
            LOG.error("Can't get access to output file", e);
            throw new SaveToFileException("Can't get access to output file", e);
        } catch (IOException e1) {
            LOG.error("Can't save results to file", e1);
            throw new SaveToFileException("Can't save results to file", e1);
        }
    }

    private void writeData(final String results, final File file) throws IOException {

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file, true);
            fileWriter.write("\n" + results);
        } finally {
            if (fileWriter != null) {
                fileWriter.close();
            }
        }
    }

    private File retrieveFile(final String header, final String fileName) throws IOException {
        final File file = new File(fileName);
        if (!file.exists()) {
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(file);
                fileWriter.append(header);
            } finally {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            }
        }
        return file;
    }
}
