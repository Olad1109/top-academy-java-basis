package main.java.com.training.generics;

/*   Generics!!!
   Создать программу, используя обобщённые типы и метасимвольные переменные
   по вычислению результат деления суммы элементов числовых (Integer,
   Double типов) массивов на количество элементов, добавить логику по проверке
   одинаково ли это значение у различных массивов. Продемонстрировать работу
   в демо-классе. */

class MyGenEx {
    // Применяем универсальный метод. Объявляем то, что T может быть любым
    // типом, который является подклассом Number.
    static class subMyGenEx<T extends Number> {
        T[] arrays; // объявление массивов типа T
        public subMyGenEx (T[] arrays) {
            this.arrays = arrays;
        } // использование ключевого слова this для ссылки на массивы
        double meanValue() {
            double sum = 0.0;
            for (T array : arrays) sum += array.doubleValue ();
            return sum / arrays.length;
        } // возвращение значения переменной sum
        boolean sameValue (subMyGenEx<?> n) {
            return meanValue () == n.meanValue ();
        } // возвращение значения переменной meanValue c аргументом "?"
    }

    public static void main (String[] args) {
        // Упаковка элементов массива в объект Integer
        Integer[] aArray = {2, -14, 39};
        subMyGenEx<Integer> aN = new subMyGenEx<> (aArray);
        double v = aN.meanValue ();
        System.out.println ("Результат (aN) деления суммы элементов" +
                "\nмассива A на их кол-во равен: " + v);
        // Упаковка элементов массива в объект Double
        Double[] bArray = {5.0, 25.0, -3.0};
        subMyGenEx<Double> bN = new subMyGenEx<>(bArray);
        double b = bN.meanValue();
        System.out.println ("Результат (bN) деления суммы элементов" +
                "\nмассива B на их кол-во равен: " + b);
        System.out.print ("Результат сравнения (aN) и (bN): ");
        // Определение условий для вывода конечного результата
        if (aN.sameValue (bN)) {
            System.out.println ("они равны!");
        } else {
            System.out.println ("они не равны!");
        }
    }

}