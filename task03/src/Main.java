/*
Задача 3:
Задан целочисленный список ArrayList. Найти минимальное, максимальное и среднее из этого списка.
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

        try {
            log.info("Минимальное значение: " + getMin(testList));
            log.info("Максимальное значение: " + getMax(testList));
            log.info("Среднее значение: " + getMean(testList));
        } catch (NullPointerException e) {
            log.severe(e.getMessage());
        } finally {
            log.info("Работа завершена.");
        }
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

    public static int getMin(List<Integer> arrayIn) {
        // вычисляем минимум в списке
        if (arrayIn.size() < 1) {
            throw new NullPointerException("Exception: arrayIn is empty!");
        }
        int ret = Integer.MAX_VALUE;
        for (Integer integer : arrayIn) {
            if (ret > integer) {
                ret = integer;
            }
        }
        return ret;
    }
    public static int getMax(List<Integer> arrayIn) {
        // вычисляем максимум в списке
        int ret = Integer.MIN_VALUE;
        if (arrayIn.size() < 1) {
            throw new NullPointerException("Exception: arrayIn is empty!");
        }
        for (Integer integer : arrayIn) {
            if (ret < integer) {
                ret = integer;
            }
        }
        return ret;
    }
    public static float getMean(List<Integer> arrayIn) {
        // вычисляем среднее арифметическое списка
        if (arrayIn.size() < 1) {
            throw new NullPointerException("Exception: arrayIn is empty!");
        }
        float ret = 0;
        for (Integer integer : arrayIn) {
            ret += integer;
        }
        return ret / arrayIn.size();
    }
}

/* Вывод программы:
[2022-10-21 22:39:58] [INFO   ] Начинаем работу, инициализируем список
[2022-10-21 22:39:58] [INFO   ] Задаем случайную длину списка и заполняем его случайными числами
[2022-10-21 22:39:58] [INFO   ] Исходный список:
[2022-10-21 22:39:58] [INFO   ] [ 36, -76, 88, 61, 63, 24, -56, 42, 38, -53, 15, 71, -77, -40, -42 ]
[2022-10-21 22:39:58] [INFO   ] Минимальное значение: -77
[2022-10-21 22:39:58] [INFO   ] Максимальное значение: 88
[2022-10-21 22:39:58] [INFO   ] Среднее значение: 6.266667
[2022-10-21 22:39:58] [INFO   ] Работа завершена.
 */