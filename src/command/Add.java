package command;
import collection.CollectionManager;
import fileWork.CityFieldsReader;

public class Add implements Command {
    private CollectionManager collectionManager;
    private CityFieldsReader cityFieldsReader;

    /**
     *
     * @param collectionManager
     * @param cityFieldsReader
     */
    public Add(CollectionManager collectionManager, CityFieldsReader cityFieldsReader) {
        this.collectionManager = collectionManager;
        this.cityFieldsReader = cityFieldsReader;
    }

    /**
     *
     */
    @Override
    public void execute() {
        collectionManager.add(cityFieldsReader.read());
    }

    /**
     *
     * @return
     */
    @Override
    public String getDescription() {
        return "добавляет элемент в коллекцию";
    }
}
