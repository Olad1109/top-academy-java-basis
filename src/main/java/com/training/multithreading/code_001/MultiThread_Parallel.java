package main.java.com.training.multithreading.code_001;

/* Модуль: Многопоточность.
   Тема: Синхронизация.
    Задание 1
       При старте приложения запускаются три потока.
    Первый поток заполняет массив случайными числами.
    Два других потока ожидают заполнения. Когда массив
    заполнен оба потока запускаются. Первый поток находит
    сумму элементов массива, второй поток среднеарифметическое
    значение в массиве. Полученный массив, сумма
    и среднеарифметическое возвращаются в метод main,
    где должны быть отображены. */

import java.util.Arrays;

class MultiThread_Parallel {

    int count;
    static int[] receiveValues;

    public static void main (String[] argCommStr) throws InterruptedException {
        MultiThread_Parallel multiThread_Parallel = new MultiThread_Parallel();
        multiThread_Parallel.arrayDeclaration();
    }

    void arrayDeclaration() throws InterruptedException {
        count = 10;
        SynchroThr numOfElem = new SynchroThr(count);
        // Объявляем массив с ёмкостью, заданной в переменной count
        int[] randSetOfNum = new int[numOfElem.getArray().length];
        System.out.println ("То, что сгенерировал главный поток:");
        // сформировать случайный массив со случайными числами
        for (int indexVal = 0; indexVal < randSetOfNum.length; indexVal ++) {
            randSetOfNum[indexVal] = (int) (Math.random() * randSetOfNum.length + 1);
            Thread.sleep (60);
        }
        receiveValues = randSetOfNum;
        System.out.println (Arrays.toString (receiveValues));
        // запись массива в общий синхронизированный ресурс
        numOfElem.put (receiveValues);
        // Чтение результата
        new SumOfElem (numOfElem, 1);
        new ArithmeticMean (numOfElem, 1);
    }

    static class SynchroThr {
        // Скрытые внутренние поля класса
        private int[] AI;
        private boolean formArray;
        // Конструктор - получает извне размер массива
        SynchroThr(int size) {
            // Сформировать массив
            AI = new int[size];
            formArray = false;
        }
        // Метод синхронизированного чтения массива
        synchronized int[] get() {
            // ожидать, пока не будет сформирован массив
            while (!formArray) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println (e.getMessage ());
                }
            }
            // Изменить флажок взаимодействия
            formArray = false;
            // Сообщить другому потоку, что массив получен
            notify();
            // Вернуть результат
            return AI;
        }
        // Метод синхронизированной записи массива чисел
        synchronized void put (int[] _AI) {
            // Ожидать, пока не будет прочитан массив (formArray=false)
            while (formArray) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println (e.getMessage());
                }
            }
            // Записать массив в общий ресурс
            AI = _AI;
            // Изменить флажок взаимодействия между потоками
            formArray = true;
            // Сообщить другому потоку, что массив сформирован
            notify();
        }
        // Метод доступа к массиву AI
        int[] getArray() {
            return AI;
        }

    }

    static class SumOfElem implements Runnable {

        final SynchroThr numOfElem;
        // Объявление переменной потока
        static Thread flowPointer_1;
        final int[] initVal, resultArray;

        SumOfElem (SynchroThr numOfElem, int resultArray) {
            this.numOfElem = numOfElem;
            // Инициализация массива
            initVal = new int[receiveValues.length];
            this.resultArray = new int[]{resultArray};
            // Создание потока
            flowPointer_1 = new Thread (this, "SumOfElem");
            flowPointer_1.start();
        }

        @Override
        public void run() {
            int sum = 0;
            // Получаем массив чисел из синхронизированного потока
            int[] AI = numOfElem.get();
            for (int j : AI) sum = sum + j;
            for (int receiveValue : AI) sum += receiveValue;
            sum = sum / 2;
            // запись массива в общий синхронизированный ресурс
            numOfElem.put(AI);
            try {
                // Задержка на 0.6 секунды
                Thread.sleep (600);
                System.out.println ("Значение, полученное в I-ом побочном потоке:\n" + sum);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    static class ArithmeticMean implements Runnable {

        final SynchroThr numOfElem;
        static Thread flowPointer_2;
        final int[] initVal, resultArray;

        ArithmeticMean (SynchroThr numOfElem, int resultArray) {
            this.numOfElem = numOfElem;
            // Инициализация массива
            initVal = new int[receiveValues.length];
            this.resultArray = new int[]{resultArray};
            // Создание потока
            flowPointer_2 = new Thread (this, "ArithmeticMean");
            flowPointer_2.start();
        }

        @Override
        public void run() {
            int sum = 0;
            double average;
            int[] AI = numOfElem.get();
            for (int j : AI) sum = sum + j;
            for (int receiveValue : AI) sum += receiveValue;
            average = (sum * 0.5) / AI.length;
            numOfElem.put(AI);
            try {
                // Задержка на 1 секунду
                Thread.sleep (1000);
                System.out.println ("Значение, полученное во II-ом побочном потоке:\n" + average);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}