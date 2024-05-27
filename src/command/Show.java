package command;

import collection.CollectionManager;

public class Show implements Command{
    private CollectionManager collectionManager;

    /**
     *
     * @param collectionManager
     */
    public Show(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute() {
        collectionManager.show();
    }
    @Override
    public String getDescription() {
        return "показывает подробное содержимое всех элементов коллекции";
    }
}