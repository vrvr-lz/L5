package command;

import io.UserIO;

public class Update implements CommandWithArguments {
    private CollectionManager collectionManager;
    private UserIO userIO;
    private String[] commandArguments;

    /**
     * @param collectionManager
     * @param userIO
     */
    public Update(CollectionManager collectionManager, UserIO userIO) {
        this.collectionManager = collectionManager;
        this.userIO = userIO;
    }

    /**
     *
     */
    @Override
    public void execute() {
        try {
            int id = Integer.parseInt(commandArguments[0]);

            if (!collectionManager.containsKey(id)) {
                System.err.println("Элемента с данным ключом в коллекции не существует.");
                return;
            }

            // Вывод списка всех доступных полей для обновления
            userIO.printCommandText("Список всех доступных полей для обновления:");
            userIO.printCommandText(collectionManager.getFieldNames());

            // Ввод значений для каждого поля
            userIO.printCommandText("Введите значения полей для элемента коллекции:");
            userIO.printCommandText("Формат ввода: <имя_поля> <значение>, или 'stop' для прерывания изменения");

            String line;
            do {
                line = userIO.readLine().trim();
                if (!line.equalsIgnoreCase("stop")) {
                    String[] parts = line.split("\\s+", 2);
                    if (parts.length == 2) {
                        String fieldName = parts[0];
                        String value = parts[1];
                        collectionManager.update(id, fieldName, value);
                    } else {
                        System.err.println("Некорректный формат ввода. Повторите попытку.");
                    }
                }
            } while (!line.equalsIgnoreCase("stop"));

        } catch (IndexOutOfBoundsException ex) {
            System.err.println("Не указаны все аргументы команды.");
        } catch (NumberFormatException ex) {
            System.err.println("Формат аргумента не соответствует целочисленному " + ex.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "изменяет указанное поле выбранного по id элемента коллекции ";
    }

    @Override
    public void getCommandArguments(String[] commandArguments) {
        this.commandArguments = commandArguments;
    }
}