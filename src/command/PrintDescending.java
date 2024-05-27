package command;

import collection.CollectionManager;

public class PrintDescending implements Command {
    private CollectionManager collectionManager;

    /**
     *
     * @param collectionManager
     */
    public PrintDescending(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.printDescending();
    }
    @Override
    public String getDescription() {
        return "выводит элементы коллекции в порядке убывания";
    }
}