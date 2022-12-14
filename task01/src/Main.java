/*
Задача 1:
Реализовать алгоритм сортировки слиянием
Для вывода необходимо использовать логгер(Java.Util.Logging)
 */

import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    // Константы
    final static int MAX_ARRAY_LENGTH = 40;
    final static int MAX_ARRAY_NUMBER = 100;
    private static final Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration( // берем конфиг для логов
                    Main.class.getResourceAsStream("./log.config"));
        } catch (IOException e) {  // печаль, беда...
            System.err.println("Could not setup logger configuration: " + e.toString());
        }
        //log.setLevel(Level.FINE);
        log.info("Начинаем работу, инициализируем массив случайной величины");
        int[] testArray = new int[(int) (Math.random() * MAX_ARRAY_LENGTH)];
//        int[] testArray = new int[] {6, 1, 3, 5, 2, 4, 7, 8};
        log.info("Заполняем массив случайными числами");
        for (int i = 0; i < testArray.length; i++) {
            testArray[i] = (int) (Math.random() * 2 * MAX_ARRAY_NUMBER) - MAX_ARRAY_NUMBER;
        }
        log.info("Исходный массив:");
        log.info(myGetArray(testArray));

        testArray = sortArray(testArray);

        log.info("Массив после сортировки:");
        log.info(myGetArray(testArray));
        log.info("Работа завершена");
    }

    public static int [] mergeArray(int[] arrayA, int[] arrayB) {
        // метод слияния двух массивов. Во время слияния и выполняется сортировка
        int[] arrayRet = new int[arrayA.length + arrayB.length];
        int positionA = 0, positionB = 0;

        for (int i = 0; i < arrayRet.length; i++) {
            // указатели позиций указывают на текущие элементы массивов A и B
            if (positionA > arrayA.length-1) {
                arrayRet[i] = arrayB[positionB];
                positionB++;
            } else if (positionB > arrayB.length-1) {
                arrayRet[i] = arrayA[positionA];
                positionA++;
            } else if (arrayA[positionA] < arrayB[positionB]) {
                arrayRet[i] = arrayA[positionA];
                positionA++;
            } else {
                arrayRet[i] = arrayB[positionB];
                positionB++;
            }
        }
        return arrayRet;
    }

    public static int [] sortArray(int[] arrayA){ // сортировка Массива который передается в функцию
        // проверяем не нулевой ли он?
        if (arrayA == null) {
            return null;
        }
        // если один элемент в массиве
        if (arrayA.length < 2) {
            return arrayA; // возврат в рекурсию в строки ниже см комменты.
        }
        // копируем левую часть от начала до середины
        int [] arrayB = new int[arrayA.length / 2];
        System.arraycopy(arrayA, 0, arrayB, 0, arrayA.length / 2);

        // копируем правую часть от середины до конца массива, вычитаем из длины первую часть
        int [] arrayC = new int[arrayA.length - arrayA.length / 2];
        System.arraycopy(arrayA, arrayA.length / 2, arrayC, 0, arrayA.length - arrayA.length / 2);

        // рекурсией закидываем поделенные обе части обратно в наш метод, он будет крутиться до тех пор,
        // пока не дойдет до 1 элемента в массиве, после чего вернется в строку и будет искать второй такой же,
        // точнее правую часть от него и опять вернет его назад
        arrayB = sortArray(arrayB); // левая часть возврат из рекурсии строкой return arrayA;
        arrayC = sortArray(arrayC); // правая часть возврат из рекурсии строкой return arrayA;

        // далее опять рекурсия возврата слияния двух отсортированных массивов
        return mergeArray(arrayB, arrayC);
    }

    public static String myGetArray(int[] arrayIn) {
        // возвращаем красивую строку для печати
        StringBuilder ret = new StringBuilder("");
        ret.append("[ ");
        for (int i=0; i<arrayIn.length; i++) {
            if (i != 0) {
                ret.append(", ");
            }
            ret.append(arrayIn[i]);
        }
        ret.append(" ]");
        return ret.toString();
    }
}

/* Вывод программы:
[2022-10-21 23:09:08] [INFO   ] Начинаем работу, инициализируем массив случайной величины
[2022-10-21 23:09:08] [INFO   ] Заполняем массив случайными числами
[2022-10-21 23:09:08] [INFO   ] Исходный массив:
[2022-10-21 23:09:08] [INFO   ] [ 4, 49, 96, 3, 50, 16, 19, 79, -39, 77, -26, 23, 36, 51, 75, -17, 37, -2, 41, -51, -35, -24, 87, -22, -9, -36, 21, 2, 84 ]
[2022-10-21 23:09:08] [INFO   ] Массив после сортировки:
[2022-10-21 23:09:08] [INFO   ] [ -51, -39, -36, -35, -26, -24, -22, -17, -9, -2, 2, 3, 4, 16, 19, 21, 23, 36, 37, 41, 49, 50, 51, 75, 77, 79, 84, 87, 96 ]
[2022-10-21 23:09:08] [INFO   ] Работа завершена
 */