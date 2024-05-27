package command;

import java.util.HashMap;
import java.util.Map;

public class Help implements Command{
    private HashMap<String, Command> commandWithoutArgumentsHashMap;
    private HashMap<String, CommandWithArguments> commandWithArgumentsHashMap;

    /**
     *
     * @param commandHashMap
     * @param commandWithArgumentsHashMap
     */
    public Help(HashMap<String, Command> commandHashMap, HashMap<String, CommandWithArguments> commandWithArgumentsHashMap) {
        this.commandWithoutArgumentsHashMap = commandHashMap;
        this.commandWithArgumentsHashMap = commandWithArgumentsHashMap;
    }
    @Override
    public void execute() {
        for (Map.Entry<String, Command> entry : commandWithoutArgumentsHashMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getDescription());//перебор Map в цикле. Ключ+значение
        }
        for (Map.Entry<String, CommandWithArguments> entry : commandWithArgumentsHashMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getDescription());//перебор Map в цикле. Ключ+значение
        }
    }
    @Override
    public String getDescription() {
        return "выводит справку по всем командам";
    }
}