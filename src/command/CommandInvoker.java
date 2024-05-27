package command;

import fileWork.CityFieldsReader;
import io.UserIO;

import java.util.*;

public class CommandInvoker {
    private HashMap<String, Command> commandsWithoutArguments;
    private HashMap<String, CommandWithArguments> commandsWithArguments;
    private CollectionManager collectionManager;
    private UserIO userIO;
    private String inputFile;
    private CityFieldsReader cityFieldsReader;
    ExecuteScriptCommand.Script script;
    ArrayList<String> commandsHistoryList = new ArrayList<>();

    /**
     *
     * @param collectionManager
     * @param userIO
     * @param inputFile
     * @param cityFieldsReader
     */
    public CommandInvoker(CollectionManager collectionManager, UserIO userIO, String inputFile, CityFieldsReader cityFieldsReader) {
        this.collectionManager = collectionManager;
        this.userIO = userIO;
        this.inputFile = inputFile;
        this.cityFieldsReader = cityFieldsReader;

        commandsWithoutArguments = new HashMap<>();
        commandsWithArguments = new HashMap<>();
        this.script = new ExecuteScriptCommand.Script();
        this.putCommands();
    }

    /**
     *
     * @param collectionManager
     * @param userIO
     * @param cityFieldsReader
     * @param script
     */
    public CommandInvoker(CollectionManager collectionManager, UserIO userIO, CityFieldsReader cityFieldsReader, ExecuteScriptCommand.Script script) {
        this.collectionManager = collectionManager;
        this.userIO = userIO;
        this.cityFieldsReader = cityFieldsReader;

        commandsWithoutArguments = new HashMap<>();
        commandsWithArguments = new HashMap<>();
        this.script = script;
        this.putCommands();
    }

    private void putCommands() {
        commandsWithoutArguments.put("info", new Info(collectionManager));
        commandsWithoutArguments.put("show", new Show(collectionManager));
        commandsWithoutArguments.put("clear", new Clear(collectionManager));
        commandsWithoutArguments.put("save", new Save(collectionManager, inputFile));
        commandsWithoutArguments.put("exit", new Exit());
        commandsWithoutArguments.put("history", new History(commandsHistoryList));
        commandsWithoutArguments.put("average_of_population_density", new AverageOfPopulationDensity(collectionManager));
        commandsWithoutArguments.put("group_counting_by_area", new GroupCountingByArea(collectionManager));
        commandsWithoutArguments.put("help", new Help(commandsWithoutArguments, commandsWithArguments));
        commandsWithoutArguments.put("print_descending", new PrintDescending(collectionManager));
        commandsWithoutArguments.put("add", new Add(collectionManager, cityFieldsReader));
        commandsWithoutArguments.put("add_if_max", new AddIfMax(collectionManager, cityFieldsReader));
        commandsWithoutArguments.put("add_if_min", new AddIfMin(collectionManager, cityFieldsReader));
        commandsWithArguments.put("update", new Update(collectionManager, userIO));
        commandsWithoutArguments.put("remove_lower", new RemoveLower(collectionManager, cityFieldsReader));
        commandsWithArguments.put("execute_script", new ExecuteScriptCommand(collectionManager, cityFieldsReader, script));
        commandsWithArguments.put("remove_by_id", new RemoveById(collectionManager));
    }

    /**
     *
     * @param firstCommandLine
     */
    public void execute(String firstCommandLine) {
        String[] words = firstCommandLine.trim().split("\\s+");//убираем пробелы в начале и конце. Сплитуем по пробелам
        String[] args = Arrays.copyOfRange(words, 1, words.length); //Выдергиваем аргументы
        if (commandsWithArguments.containsKey(words[0].toLowerCase(Locale.ROOT))) { //Преобразуем все в нижний регистр.
            CommandWithArguments command = commandsWithArguments.get(words[0].toLowerCase(Locale.ROOT));//получаем команду из списка
            command.getCommandArguments(args);//Даем ей нужные аргументы
            command.execute();//Выполнение команды
            addToCommandsHistory(words[0].toLowerCase(Locale.ROOT));

        } else if (commandsWithoutArguments.containsKey(words[0].toLowerCase(Locale.ROOT))) {
            Command command = commandsWithoutArguments.get(words[0].toLowerCase(Locale.ROOT));
            command.execute();//Выполнение команды
            addToCommandsHistory(words[0].toLowerCase(Locale.ROOT));
        } else {
            System.err.println("Команда " + words[0] + " не распознана, для получения справки введите команду help");
        }
    }

    /**
     *
     * @param string
     */
    public void addToCommandsHistory(String string){
        if(commandsHistoryList.size()==6) {
            commandsHistoryList.remove(0);
            commandsHistoryList.add(string);
        }else{
            commandsHistoryList.add(string);
        }
    }
}
