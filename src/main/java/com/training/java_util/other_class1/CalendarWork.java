package main.java.com.training.java_util.other_class1;

/*    JAVA. Util - other classes.
   Закрепить материал - по возможности реализовать
   задания своими способами:
      * реализовать классы Calendar, Date */

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarWork {
    public static void main (String[] argCommStr) {
        // Создаем объект-массив из 12 элементов
        String[] months = {"Янв", "Фев", "Мар", "Апр", "Май", "Июн", "Июл",
                "Авг", "Сен", "Окт", "Ноя", "Дек"};
        int year = 0;
        // Создание григорианского календаря, инициализированного
        // текущей датой и временем в локализации и часовом поясе по умолчанию
        GregorianCalendar grCalendar = new GregorianCalendar();
        // Создаем объект-массив из 7 элементов
        String[] toDay = {"Суббота", "Воскресенье", "Понедельник",
                "Вторник", "Среда", "Четверг", "Пятница"};
        System.out.println ("Вывод даты с помощью класса Calendar");
        System.out.print ("Сегодня: ");
        // Вывод времени и даты на данный момент с помощью абстрактного класса
        // Calendar, предоставляющего методы работы со значениями полей календаря
        System.out.print (grCalendar.get(Calendar.DATE));
        System.out.print (" " + months[grCalendar.get(Calendar.MONTH)] + " ");
        // Тест на то, является ли текущий год високосным
        if (grCalendar.isLeapYear(year)) {
            System.out.println (grCalendar.get(Calendar.YEAR) + " (високосный год)");
        } else {
            System.out.println (grCalendar.get(Calendar.YEAR) + " (не високосный год)");
        }
        System.out.print ("Точное время: ");
        System.out.print (grCalendar.get(Calendar.HOUR_OF_DAY) + " ч. ");
        System.out.print (grCalendar.get(Calendar.MINUTE) + " мин. ");
        System.out.println (grCalendar.get(Calendar.SECOND) + " cек.");
        System.out.println ("День недели: " + toDay[grCalendar.get(Calendar.DAY_OF_WEEK)]);
        Date date = new Date();
        System.out.println ("\nВывод даты с помощью класса Date\n" + date);
    }
}
