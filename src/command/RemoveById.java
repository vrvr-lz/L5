package command;

import collection.CollectionManager;

public class RemoveById implements CommandWithArguments {
    private CollectionManager collectionManager;
    private String[] commandArguments;

    /**
     *
     * @param collectionManager
     */
    public RemoveById(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        if (commandArguments != null && commandArguments.length > 0) {
            try {
                int id = Integer.parseInt(commandArguments[0]);
                collectionManager.removeById(id);
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат id");
            }
        } else {
            System.out.println("id не указан");
        }
    }

    @Override
    public void getCommandArguments(String[] commandArguments) {
        this.commandArguments = commandArguments;
    }
    @Override
    public String getDescription() {
        return "удаляет элемент из коллекции по его id";
    }
}