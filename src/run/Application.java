package run;

import collection.City;
import collection.CollectionManager;
import command.CommandInvoker;
import fileWork.CityFieldsReader;
import fileWork.FileManager;
import fileWork.XmlParser;
import io.UserIO;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.NoSuchElementException;

/**
 *
 */
public class Application {
    CollectionManager collectionManager;
    FileManager fileManager;
    XmlParser xmlParser;
    UserIO userIO;
    CommandInvoker commandInvoker;
    CityFieldsReader cityFieldsReader;

    /**
     *
     * @param inputFile
     */
    public void start(String inputFile) {
        collectionManager = new CollectionManager();
        fileManager = new FileManager();
        xmlParser = new XmlParser();
        userIO = new UserIO();
        cityFieldsReader = new CityFieldsReader(userIO);
        this.commandInvoker = new CommandInvoker(collectionManager, userIO, inputFile, cityFieldsReader);

        try {
            File ioFile = new File(inputFile);
            if (!ioFile.canWrite() || ioFile.isDirectory() || !ioFile.isFile()) throw new IOException();

            FileInputStream fileInputStream = new FileInputStream(inputFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            StringBuilder fileContent = new StringBuilder();
            int character;
            while ((character = inputStreamReader.read()) != -1) {
                fileContent.append((char) character);
            }

            City[] cities;
            try {
                cities = xmlParser.parseToCollection(new InputSource(new StringReader(fileContent.toString())));
            } catch (ParserConfigurationException | SAXException e) {
                System.err.println("Ошибка при парсинге файла XML: " + e.getMessage());
                System.exit(0);
                return;
            }

            for (City element : cities) {
                collectionManager.add(element);
            }

            if (cities.length > 0) {
                userIO.printCommandText("Элементы коллекций из указанного файла были загружены\n");
            } else {
                System.err.println("Файл XML содержит некорректный элемент коллекции\nПожалуйста, исправьте файл\n** До исправления программа будет игнорировать поврежденный элемент **");
            }

            fileInputStream.close();
            inputStreamReader.close();
        } catch (IOException e) {
            System.err.println("По указанному адресу нет подходящего файла");
            System.exit(0);
        } catch (NoSuchElementException ex) {
            System.err.println("Ctrl + D exception");
        }

        cycle();
    }

    /**
     *
     */

    public void cycle() {
        userIO.printCommandText("Программа была запущена.\n");
        while (true) {
            userIO.printCommandText("\nВведите название команды:\n");
            userIO.printPreamble();
            String line = userIO.readLine();
            commandInvoker.execute(line);
        }
    }
}
