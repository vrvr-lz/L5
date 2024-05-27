package command;

import fileWork.CityFieldsReader;

public class RemoveLower implements Command {
    private CollectionManager collectionManager;
    private CityFieldsReader cityFieldsReader;


    /**
     * @param collectionManager
     */
    public RemoveLower(CollectionManager collectionManager, CityFieldsReader cityFieldsReader) {
        this.collectionManager = collectionManager;
        this.cityFieldsReader = cityFieldsReader;
    }

    @Override
    public void execute() {
        collectionManager.removeLower(cityFieldsReader.read()); // Передача cityToRemove в метод removeLower
    }

    @Override
    public String getDescription() {
        return "удаляет из коллекции все элементы, чье поле area меньше, чем заданный";
    }

}
