package command;

import collection.CollectionManager;

public class Info implements Command {
    private CollectionManager collectionManager;

    /**
     *
     * @param collectionManager
     */
    public Info(CollectionManager collectionManager){
        this.collectionManager=collectionManager;
    }
    @Override
    public void execute() {
        collectionManager.info();
    }
    @Override
    public String getDescription() {
        return "выводит информацию о коллекции(тип, дата инициализации, кол-во элементов, тип элементов коллекции)";
    }
}

