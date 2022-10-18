package main.java.com.training.java_io.next;
// Обеспечение системного ввода и вывода через потоки данных
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
// Используем структуру коллекций, отвечающих за поддержку интернационализации,
// загрузчик служб, свойства, генерацию случайных чисел, синтаксис
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Способ сделать пользовательские API доступными для всех приложений,
// где swing выполняет обработку в ответ на событие, сгенерированное
// жестом пользователя
import javax.swing.JOptionPane;
// Объявляем класс с расширением WorkWithFiles_part_2
public class WorkWithFiles_part_1 extends WorkWithFiles_part_2 {
    // Объявляем входной поток в переменную userGo
    static Scanner userGo = new Scanner (System.in);
    // Объявляем переменную userOut для выходного потока
    static PrintStream userOut = new PrintStream (System.out);
    // Объявляем абстрактное представление путей к файлам
    File TargetDir = null, file_path, firstFile;
    static File TargetDirGo, getFirstFile;
    String selectFile;
    static String getSelectFile;
    // Конструктор динамической строки
    StringBuilder strForDialog = new StringBuilder();
    // Объявляем метод main()
    public static void main (String[] argCommStr) throws IOException {
        // Открываем доступ к методам через метод main()
        WorkWithFiles_part_1 workWithFiles_part_1 = new WorkWithFiles_part_1();
        workWithFiles_part_1.menu_ForTheUser();
    }

    void menu_ForTheUser() throws IOException {
        userOut.println("""
                Команды меню:\s
                [getInfo][wordRepl][wordDel][transfer][exit]""");
        // Создаём цикл рабочего меню
        while (true) {
            userOut.println ("Вводим одну из команд: ");
            // Устанавливаем switch - оператор выбора case-ов, запускающий
            // нужную часть кода, доступ к которой открывается, в данном случае,
            // через переменную userGo и метод next()
            switch (userGo.next()) {
                case "getInfo" -> GetInfoAboutAFile();
                case "wordRepl" -> wordReplacement();
                case "wordDel" -> wordDelete();
                case "transfer" -> dataTransfer();
                case "exit" -> {
                    userOut.println ("Выход из программы!");
                    return;
                }
            }
        }
    }

    void GetInfoAboutAFile() throws IOException {
        String replStr_Final;
        // Создание нового экземпляра File, с последующим использованием
        // абстрактного пути, что бы записать его в переменную TargetDir
        TargetDir = new File ("");
        strForDialog = new StringBuilder();
        // Потребность в создании каталога
        boolean NeedToCreateADir, hasNumber = false;
        userOut.println ("Укажем полный путь к нужной директории: ");
        // Создаём абстрактный каталог
        TargetDirGo = new File (TargetDir + userGo.next());
        userOut.print ("Необходимая директория ");
        // Проверка на реальность каталога
        if (TargetDirGo.exists() && TargetDirGo.isDirectory()) {
            userOut.println ("существует");
        } else {
            userOut.println ("не существует");
            // Применяем метод, создающий новый пустой неабстрактный каталог
            NeedToCreateADir = TargetDirGo.mkdir();
            if (NeedToCreateADir)
                userOut.println ("Создаём директорию, в которой есть нужда!");
        }
        userOut.println ("Сохраняем адрес директории!\n" +
                "Ввод названия файла, текст из которого нужно проявить:");
        selectFile = userGo.next();
        // Используем префиксы и строки
        file_path = new File (TargetDirGo + "/" + selectFile);
        // Объявляем абстрактный путь к файлу
        firstFile = new File (file_path.toURI());
        getSelectFile = selectFile;
        getFirstFile = firstFile;
        assert false;
        int punctuationMarks = 0, letters = 0, numbers = 0, numStr;
        try (FileInputStream fis = new FileInputStream (firstFile);
             InputStreamReader isr = new InputStreamReader (fis);
             BufferedReader reader = new BufferedReader (isr)) {
            while ((replStr_Final = reader.readLine()) != null) {
                // Применяем регулярное выражение, что бы вернуть длину строки
                // без учёта символов, не указанных в нём, используем символ
                // отрицания - "^" для этой цели
                punctuationMarks += replStr_Final.replaceAll
                        ("[^,.:;!?]", "").length();
                letters += replStr_Final.replaceAll
                        ("[^a-zA-Z\\u0400-\\u04FF]", "").length();
                for (numStr = 0; numStr < replStr_Final.length(); numStr ++) {
                    // Проверка значений примитивного типа в строке replStr_Final
                    // на принадлежность к символам цифр
                    if (Character.isDigit(replStr_Final.charAt(numStr))) {
                        hasNumber = true;
                    } else if (!Character.isDigit(replStr_Final.charAt(numStr))
                            && hasNumber) {
                        ++ numbers;
                        hasNumber = false;
                    }
                }
                if (hasNumber) {
                    ++ numbers;
                }
                strForDialog.append (replStr_Final);
            }
        }
        if (strForDialog.length() < 95) {
            userOut.printf ("|%10.94s|", strForDialog);
        } else {
            userOut.printf ("|%10.91s...|", strForDialog);
            // Вывод содержимого текстового документа в окне java-приложения
            JOptionPane.showMessageDialog (null,
                    "<html><body><p style='width: 420px;'>" + strForDialog
                            + "</p></body></html>",
                    selectFile, JOptionPane.INFORMATION_MESSAGE);
        }
        userOut.println ("\nКоличество знаков препинания: " + punctuationMarks
                + "\nКоличество букв: " + letters
                + "\nКоличество чисел: " + numbers);
    }

    void wordReplacement() throws IOException {
        String allStr, search_Word, wordGMO;
        // Объявляем класс для компиляции регулярного выражения
        Pattern pattern;
        // Объявляем класс для сопоставления символов
        Matcher matcher;
        int count = 0;
        getSelectFile = selectFile;
        if (selectFile == null) {
            userOut.println ("Программе необходимо прочитать текст!");
        } else {
            allStr = String.valueOf (strForDialog);
            userOut.println ("Ввод слова, которое необходимо найти в "
                    + selectFile + ":");
            search_Word = userGo.next();
            pattern = Pattern.compile (search_Word);
            matcher = pattern.matcher (allStr);
            // Поиск входной последовательности символов
            while (matcher.find()) {
                if (allStr.toLowerCase().contains(search_Word.toLowerCase())) {
                    // Определение количества вхождений строки в переменную search_Word
                    count ++;
                }
            }
            userOut.print ("Введите слово-заменитель: ");
            wordGMO = userGo.next();
            try (FileInputStream fis_1 = new FileInputStream (firstFile);
                 InputStreamReader isr_1 = new InputStreamReader (fis_1);
                 BufferedReader br_1 = new BufferedReader(isr_1)) {
                // Используем метод getProperty(), что бы сохранить конструкцию текста
                String END = System.getProperty ("line.separator");
                // Используем построитель строк
                StringBuilder sb_1 = new StringBuilder();
                String ln_1;
                while ((ln_1 = br_1.readLine()) != null) {
                    sb_1.append(ln_1.replaceAll(search_Word, wordGMO)).append(END);
                    count ++;
                } br_1.close();
                // Записываем текст в буферизированный поток вывода
                BufferedWriter bw_1 = new BufferedWriter (new FileWriter(firstFile));
                bw_1.write (sb_1.toString());
                bw_1.close();
                count = count / 2;
            }
            userOut.println ("Количество замен: " + count);
        }
    }
}