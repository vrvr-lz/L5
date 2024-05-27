package command;

import collection.CollectionManager;
import fileWork.CityFieldsReader;

public class AddIfMin implements Command {
    private CollectionManager collectionManager;
    private CityFieldsReader cityFieldsReader;

    /**
     *
     * @param collectionManager
     *
     * @param cityFieldsReader
     */
    public AddIfMin(CollectionManager collectionManager, CityFieldsReader cityFieldsReader) {
        this.collectionManager = collectionManager;
        this.cityFieldsReader = cityFieldsReader;
    }

    /**
     *
     */
    @Override
    public void execute() {
        collectionManager.addIfMin(cityFieldsReader.read());

    }

    /**
     *
     * @return
     */
    @Override
    public String getDescription() {
        return "добавляет элемент в коллекцию, только если его поле area меньше поля areaа элемента в коллекции";
    }
}