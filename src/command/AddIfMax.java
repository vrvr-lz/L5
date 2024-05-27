package command;

import collection.CollectionManager;
import fileWork.CityFieldsReader;

public class AddIfMax implements Command {
    private CollectionManager collectionManager;
    private CityFieldsReader cityFieldsReader;

    /**
     *
     * @param collectionManager
     * @param cityFieldsReader
     */
    public AddIfMax(CollectionManager collectionManager, CityFieldsReader cityFieldsReader) {
        this.collectionManager = collectionManager;
        this.cityFieldsReader = cityFieldsReader;
    }

    /**
     *
     */
    @Override
    public void execute() {
        collectionManager.addIfMax(cityFieldsReader.read());
    }

    /**
     *
     * @return
     */
    @Override
    public String getDescription() {
        return "добавляет элемент в коллекцию, только если поле area превышает наибольшее поле area элемента в коллекции";
    }
}
