package command;

import collection.CollectionManager;

public class GroupCountingByArea implements Command {
    private CollectionManager collectionManager;

    /**
     *
     * @param collectionManager
     */
    public GroupCountingByArea(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.groupCountingByArea();
    }
    @Override
    public String getDescription() {
        return "группирует элементы коллекции по значению поля area, выводит количество элементов в каждой группе";
    }
}
