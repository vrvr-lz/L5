package io;

import java.util.Scanner;

public class UserIO {
    Scanner scanner;


    //Конструктор класса без параметров. При вызове в поле scanner присваивается Scanner,
    //производящий чтение из стандартного потока ввода с кодировкой UTF-8
    public UserIO() {
        scanner = new Scanner(System.in, "UTF-8");
    }

    /**
     *
     * @param scanner
     */
    public UserIO(Scanner scanner) {
        this.scanner = scanner;
    }

    //Чтение данных из места, на которое указывает объект,
    // на который ссылается поле scanner данного объекта
    public String readLine() {
        return scanner.nextLine();
    }

    //Вывод текста, принадлежащий команде в стандартный поток вывода
    public void printCommandText(String str) {
        System.out.print(str);
    }

    //Вывод текст ошибки, принадлежащий команде в стандартный поток вывода ошибок
    public void printCommandError(String str) {
        System.err.println(str);
    }
    //Вывод символа стрелки вправо перед запросом ввода команды
    public void printPreamble() {
        System.out.print("->");
    }

}
