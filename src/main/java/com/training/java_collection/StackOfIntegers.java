package main.java.com.training.java_collection;

/*   JCF!
   Реализуйте класс "Stack" для работы с целыми значениями. Стек должен иметь
   фиксированный размер. Реализуйте набор операций для работы со стеком:
     * помещение целого значения в стек,
     * выталкивание целого значения из стека,
     * подсчет количества целых в стеке,
     * проверку пустой ли стек,
     * проверку полный ли стек,
     * очистку стека,
     * получение значения без выталкивания верхнего целого в стеке.
   При старте приложения нужно отобразить меню с помощью, которого
   пользователь может выбрать необходимую операцию. */

import java.util.Scanner;
import java.util.Stack;

class StackOfIntegers {
    // Объявляем стек контейнера целых значений
    Stack<Integer> integerStack = new Stack<>();
    // Объявляем анализатор вводимых данных
    Scanner scanner = new Scanner (System.in);
    int fixSize = 4; // фиксированный размер заполненного стека
    public static void main (String[] args) {
        StackOfIntegers stackOfIntegers = new StackOfIntegers();
        stackOfIntegers.menu();
    }
    void menu() {
        System.out.println("Добро пожаловать в Стек-меню!\nДоступные команды:");
        System.out.println("[exit][input][del][value]\n[fullness][clear][last][output]");
        while (true) {
            System.out.println("Введите одну из команд: ");
            // Объявляем оператор выбора, находим и возвращаем следующий полный токен из
            // этого сканера
            switch (scanner.next()) {
                case "exit": {
                    System.out.println("Выход из Стек-меню!");
                    return;
                } case "input": {
                    pushInStack();
                    break; // ввод целого значения в стек
                } case "del": {
                    popFromStack();
                    break; // удаление числового элемента
                } case "value":{
                    countStack();
                    break; // подсчёт кол-ва чисел в стеке
                } case "fullness": {
                    isEmpty();
                    break; // проверка стека на заполненность
                } case "clear": {
                    cleanStack();
                    break; // очистка стека
                } case "last": {
                    peekStack();
                    break; // последнее значение, внесённое в стек
                } case "output": {
                    printStack(); // вывод всего стека
                } default:
            }
        }
    }

    void pushInStack() {
        if (integerStack.size() > fixSize) {
            System.out.println("Вводим целое число: ");
            integerStack.push(scanner.nextInt());
            System.out.println("Значение " + integerStack.remove(0) + " покинуло стек");
            System.out.println("Значение " + integerStack.peek() + " помещено в стек");
        } else {
            System.out.println("Вводим целое число: ");
            integerStack.push(scanner.nextInt());
            System.out.println("Значение " + integerStack.peek() + " помещено в стек");
        }
    }

    void popFromStack() {
         System.out.println("Ввод index-а элемента, желаемого к удалению: ");
         int indexDel = scanner.nextInt();
         System.out.println("Значение " + integerStack.remove(indexDel) + " покинуло стек");
    }

    void countStack () {
            System.out.println("Количество элементов - " + integerStack.size());
    }

    void isEmpty() {
        if (integerStack.isEmpty()) {
            System.out.println("Стек пуст");
        } else if (integerStack.size() < fixSize + 1) {
            System.out.println("Стек в пределах нормы");
        } else {
            System.out.println("Стек полон");
        }
    }

    void cleanStack() {
        System.out.println("Стек очищен");
    }

    void peekStack() {
        System.out.println("Последнее значение, внесённое в стек: " + integerStack.peek());
    }

    void printStack() {
            System.out.println(integerStack);
            System.out.println("Вывод стека успешно произведён!");
    }
}
