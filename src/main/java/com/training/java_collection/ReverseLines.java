package main.java.com.training.java_collection;

/*   JCF!
   Ввести строки с консоли, записать в список, вывести строки в
   консоль в обратном порядке. */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ReverseLines {
    public static void main (String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print ("Введите количество строк: ");
            int x = 0, y;
            // Возвращаем true тогда и только тогда, когда следующий токен этого сканера
            // является допустимым значением int
            if (scanner.hasNextInt()) {
                x = scanner.nextInt();
            } else {
                System.out.print ("Введены некорректные данные!");
            }
            // Читаем текст из потока ввода символов, буферизуя символы
            BufferedReader reader = new BufferedReader (new InputStreamReader (System.in));
            // Используем реализацию интерфейса List с динамическим размером массива
            // посредством упаковки строк в контейнер
            ArrayList<String> strings = new ArrayList<> ();
            for (y = 0; x > y; y ++) {
                // Добавляем указанный элемент в конец этого списка
                strings.add (reader.readLine());
            }
            // Возвращаем количество элементов в ArrayList<>
            for (y = strings.size() - 1; y >= 0; y --) {
                // Возвращаем элемент в указанной позиции
                System.out.println (strings.get(y));
            }
        }
    }
}
