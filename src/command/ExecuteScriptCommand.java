package command;
import collection.CollectionManager;
import exceptions.RecoursiveCallException;
import fileWork.CityFieldsReader;
import io.UserIO;
import java.io.*;
import java.util.*;



public class ExecuteScriptCommand implements CommandWithArguments {
    private String[] commandArguments;
    private CollectionManager collectionManager;
    private UserIO userIO;
    private CityFieldsReader cityFieldsReader;
    private String scriptPath;
    private Script script;

    /**
     * @param collectionManager
     * @param cityFieldsReader
     * @param script
     */
    public ExecuteScriptCommand(CollectionManager collectionManager, CityFieldsReader cityFieldsReader, Script script) {
        this.collectionManager = collectionManager;
        this.cityFieldsReader = cityFieldsReader;
        this.script = script;
    }

    // Метод, исполняющий команду. В коллекцию scripts при начале исполнения добавляется адрес скрипта, далее идет само его исполнение, в конце адрес файла удаляется. В случае ошибки выводится соответствующее сообщение.
    @Override
    public void execute() {
        try {
            if (commandArguments.length == 1) {
                scriptPath = commandArguments[0];
                if (script.scriptPaths.contains(scriptPath)) throw new RecoursiveCallException();
                else script.putScript(scriptPath);
            } else throw new IllegalArgumentException();

            File ioFile = new File(scriptPath);
            if (!ioFile.canWrite() || ioFile.isDirectory() || !ioFile.isFile()) throw new IOException();

            FileInputStream fileInputStream = new FileInputStream(scriptPath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            Scanner scanner = new Scanner(inputStreamReader);
            userIO = new UserIO(scanner);
            CommandInvoker commandInvoker = new CommandInvoker(collectionManager, userIO, cityFieldsReader, script);

            while (scanner.hasNext()) {
                commandInvoker.execute(scanner.nextLine());
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Файл скрипта не найден");
        } catch (NullPointerException ex) {
            System.err.println("Не выбран файл, из которого читать скрипт");
        } catch (IOException ex) {
            System.err.println("Доступ к файлу невозможен" + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.err.println("скрипт не передан в качестве аргумента команды, либо кол-во агрументов больше 1");
        } catch (RecoursiveCallException ex) {
            System.err.println("Скрипт " + scriptPath + " уже существует (Рекурсивный вызов)");
        }
        script.removeScript(scriptPath);

    }

    @Override
    public void getCommandArguments(String[] commandArguments) {
        this.commandArguments = commandArguments;
    }

    @Override
    public String getDescription() {
        return "считает и исполняет скрипт из указанного файла";
    }

    static class Script {
        private ArrayList<String> scriptPaths = new ArrayList<String>();

        public void putScript(String scriptPath) {
            scriptPaths.add(scriptPath);
        }

        public void removeScript(String scriptPath) {
            scriptPaths.remove(scriptPath);
        }
    }
}