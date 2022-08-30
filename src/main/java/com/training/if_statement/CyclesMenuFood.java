package main.java.com.training.if_statement;

// Задание - Создать цикл - меню с выбором из 10 блюд - с вводом ключа в консоль.

import java.io.IOException;

// Объявляем класс CyclesMenuFood
public class CyclesMenuFood {
    // Объявляем передачу аргументов командной строки переменной argCommStr в методе
    // main(), бросающего исключения IOException при вводе и выводе данных, используем
    // модификатор доступа public, ключевое слово static, и void - пустой тип данных
    public static void main (String[] argCommStr) throws IOException {
        // Создаём цикл "пока" с предусловием true
        while (true) {
            System.out.println("Меню по блюдам: ");
            System.out.println(" 1. Пицца Маргарита ");
            System.out.println(" 2. Люля-кебаб ");
            System.out.println(" 3. Грибной суп ");
            System.out.println(" 4. Салат Оливье ");
            System.out.println(" 5. Сельдь под Шубой ");
            System.out.println(" 6. Винегрет ");
            System.out.println(" 7. Вишнёвый Морс ");
            System.out.println(" 8. Холодец ");
            System.out.println(" 9. Медовуха ");
            System.out.println(" A. Иван-чай ");
            System.out.print("Bаш выбор (N - отказ): ");
            // Создаём переменную choice 16-битного типа char,
            char choice = (char)
                    // Объявляем поток в классе System для ввода данных, используя
                    // метод read(), считывающий байты данных входного потока
                    System.in.read();
            char ignore;
            // Указание для программы - делать безусловно!
            do {
                ignore = (char)
                        System.in.read();
            } while (ignore != '\n'); // пока ignore не равно '\n'
            if (choice == 'N') {
                // Используем оператор return, что бы завершить работу кода
                return;
            }
            // Устанавливаем switch - оператор выбора case-ов, запускающих нужную часть кода
            switch (choice) {
                case '1' -> System.out.println("Принимаем заказ: Пицца Маргарита");
                case '2' -> System.out.println("Принимаем заказ: Люля-кебаб");
                case '3' -> System.out.println("Принимаем заказ: Грибной суп");
                case '4' -> System.out.println("Принимаем заказ: Салат Оливье");
                case '5' -> System.out.println("Принимаем заказ: Селдь под Шубой");
                case '6' -> System.out.println("Принимаем заказ: Винегрет");
                case '7' -> System.out.println("Принимаем заказ: Вишнёвый Морс");
                case '8' -> System.out.println("Принимаем заказ: Холодец");
                case '9' -> System.out.println("Принимаем заказ: Медовуха");
                case 'A' -> System.out.println("Принимаем заказ: Иван-чай");
                }
            }
        }
    }
