package main.java.com.training.java_util.other_class1;

/*    JAVA. Util - other classes.
   Закрепить материал - по возможности реализовать
   задания своими способами:
      * реализовать класс StringTokenizer */

import java.util.StringTokenizer;

public class StrTokenWork {
    // Создаём строки str0, str1, str2, разделяемые на лексемы
    static String str0 = "имя:Фёдор|фамилия:Губошлёпец|e-Mail:fed.guboshlep23@mail.ru";
    static String str1 = "имя:Маша|фамилия:Рублёва|e-Mail:masha.rubleva@mail.ru";
    static String str2 = "используем StringTokenizer для разбора строки на лексемы.";

    public static void main(String[] args) {
        // Вызываем конструктор класса StringTokenizer,
        // отвечающий за строки и разделители
        StringTokenizer st0 = new StringTokenizer(str0, ":|");
        StringTokenizer st1 = new StringTokenizer(str1, ":|");
        StringTokenizer st2 = new StringTokenizer(str2, " .");
        // Использование интерфейса Enumeration, который занимается
        // перебором объектов, входящих в коллекцию.
        while (st0.hasMoreTokens()) {
            System.out.println(st0.nextToken() + " - " + st0.nextToken());
        } // вывод лексем в виде объекта типа String
        // Один из методов Enumeration-а, проверяющий наличие лексем
        while (st1.hasMoreTokens()) {
            System.out.println(st1.nextToken() + " - " + st1.nextToken());
        }
        int i = 1;
        while (st2.hasMoreTokens()) {
            System.out.println((i++) + " " + st2.nextToken());
        }
    }
}
