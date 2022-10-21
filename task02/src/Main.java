/*
Задача 2:
Пусть дан произвольный список целых чисел, удалить из него четные числа
Для вывода необходимо использовать логгер(Java.Util.Logging)
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    // Константы
    final static int MAX_LIST_LENGTH = 30;
    final static int MAX_LIST_NUMBER = 100;
    private static final Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration( // берем конфиг для логов
                    Main.class.getResourceAsStream("./log.config"));
        } catch (IOException e) {  // печаль, беда...
            System.err.println("Could not setup logger configuration: " + e.toString());
        }
        //log.setLevel(Level.FINE);
        log.info("Начинаем работу, инициализируем список");
        List<Integer> testList = new ArrayList<Integer>();
        log.info("Задаем случайную длину списка и заполняем его случайными числами");
        int current_length = (int)(Math.random() * MAX_LIST_LENGTH);
        for (int i = 0; i < current_length; i++) {
            testList.add((int) (Math.random() * 2 * MAX_LIST_NUMBER) - MAX_LIST_NUMBER);
        }

        log.info("Исходный список:");
        log.info(myGetList(testList));

        testList = removeEven(testList);

        log.info("Список после обработки:");
        log.info(myGetList(testList));

        log.info("Работа завершена.");
    }
    public static String myGetList(List<Integer> arrayIn) {
        // возвращаем красивую строку для печати
        StringBuilder ret = new StringBuilder("");
        ret.append("[ ");
        for (int i=0; i<arrayIn.size(); i++) {
            if (i != 0) {
                ret.append(", ");
            }
            ret.append(arrayIn.get(i));
        }
        ret.append(" ]");
        return ret.toString();
    }
    public static List<Integer> removeEven(List<Integer> arrayIn) {
        // Из входящего списка чисел удаляем четные
        List<Integer> ret = new ArrayList<Integer>();
        for (Integer integer : arrayIn) {
            if (integer % 2 != 0) {
                ret.add(integer);
            }
        }
        return ret;
    }
}

/* Вывод программы:
[2022-10-21 22:41:07] [INFO   ] Начинаем работу, инициализируем список
[2022-10-21 22:41:07] [INFO   ] Задаем случайную длину списка и заполняем его случайными числами
[2022-10-21 22:41:07] [INFO   ] Исходный список:
[2022-10-21 22:41:07] [INFO   ] [ 1, 97, -11, -5, 97, -31, 6, 1, -90, -55, 34, -62, 5, 60, 12 ]
[2022-10-21 22:41:07] [INFO   ] Список после обработки:
[2022-10-21 22:41:07] [INFO   ] [ 1, 97, -11, -5, 97, -31, 1, -55, 5 ]
[2022-10-21 22:41:07] [INFO   ] Работа завершена.
 */