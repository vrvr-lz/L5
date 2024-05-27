package command;

import collection.CollectionManager;

public class Save implements Command{
    private String inputFile;
    private CollectionManager collectionManager;

    /**
     *
     * @param collectionManager
     * @param inputFile
     */
    public Save(CollectionManager collectionManager,String inputFile){
        this.collectionManager=collectionManager;
        this.inputFile=inputFile;
    }
    @Override
    public void execute() {
        collectionManager.save(inputFile);
    }
    @Override
    public String getDescription() {
        return "сохраняет коллекцию в указанный файл";
    }
}
