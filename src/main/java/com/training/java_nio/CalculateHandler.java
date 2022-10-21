package main.java.com.training.java_nio;
import static java.nio.file.StandardOpenOption.*;

// Обеспечиваем движение данных между файлами, сохранение объектов классов
// в файл и чтение их из файла
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class CalculateHandler {

    String lineToWrite, path;
    // Объявляем переменные типа данных double
    double getX, getY, getZ, aCalculate, bCalculate, resultCalculate;
    // Устанавливаем модификатор доступа private, что бы закрыть прямой доступ к
    // переменным userInput, keepOn для других объектов класса CalculateHandler,
    // исключаем перезапись методов
    private final Scanner userInput = new Scanner (System.in);
    private boolean keepOn = true;

    void start() throws IOException {
        System.out.println ("Добро пожаловать в мой калькулятор!");
        while (keepOn) {
            calculate();
        }
    }

    String chooseOperator() {
    System.out.println ("""
            -----------------------------------
              +  для сложения
              -  для вычитания
              *  для умножения
              /  для деления
            -----------------------------------
            Что будем использовать?""");
        return userInput.next();
    }

    double getFirstNumber() {
        System.out.println ("Ввод первого элемента:");
        return userInput.nextDouble();
    }

    double getSecondNumber() {
        System.out.println ("Ввод второго элемента:");
        return userInput.nextDouble();
    }

    void addition (double a, double b) throws IOException {
        double a_plus_b = a + b;
        unionCalculate (a, b, a_plus_b);
    }

    void division (double a, double b) throws IOException {
        double a_div_b;
        a_div_b = a / b;
        if (b == 0) {
            a_div_b = Double.NaN;
        }
        unionCalculate (a, b, a_div_b);
    }

    void multi (double a, double b) throws IOException {
        double a_multi_b = a * b;
        unionCalculate (a, b, a_multi_b);
    }

    void sub (double a, double b) throws IOException {
        double a_minus_b = a - b;
        unionCalculate (a, b, a_minus_b);
    }
    // Используем метод, дабы исключить дублирование кода
    void unionCalculate (double a, double b, double a_and_b_Calculate)
            throws IOException {
        aCalculate = a;
        bCalculate = b;
        resultCalculate = a_and_b_Calculate;
        // Возвращаем ближайшее значение к аргументу с округлением
        getX = Math.round (a * 1000) / 1000.0d;
        getY = Math.round (b * 1000) / 1000.0d;
        if (Double.isNaN(a_and_b_Calculate)) {
            getZ = Double.NaN;
        } else {
            getZ = Math.round (a_and_b_Calculate * 10000) / 10000.0d;
        }
        System.out.println ("Получаем ответ: " + getZ);
        aSet_OfSolutions();
    }

    public void calculate() throws IOException {
        int n = 0;
        String operation;
        while (n < 1) {
            operation = chooseOperator();
            // Устанавливаем оператор выбора case-ов для использования переменных,
            // и их связь с методами chooseOperator(), getFirstNumber(), getSecondNumber()
            switch (operation) {
                case "+" -> {
                    double num1 = getFirstNumber();
                    double num2 = getSecondNumber();
                    addition (num1, num2);
                    n = 1;
                } case "-" -> {
                    double num1 = getFirstNumber();
                    double num2 = getSecondNumber();
                    sub (num1, num2);
                    n = 1;
                } case "/" -> {
                    double num1 = getFirstNumber();
                    double num2 = getSecondNumber();
                    division (num1, num2);
                    n = 1;
                } case "*" -> {
                    double num1 = getFirstNumber();
                    double num2 = getSecondNumber();
                    multi (num1, num2);
                    n = 1;
                }
            }
        } showMenu();
    }

    void showMenu() throws IOException {
        int z = 0;
        while (z != 1) {
        System.out.println ("""
              Введите необходимую команду.
              1. Произвести вычисления (calculate)?
              2. Узнать статистику вычислений (statistic)?
              3. Покинуть программу (exit)?""");
        String yesOrNo = userInput.next();
            switch (yesOrNo) {
                case "calculate" -> {
                    z = 1;
                    calculate();
                } case "exit" -> {
                    z = 1;
                    keepOn = false;
                    exitMessage();
                } case "statistic" -> loadStatistic();
            }
        }
    }

    void loadStatistic() throws IOException {
        System.out.println ("Статистика вычислений:");
        // Объявляем абстрактный путь к файлу
        File firstFile = new File (path);
        // Объявляем метод InputStream() для считывания данных в качестве входного потока
        InputStream postFile = new FileInputStream (firstFile);
        // Создаём массив внутреннего буфера для входного потока
        BufferedInputStream buffer = new BufferedInputStream (postFile);
        // Объявляем динамическую строку
        StringBuilder replStr_A = new StringBuilder();
        // Проверка ложных значений
        assert false;
        int dataFlow;
        while ((dataFlow = buffer.read()) != -1) {
            String replStr_Before = String.valueOf ((char) dataFlow);
            // Увеличиваем длину динамической строки replStr_A
            replStr_A.append (replStr_Before);
        }
        // Закрываем входной поток buffer и освобождаем все системные ресурсы,
        // связанные с потоком buffer
        buffer.close();
        // Удаляем пустую строку
        String replStr_Final = String.valueOf (replStr_A)
                .substring (0, replStr_A.length() - 1);
        System.out.println (replStr_Final);
    }

    void aSet_OfSolutions() throws IOException {
        if (aCalculate + bCalculate == resultCalculate) {
            System.out.println ("(" + getX + ") + (" + getY + ") = " + getZ);
            lineToWrite = "(" + getX + ") + (" + getY + ") = " + getZ;
        } else if (aCalculate - bCalculate == resultCalculate) {
            System.out.println ("(" + getX + ") - (" + getY + ") = " + getZ);
            lineToWrite = "(" + getX + ") - (" + getY + ") = " + getZ;
        } else if (aCalculate * bCalculate == resultCalculate) {
            System.out.println ("(" + getX + ") * (" + getY + ") = " + getZ);
            lineToWrite = "(" + getX + ") * (" + getY + ") = " + getZ;
        } else if (aCalculate / bCalculate == resultCalculate) {
            if (bCalculate == 0) {
                getZ = Double.NaN;
            }
            System.out.println ("(" + getX + ") / (" + getY + ") = " + getZ);
            lineToWrite = "(" + getX + ") / (" + getY + ") = " + getZ;
        }
        path = "src/main/java/com/training/java_nio/CalculatorData/Calculations.txt";
        if (lineToWrite != null) {
            // Объявляем абстрактный путь к файлу
            File whereToRecord = new File (path);
            System.out.println ("Потребность в создании файла: ");
            boolean NeedForANewFile;
            // Проверяем наличие файла по указанному абстрактному пути
            if (!whereToRecord.exists()) {
                // Применяем метод, атомарно создающий новый пустой файл
                NeedForANewFile = whereToRecord.createNewFile();
                System.out.println (NeedForANewFile +
                        "\nТекстовый файл создан: " + "\n" + path);
            } else {
                NeedForANewFile = false;
                System.out.println (NeedForANewFile);
            }
            // Кодируем эту строку в последовательность байтов и применяем lineSeparator()
            // - разделитель строк на случай записи новых строк
            byte[] data = (lineToWrite + System.lineSeparator()).getBytes();
            // Преобразуем заданный URI в объект Path
            Path updateData = Paths.get (whereToRecord.toURI());
            // Объявляем метод OutputStream() для выходного потока
            try (OutputStream out = new BufferedOutputStream
                    // Создаём CREATE - новый файл, если он не существует, иначе параметр
                    // игнорируется, если файл открыт для записи, записываем байты в
                    // APPEND - конец файла, иначе игнор, т. е. эти операции атомарны (условны)
                    (Files.newOutputStream(updateData, APPEND))) {
                // Записываем байты из указанного массива байтов
                out.write (data, 0, data.length);
            }
        }
    }

    void exitMessage() {
        System.out.println ("Работа программы приостановлена!");
    }

}