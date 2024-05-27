package command;

import collection.CollectionManager;

public class AverageOfPopulationDensity implements Command {
    private CollectionManager collectionManager;

    /**
     *
     * @param collectionManager
     */
    public AverageOfPopulationDensity(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     *
     */
    @Override
    public void execute() {
        collectionManager.averageOfPopulationDensity();
    }

    /**
     *
     * @return
     */
    @Override
    public String getDescription() {
        return "выводит среднее значение поля populationDensity для всех элементов коллекции";
    }
}
