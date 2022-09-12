package main.java.com.training.generics;

/*   Generics!!!
   Создать программу, используя обобщённые типы и метасимвольные переменные
   по вычислению результат деления произведения элементов числовых (Integer,
   Double типов) массивов на количество элементов, добавить логику по проверке
   одинаково ли это значение у различных массивов. Продемонстрировать работу
   в демо-классе. */

class MyGenExMulti {
    // Применяем универсальный метод. Объявляем то, что T может быть любым
    // типом, который является подклассом Number.
    public static class subMyGenExMulti<T extends Number> {
        T[] arrays; // объявление массивов типа T
        public subMyGenExMulti (T[] arrays) {
            this.arrays = arrays;
        } // использование ключевого слова this для ссылки на массивы
        double meanValueMulti() {
            double multiply = 1.0;
            for (T array : arrays) multiply *= array.doubleValue ();
            return multiply / arrays.length;
        } // возвращение значения переменной multiply
        boolean sameValueMulti (subMyGenExMulti<Double> n) {
            return meanValueMulti () == n.meanValueMulti ();
        } // возвращение значения переменной meanValueMulti c аргументом Double
    }

    public static void main (String[] args) {
        // Упаковка элементов массива в объект Integer
        Integer[] aArray = {3, -1, 125};
        subMyGenExMulti<Integer> aN = new subMyGenExMulti<> (aArray);
        double v = aN.meanValueMulti ();
        System.out.println ("Результат (aN) деления произведения элементов" +
                "\nмассива A на их кол-во равен: " + v);
        // Упаковка элементов массива в объект Double
        Double[] bArray = {5.0, 25.0, -3.0};
        subMyGenExMulti<Double> bN = new subMyGenExMulti<> (bArray);
        double b = bN.meanValueMulti();
        System.out.println ("Результат (bN) деления произведения элементов" +
                "\nмассива B на их кол-во равен: " + b);
        System.out.print ("Результат сравнения (aN) и (bN): ");
        // Определение условий для вывода конечного результата
        if (aN.sameValueMulti(bN)) {
            System.out.println ("они равны!");
        } else {
            System.out.println ("они не равны!");
        }
    }

}
