package collection;
import fileWork.FileManager;
import fileWork.XmlParser;
import io.UserIO;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

public class CollectionManager {
    HashSet<City> hashSet;
    LocalDateTime collectionInitializator;
    UserIO userIO;

    /**
     *
     */
    //Конструктор - создание нового объекта менеджера коллекции
    public CollectionManager() {
        this.hashSet = new HashSet<>();
        this.collectionInitializator = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);
        this.userIO = new UserIO();
    }

    /**
     *
     */
    public void info() {
        System.out.println("Коллекция " + hashSet.getClass().getSimpleName());
        System.out.println("Тип элементов коллекции: " + City.class.getSimpleName());
        System.out.println("Время инициализации коллекции: " + collectionInitializator);
        System.out.println("Количество элементов в коллекции: " + hashSet.size());
    }
    //Вывод информации по элементам коллекции

    /**
     *
     */
    public void show() {
        if (hashSet.isEmpty()) {
            System.out.println("Коллекция пуста");
        } else {
            List<City> sortedList = new ArrayList<>(hashSet);
            sortedList.sort(Comparator.comparingInt(City::getId)); // Сортировка по возрастанию id
            for (City city : sortedList) {
                System.out.println(city.toString());
            }
        }
    }

    /**
     *
     */
    //Удаление всех элементов коллекции
    public void clear(){
        hashSet.clear();
        System.out.println("Коллекция была очищена");
    }

    //Сохранение элементов коллекции в формате xml

    /**
     *
     * @param filePath
     */
    public void save(String filePath) {
        XmlParser xmlParser = new XmlParser();
        FileManager fileManager = new FileManager();
        City[] labWorks = new City[hashSet.size()];
        labWorks = hashSet.toArray(labWorks);
        String str = xmlParser.parseToXml(labWorks);
        fileManager.writeCollection(str, filePath);
        System.out.println("Коллекция была сохранена.");
    }

    /**
     *
     * @param id
     * @param field
     * @param value
     */
    public void update(Integer id, String field, String value) {
        try {
            Iterator<City> iterator = hashSet.iterator();
            while (iterator.hasNext()) {
                City city = iterator.next();
                if (city.getId() == id) {
                    switch (field) {
                        case "name":
                            if (value.isEmpty()) throw new IllegalArgumentException("Строка не может быть пустой");
                            city.setName(value);
                            break;
                        case "coordinates_x":
                            city.getCoordinates().setX(Long.parseLong(value));
                            break;
                        case "coordinates_y":
                            city.getCoordinates().setY(Integer.parseInt(value));
                            break;
                        case "area":
                            double area = Double.parseDouble(value);
                            if (area <= 0) throw new IllegalArgumentException("Значение поля должно быть больше 0");
                            city.setArea(area);
                            break;
                        case "population":
                            int population = Integer.parseInt(value);
                            if (population <= 0) throw new IllegalArgumentException("Значение поля должно быть больше 0");
                            city.setPopulation(population);
                            break;
                        case "metersAboveSeaLevel":
                            city.setMetersAboveSeaLevel(Double.parseDouble(value));
                            break;
                        case "populationDensity":
                            long populationDensity = Long.parseLong(value);
                            if (populationDensity <= 0) throw new IllegalArgumentException("Значение поля должно быть больше 0");
                            city.setPopulationDensity(populationDensity);
                            break;
                        case "telephoneCode":
                            int telephoneCode = Integer.parseInt(value);
                            if (telephoneCode <= 0 || telephoneCode > 100000) throw new IllegalArgumentException("Значение поля должно быть больше 0 и не больше 100000");
                            city.setTelephoneCode(telephoneCode);
                            break;
                        case "government":
                            city.setGovernment(Government.valueOf(value.toUpperCase()));
                            break;
                        case "governor_age":
                            city.getGovernor().setAge(Integer.parseInt(value));
                            break;
                        default:
                            System.out.println("Поле не распознано");
                            break;
                    }
                    System.out.println("Значение поля было изменено");
                    return;
                }
            }
            System.out.println("Город с указанным id не найден");
        } catch (NumberFormatException ex) {
            System.err.println("Неверный формат числа");
        } catch (IllegalArgumentException ex) {
            System.err.println("Ошибка: " + ex.getMessage());
        }
    }

    /**
     *
     * @param id
     */
    public void removeById(int id) {
        boolean removed = false;
        Iterator<City> iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            City city = iterator.next();
            if (city.getId() == id) {
                iterator.remove();
                removed = true;
                System.out.println("Элемент с ID " + id + " успешно удален из коллекции.");
                break;
            }
        }
        if (!removed) {
            System.out.println("Элемент с ID " + id + " не найден в коллекции.");
        }
    }

    /**
     *
     */
    public void averageOfPopulationDensity() {
        if (hashSet.isEmpty()) {
            System.out.println("Коллекция пуста.");
            return;
        }

        long totalPopulationDensity = 0;
        int numberOfCities = 0;
        for (City city : hashSet) {
            if (city.getPopulationDensity() != null) {
                totalPopulationDensity += city.getPopulationDensity();
                numberOfCities++;
            }
        }

        if (numberOfCities > 0) {
            double averagePopulationDensity = (double) totalPopulationDensity / numberOfCities;
            System.out.println("Среднее значение плотности населения: " + averagePopulationDensity);
        } else {
            System.out.println("Ни один город не содержит информацию о плотности населения.");
        }
    }

    /**
     *
     */
    public void groupCountingByArea() {
        if (hashSet.isEmpty()) {
            System.out.println("Коллекция пуста.");
            return;
        }
        HashMap<Double, Integer> areaCountMap = new HashMap<>();
        for (City city : hashSet) {
            double area = city.getArea();
            areaCountMap.put(area, areaCountMap.getOrDefault(area, 0) + 1);
        }

        System.out.println("Группировка элементов коллекции по значению поля area:");
        for (Double area : areaCountMap.keySet()) {
            int count = areaCountMap.get(area);
            System.out.println("Площадь: " + area + ", количество элементов: " + count);
        }
    }

    /**
     *
     */
    public void printDescending() {
        if (hashSet.isEmpty()) {
            System.out.println("Коллекция пуста.");
            return;
        }
        ArrayList<City> tempCollection = new ArrayList<>(hashSet);
        Collections.sort(tempCollection, Comparator.comparingInt(City::getId).reversed());
        System.out.println("Элементы коллекции в порядке убывания:");
        for (City city : tempCollection) {
            System.out.println(city);
        }
    }
    public void add(City city) {
        hashSet.add(city);

        userIO.printCommandText("Элемент добавлен в коллекцию\n");
    }

    /**
     *
     * @param city
     */
    public void addIfMax(City city) {
        if (hashSet.isEmpty()) {
            hashSet.add(city);
        } else {
            boolean isMax = true;
            for (City existingCity : hashSet) {
                if (city.getArea() <= existingCity.getArea()) {
                    isMax = false;
                    break;
                }
            }if (isMax) {
                hashSet.add(city);
                System.out.println("\nЭлемент успешно добавлен в коллекцию, так как значение поля area наибольшее среди других элементов коллекции");
            }else {
                System.out.println("\nЭлемент не добавлен в коллекцию, так как значение поля area меньше, чем у одного из элементов коллекции");
            }
        }
    }


    /**
     *
     * @param city
     */
    public void addIfMin(City city) {
        if (hashSet.isEmpty()) {
            hashSet.add(city);
        } else {
            boolean isMin = true;
            for (City existingCity : hashSet) {
                if (city.getArea() >= existingCity.getArea()) {
                    isMin = false;
                    break;
                }
            }if (isMin) {
                hashSet.add(city);
                System.out.println("\nЭлемент успешно добавлен в коллекцию, так как значение поля area наименьшее среди других элементов коллекции");
            }else {
                System.out.println("\nЭлемент не добавлен в коллекцию, так как значение поля area больше, чем у одного из элементов коллекции");
            }
        }
    }

    /**
     *
     * @return
     */
    public String getFieldNames(){
        return "Список всех полей: \nname (String)\ncoordinate_x (long)\ncoordinate_y (int)\n" +
                "area (double)\npopulation (Integer)\nmetersAboveSeaLevel (Double)\npopulationDensity (Long)\ntelephoneCode (int)\ngovernment" +
                Arrays.toString(Government.values())+"\nhumanAge (int)\n";
    }

    /**
     *
     * @param cityToRemove
     */
    public void removeLower(City cityToRemove) {
        double areaThreshold = cityToRemove.getArea();
        Iterator<City> iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            City currentCity = iterator.next();
            if (currentCity.getArea() < areaThreshold && currentCity.compareTo(cityToRemove) < 0) {
                iterator.remove();
            }
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public boolean containsKey(Integer id) {
        for (City city : hashSet) {
            if (city.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
