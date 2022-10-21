/*
Задача 3:
Задан целочисленный список ArrayList. Найти минимальное, максимальное и среднее из этого списка.
Для вывода необходимо использовать логгер(Java.Util.Logging)
 */

import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    private static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration( // берем конфиг для логов
                    Main.class.getResourceAsStream("./log.config"));
        } catch (IOException e) {  // печаль, беда...
            System.err.println("Could not setup logger configuration: " + e.toString());
        }
        //log.setLevel(Level.FINE);
        log.info("Начинаем работу!");

        log.info("Работа завершена.");
    }
}