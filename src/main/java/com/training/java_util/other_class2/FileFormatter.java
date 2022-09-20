package main.java.com.training.java_util.other_class2;

/*    JAVA. Util - other classes 2.
    Создать программу, вычисляющую разницу между кубом и квадратом суммы целых чисел,
    записанных в текстовый файл с помощью FileWriter и считанного с применением Scanner
    входного потока данных типа FileReader. Конечный результат, вывести в консоль с
    применением Formatter в виде строки шириной в 20 символов с точностью 3 знака после
    запятой, выровнять по левому краю */

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
// Создаём класс FileFormatter
class FileFormatter {
    // Объявляем считыватель данных в переменную scanner
    Scanner scanner = new Scanner (System.in);
    // Создаём переменную size типа данных 32-битной точности
    int size;
    // Объявляем строчный тип данных в переменной format1
    String format1;
    // Объявляем тип данных 64-битной точности, с плавающей запятой,
    // в переменных: arrayValue, emptyArray
    double[] arrayValue;
    double[] emptyArray = new double[] {0}; // добавляем начальное значение массива
    // Применяем метод main, активируя Exception, для выявления возможных исключений,
    // когда работает JVM
    public static void main (String[] args) throws Exception {
        // Объявляем метод FileFormatter(), открывающий доступ к управлению над
        // параметрами числа и строки, которые используются в методе menuOfFileForm()
        FileFormatter fileFormatter = new FileFormatter();
        fileFormatter.menuOfFileForm();
    }
    // Применяем метод menuOfFileForm(), использующий Exception
    void menuOfFileForm() throws Exception {
        System.out.println ("Добро пожаловать в программу!\nДоступные команды:");
        System.out.println ("[exit][input][calculate][read]");
        // Создаём цикл рабочего меню
        while (true) {
            System.out.println ("Можно ввести одну из команд: ");
            // Устанавливаем switch - оператор выбора case-ов, запускающий нужную часть кода,
            // доступ к которой открывается, в данном случае, через переменную scanner и метод next()
            switch (scanner.next()) {
                case "exit": {
                    System.out.println ("Выход из программы!");
                    return;
                } case "input": {
                    dataRecording(); // запись данных
                    break; // прерывание действия блока кода, за которое оно отвечает
                } case "calculate": {
                    calculateOutput(); // использование данных в вычислениях
                    break;
                } case "read": {
                    ReadAndSaveData(); // чтение данных с их сохранением в массив
                } default:
            }
        }
    }
    // Применяем метод dataRecording(), где возможны исключения в работе с внешними данными
    void dataRecording() throws IOException {
        try (Scanner forInput = new Scanner (System.in)) {
            double RoundVal;
            System.out.println ("Укажем количество элементов массива: ");
            // Считываем с клавиатуры размер массива и записываем в size
            size = forInput.nextInt();
            // Объявляем и создаём массив arrayValue c размером в переменной size
            arrayValue = new double[size];
            System.out.println ("Заполняем массив: ");
            // Используем целочисленное значение в переменной i, которую будем
            // использовать в качестве индекса
            for (int i = 0; i < size; i ++) {
                arrayValue[i] = forInput.nextDouble();
                // Округление до трёх цифр после запятой
                RoundVal = Math.round(arrayValue[i] * 1000) / 1000.0d;
                arrayValue[i] = RoundVal;
            }
        }
        System.out.println ("Подтверждение: ");
        // Объявляем преобразователь данных в строку, доступную к реконструкции
        StringBuilder format2 = new StringBuilder();
        // Проверка ложных значений
        assert false;
        for (int index = 0; index < arrayValue.length; index ++) {
            // Создаём массив элементов типа String из arrayValue, где начальный и конечный символы
            // станут строками, символ запятой используем в качестве разделителя, по итогу цикла
            // начальный и конечный элементы удаляются
            format1 = arrayValue[index] + (index != arrayValue.length - 1 ? ", " : "");
            // Добавление символов аргумента String с помощью переменной format1
            format2.append(String.format(format1));
        }
        System.out.println (format2);
        // Передача значений массива arrayValue переменной arrOutput
        double[] arrOutput = arrayValue;
        // Выводим массив на печать
        System.out.println ("Стандартный вывод элементов массива: \n"
                + Arrays.toString(arrOutput));
        String path = "src/main/java/com/training/java_util/other_class2/Example.txt";
        // String path = "src\\main\\java\\com\\training\\java_util\\other_class2\\Example.txt"; // Для Windows
        // Создание абстрактного класса File, где конструктор создает новый экземпляр класса,
        // преобразуя указанную строку pathname в абстрактный путь.
        File file = new File(path);
        // Потребность в создании файла
        boolean NeedForANewFile;
        System.out.println ("Оценка необходимости в создании *.txt: ");
        // Проверяем наличие файла по указанному абстрактному пути
        if (!file.exists()) {
            // Применяем метод, атомарно создающий новый пустой файл
            NeedForANewFile = file.createNewFile();
            System.out.println (NeedForANewFile +
                    "\nСтановимся свидетелями рождения файла: " + "\n" + path);
        } else {
            NeedForANewFile = false;
            System.out.println (NeedForANewFile +
                    "\nТекстовый файл был уже создан ранее: " + "\n" + path);
        }
        // Создание объекта FileWriter, необходимый для записи данных во внешний источник
        FileWriter writer = new FileWriter (file);
        // Проверка наличия данных
        assert format2 != null;
        // Запись содержимого в файл
        writer.write (format2.toString());
        System.out.println ("Запись в текстовый файл произведена!");
        writer.flush(); // сброс потока данных
        writer.close(); // закрытие потока данных
    }

