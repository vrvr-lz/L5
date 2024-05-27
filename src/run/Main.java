package run;

public class Main {
    public static void main(String[] args) {
        String inputFile = "";
        if (args.length > 0 && !args[0].equals("")) {
            inputFile = args[0];
        }

        if (!inputFile.isEmpty()) {
            Application application = new Application();
            application.start(inputFile);
        } else {
            System.out.println("Не удалось получить путь к файлу.");
        }
    }
}