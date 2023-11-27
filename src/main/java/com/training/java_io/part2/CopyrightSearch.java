package main.java.com.training.java_io.part2;

/*     ДЗ JAVA.Io_2
    Основываясь на последнем примере по поиску знака авторского права,
    создать аналогичную программу с использованием ByteArrayInputStream
    и BufferedInputStream ТОЛЬКО ПО ПОИСКУ ЗНАКА ТОВАРНОГО ЗНАКА ? ® (R)
    ( &reg; ). В качестве исходной фразы взять
    - This is a &reg; but this is &reg not. */
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class CopyrightSearch {
     // Объявляем переменную nonStandard для выходного потока отдельных символов
     PrintStream nonStandard = new PrintStream (System.out, true,
            StandardCharsets.UTF_8);
    public static void main (String[] argCommStr) throws IOException {
        // Открываем доступ к методам через метод main()
        CopyrightSearch copyrightSearch = new CopyrightSearch();
        copyrightSearch.ByteArrStream();
        copyrightSearch.BuffStream();
    }

    void ByteArrStream() {
        Date date = new Date();
        int b;
        String text = "This is a &reg; but this is &reg; not";
        // Кодируем строку text в последовательность байтов
        byte[] array_1 = text.getBytes();
        // Объявляем encore - переменную внутреннего буфера, содержащего байты,
        // которые могут быть прочитаны из входного потока методом read()
        ByteArrayInputStream encore;
        // Считываем байты данных из входного потока строки array_1, где 0 - первый
        // байт для чтения, а 38 - это кол-во байтов, которые нужно считать
        encore = new ByteArrayInputStream (array_1, 0, 38);
        System.out.println ("Выводим символы из байт-кода с помощью" +
                "\nметода ByteArrayInputStream():");
        // Создаём условие считывания данных
        while ((b = encore.read()) != -1) {
            char ch = (char) b;
            System.out.println (b + " --> " + ch);
        }
        Date date1 = new Date();
        System.out.println ("Время обработки: " +
                (date1.getTime() - date.getTime()) + " мс.");
    }

    void BuffStream() throws IOException {
        Date date = new Date();
        String replStr_Before, replStr_Final;
        String file_path = "src/main/java/com/training/java_io/part2/text.txt";
        // Объявляем абстрактный путь к файлу
        File firstFile = new File (file_path);
        // Объявляем метод InputStream() для считывания данных в качестве
        // входного потока
        InputStream postFile = new FileInputStream (firstFile);
        // Создаём массив внутреннего буфера для входного потока
        BufferedInputStream byteCode = new BufferedInputStream (postFile);
        // Объявляем динамическую строку
        StringBuilder replStr_A = new StringBuilder();
        // Проверка ложных значений
        assert false;
        int code;
        System.out.println ("метода BufferedInputStream():");
        while ((code = byteCode.read()) != -1) {
            replStr_Before = String.valueOf ((char) code);
            // Увеличиваем длину динамической строки replStr_A
            replStr_A.append (replStr_Before);
            System.out.println (code + " --> " + (char) code);
        }
        // Закрываем этот входной поток и освобождаем все системные ресурсы,
        // связанные с потоком byteCode
        byteCode.close();
        Date date2 = new Date();
        System.out.println ("Время обработки: "
                + (date2.getTime() - date.getTime()) + " мс.");
        // Объявляем символ для замены последовательности символов в строке
        replStr_Final = String.valueOf(replStr_A)
                .replace("&reg;", "®");
        System.out.println ("Замена кода знака на сам знак:\n" + replStr_Final);
    }
}