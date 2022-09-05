package main.java.com.training.annotations;

import java.lang.annotation.*;
// Получаем классы и интерфейсы для получения рефлексивной информации о классах и объектах
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

class MainJavaAnnot {
    // Указываем контексты, в которых применим интерфейс аннотаций - @Цель,
    // где ElementType обозначает синтаксическое местоположение
    @Target ({ElementType.TYPE, ElementType.METHOD})
    // Указываем, как долго должна JVM сохранять аннотации - @Удержание
    @Retention (RetentionPolicy.RUNTIME)
    public @interface JavaAnnot {
        String name ();
        int value ();
    }

    static class showClass0 {
        @JavaAnnot (name = "Maxim P.", value = 1)
        public void printString () {}
    }

    static class showClass1 {
        @JavaAnnot (name = "Konstantin R.", value = 2)
        public void printString () {}
    }

    static class showClass2 {
        @JavaAnnot (name = "Yulia N.", value = 3)
        public void printString () {}
    }
    // Проверяем наличие определённых методов с помощью NoSuchMethodException
    // (не такого метода исключение) и безопасности через SecurityException
    public static void main (String[] args) throws NoSuchMethodException, SecurityException {
        // Объявляем экземпляры класса Class для сохранения в контейнер классов и интерфейсов
        // в запущенном Java-приложении
        Class<showClass0> showClass0 = showClass0.class;
        readAnnotationOn (showClass0);
        // Возвращаем объект Method, отражающий указанный открытый метод-член класса или интерфейса,
        // представленного этим объектом Class
        Method method0 = showClass0.getMethod ("printString");
        readAnnotationOn (method0);

        Class<showClass1> showClass1 = showClass1.class;
        readAnnotationOn (showClass1);
        Method method1 = showClass1.getMethod ("printString");
        readAnnotationOn (method1);

        Class<showClass2> showClass2 = showClass2.class;
        readAnnotationOn (showClass2);
        Method method2 = showClass2.getMethod ("printString");
        readAnnotationOn (method2);
    }
    // Представляем аннотированную конструкцию программы, работающей в JVM
    static void readAnnotationOn (AnnotatedElement element) {
        try {
            // Возвращаем класс времени выполнения объекта element при выводе
            System.out.println ("Доступ к аннотациям " + element.getClass ().getName ());
            Annotation[] annotations = element.getAnnotations ();
            for (Annotation annotation : annotations) {
                // Применяем оператор instanceof для проверки, был ли объект, на который
                // ссылается переменная annotation, создан на основе аннотации @JavaAnnot
                if (annotation instanceof JavaAnnot fileInfo) {
                    System.out.println ("Автор: " + fileInfo.name ());
                    System.out.println ("Важность: " + fileInfo.value ());
                } else return;
            }
        } catch (Exception e) {
            // Проверка исключений и их обратная трассировка
            e.printStackTrace ();
        }
    }

}
