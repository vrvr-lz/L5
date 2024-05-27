package command;

import java.util.ArrayList;

public class History implements Command{
    ArrayList<String> commandsHistoryList = new ArrayList<>();

    /**
     *
     * @param commandsHistoryList
     */
    public History(ArrayList<String>  commandsHistoryList) {
        this.commandsHistoryList=commandsHistoryList;
    }
    @Override
    public void execute() {
        System.out.println("History: ");
        for(String str: commandsHistoryList)
            System.out.println(str);
    }
    @Override
    public String getDescription() {
        return "выводит последние 6 команд (без их аргументов)";
    }
}