    void calculateOutput() {
        double sum = 0;
        double BasicSum;
        if (size > 0) {
            // Используем ':' для присваивания значений arrayValue переменной j, имеющий тип double
            for (double j : arrayValue) {
                sum = sum + j;
            }
        } else {
            // Создаём условие на случай отсутствия числовых данных от пользователя
            for (double j : emptyArray) {
                sum = sum + j;
            }
        }
        BasicSum = sum;
        // Используем класс данных Math, содержащий методы для выполнения основных операций с числами
        double differenceOfValues = Math.pow(BasicSum, 3) - Math.pow(BasicSum, 2);
        // Устанавливаем предельное количество чисел после плавающей запятой в differenceOfValues
        differenceOfValues = Math.round(differenceOfValues * 1000) / 1000.0d;
        System.out.print ("Команда принята, вычисления произведены:"
                + "\nBasicSum^3 - BasicSum^2 = ");
        // Используем %.3f на случай нуля после запятой
        System.out.printf ("%.3f", differenceOfValues);
        System.out.println();
    }

    void ReadAndSaveData() {
        File file = new File ("src/initLearn/JUtil/otherClass2/Example.txt");
        // File file = new File ("src\\initLearn\\JUtil\\otherClass2\\Example.txt"); // Для Windows
        if (!file.exists()) {
            System.err.println ("Файл отсутствует!");
            return;
        }
        // Ограничение по количеству символов в одной строке
        int count = 20;
        // Прогрессирующее количество символов, с учётом всех возможных строк
        int countDynamic = 0;
        // Создаём переменную buf 16-битного типа char, используем индекс count
        char[] buf = new char[count];
        char[] buf2;
        int numOfChPerLine;
        // Объявляем экземпляр line класса String
        String line;
        // Создание объекта FileReader в блоке кода возможных исключений
        try (FileReader fr = new FileReader(file)) {
            System.out.println ("Вывод прочтённых данных с ограничением ширины строк в "
                    + count + " символов:");
            // Объявляем цикл "пока", указываем условия считывания данных извне
            while ((numOfChPerLine = fr.read(buf)) > 0) {
                // Используем класс Arrays, реализующий метод, который копирует указанный массив,
                // усекая или дополняя нулевыми символами, что бы сохранить заданную длину массива
                buf = Arrays.copyOf(buf, numOfChPerLine);
                System.out.println(buf);
                // Считывание символов
                countDynamic += numOfChPerLine;
            }
            // Перезапись данных о количестве символов в переменной buf2
            buf2 = new char[countDynamic];
            // Поиск исключений и создание массива из считанных данных
            try (FileReader fr1 = new FileReader(file)) {
                while ((numOfChPerLine = fr1.read(buf2)) > 0) {
                    buf2 = Arrays.copyOf(buf2, numOfChPerLine);
                }
            }
            // Возврат значения строкового представления аргумента buf2 массива char[] в переменную line
            line = String.valueOf(buf2);
            System.out.println ("Количество символов с учётом пробелов: " + countDynamic);
            // Разбиение строки вокруг совпадений заданного регулярного выражения в массиве строк
            String[] numbersStrings = line.split(",");
            double[] numbers = new double[numbersStrings.length];
            // Управляем параметрами числа и строки, используем метод length, отвечающий за длину
            // строки, присваиваем объекту numbers[i] значение примитивного типа double методом valueOf
            for (int i = 0; i < numbersStrings.length; i ++) {
                numbers[i] = Double.parseDouble(numbersStrings[i]);
                // Возврат значений в переменные size и arrayValue
                size = i;
                arrayValue = numbers;
            }
            System.out.println ("Вывод в виде массива сохранённых данных: ");
            System.out.println (Arrays.toString(numbers));
        } catch (IOException e) { // сигналы о том, что произошло какое-то исключение ввода-вывода
            // Реализация конструктора, необходимая для обработки исключений времени выполнения
            throw new RuntimeException(e);
        }
    }
}