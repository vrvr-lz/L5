package command;

import collection.CollectionManager;

public class Clear implements Command {
    private CollectionManager collectionManager;

    /**
     *
     * @param collectionManager
     */
    public Clear(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    /**
     *
     */
    @Override
    public void execute() {
        collectionManager.clear();
    }

    /**
     *
     * @return
     */
    @Override
    public String getDescription() {
        return "очищает все элементы коллекции";
    }
}
