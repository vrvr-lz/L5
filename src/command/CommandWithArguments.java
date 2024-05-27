package command;

public interface CommandWithArguments extends Command {
    void getCommandArguments(String[] commandArguments);
}
