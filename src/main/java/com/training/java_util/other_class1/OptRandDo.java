package main.java.com.training.java_util.other_class1;

/*    JAVA. Util - other classes.
   Закрепить материал - по возможности реализовать
   задания своими способами:
      * реализовать классы Optional, Random */

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class OptRandDo {
    public static void main (String[] args) {
        // Упаковка значений new ArrayList в numbers
        ArrayList<Integer> numbers = new ArrayList<>();
        // Применение методов stream() и min() с использованием сортировки
        // (Integer::compare) с последующим сравнением значений
        Optional<Integer> min = numbers.stream().min(Integer::compare);
        Random rand = new Random();
        int randomNum = 0, total = 0;
        int count = 3; // кол-во чисел, нужных для вывода средних значений
        for(int i = 0; i < count; i++) {
            // Условия получения значений до 70
            randomNum = rand.nextInt(70);
            System.out.println(randomNum);
            // Отслеживание общих значений
            total += randomNum;
        }
        // Поиск среднеарифметического с помощью Integer
        int average = total / count;
        System.out.printf ("Среднее значение (без остатка): %d%n", average);
        // Деление с помощью Double
        double averageDouble = total / ((double) count);
        System.out.printf ("Среднее значение (с остатком): %7.3f%n", averageDouble);
        // Метод orElseGet() задаёт функцию, которая будет возвращать
        // значение по умолчанию
        System.out.println ("Случайное число: " + min.orElseGet(()->rand.nextInt(120)));
    } // генерация числа класса Random с помощью метода nextInt()
}
