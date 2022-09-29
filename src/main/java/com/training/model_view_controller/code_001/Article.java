package main.java.com.training.model_view_controller.code_001;

import static java.nio.file.StandardOpenOption.*;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

/*  Модуль: MVC
    Тема: MVC
    Создайте класс Статья. Необходимо хранить следующую информацию:
    *  название статьи;
    *  автор статьи;
    *  количество знаков;
    *  название издания или сайта, где статья была впервые опубликована;
    *  краткое описание.
    Создайте необходимые методы для этого класса. Реализуйте паттерн MVC для класса
    "Article" и код для использования модели, контроллера и представления. */
import javax.swing.JOptionPane;
// Используем расширение UsageMVC для работы метода mainMVC()
class Article extends UsageMVC {

    Scanner userInput = new Scanner (System.in);
    int numOfElem;
    String selectFile, path, lineToWrite;
    boolean exitBool = false;
    File targetDirGo, getFirstFile, whereToRecord;
    // Объявляем построитель динамической строки
    StringBuilder strForDialog;
    // Объявляем экземпляры супер-класса Object: tableHeader, articleData
    static Object[] tableHeader;
    static Object[][] articleData;

    void startMenu() throws IOException {
        System.out.println ("Добро пожаловать в приложение «Article»!");
        while (!exitBool) {
            System.out.println("""
                    -------------------------------------------------------
                    1. Детальная информация по статьям (details)?
                    2. Количество знаков в отдельной статье (numOfChar)?
                    3. Покинуть программу (exit)?
                    -------------------------------------------------------
                    Ввод необходимой команды:""");
            String command = userInput.next();
            switch (command) {
                case "details" -> details();
                case "numOfChar" -> numOfChar();
                case "exit" -> exitMessage();
            }
        }
    }

    void details() throws IOException {
        targetDirGo = new File
        ("src/main/java/com/training/model_view_controller/code_001/dataArticles");
        tableHeader = new Object[] {"characters", "sites", "descriptions", "titles", "authors"};
        String replStr_Final, file_path;
        String[] characters, sites, descriptions, titles, authors, arrayStrInfo;
        String[][] duoArray_articleStr;
        strForDialog = new StringBuilder();
        boolean select;
        int x, y = 5, z = 0, indexPlusPlus;
        System.out.println ("Добавляем (false or true) новые данные с их последующим сохранением?");
        select = userInput.nextBoolean();
        System.out.println ("Количество строк в графическом исполнении:");
        x = userInput.nextInt();
        duoArray_articleStr = new String[x][y];
        arrayStrInfo = new String[y];
        characters = new String[x];
        sites = new String[x];
        descriptions = new String[x];
        titles = new String[x];
        authors = new String[x];
        if (select) {
            System.out.println ("Заполнение массива[" + x + "][" + y + "]");
            for (numOfElem = 0; numOfElem < y; numOfElem ++) {
                System.out.print ("- столбец (" + numOfElem + "._");
                if (numOfElem == 0) {
                    System.out.println ("количество_знаков):");
                    replaceForData (characters, duoArray_articleStr);
                    whereToRecord = new File (targetDirGo + "/characters.txt");
                } else if (numOfElem == 1) {
                    System.out.println ("веб-ресурс):");
                    replaceForData (sites, duoArray_articleStr);
                    whereToRecord = new File (targetDirGo + "/sites.txt");
                } else if (numOfElem == 2) {
                    System.out.println ("краткое_описание):");
                    replaceForData (descriptions, duoArray_articleStr);
                    whereToRecord = new File (targetDirGo + "/descriptions.txt");
                } else if (numOfElem == 3) {
                    System.out.println ("название_статьи):");
                    replaceForData (titles, duoArray_articleStr);
                    whereToRecord = new File (targetDirGo + "/titles.txt");
                } else {
                    System.out.println ("автор_статьи):");
                    replaceForData (authors, duoArray_articleStr);
                    whereToRecord = new File (targetDirGo + "/authors.txt");
                }
                // Кодируем эту строку в последовательность байтов и применяем lineSeparator()
                // - разделитель строк на случай записи новых строк
                byte[] data = (lineToWrite + System.lineSeparator()).getBytes();
                // Преобразуем заданный URI в объект Path
                Path updateData = Paths.get (whereToRecord.toURI());
                // Объявляем абстрактный класс OutputStream для выходного потока
                try (OutputStream out = new BufferedOutputStream
                        // Записываем байты в APPEND - конец файла, иначе игнор, т. е.
                        // эти операции атомарны (условны)
                        (Files.newOutputStream (updateData, APPEND))) {
                    // Записываем байты из указанного массива байтов
                    out.write (data, 0, data.length);
                }
            }
            String formStr_1;
            System.out.println ("Вывод всего массива: ");
            for (String[] inputDatum : duoArray_articleStr) {
                formStr_1 = Arrays.toString (inputDatum)
                        .replace ("_", " ")
                        .replace ("[", "")
                        .replace ("]", "")
                        .trim();
                System.out.println (formStr_1);
            }
        } else {
            System.out.println ("Автозаполнение массива[" + x + "][" + y + "]");
            for (numOfElem = 0, indexPlusPlus = 0; numOfElem < y; numOfElem ++) {
                System.out.print ("- столбец (" + numOfElem + "._");
                if (numOfElem == 0) {
                    System.out.println ("количество_знаков):\n . . .");
                    file_path = String.valueOf (new File (targetDirGo + "/characters.txt"));
                } else if (numOfElem == 1) {
                    System.out.println ("веб-ресурс):\n . . .");
                    file_path = String.valueOf (new File (targetDirGo + "/sites.txt"));
                } else if (numOfElem == 2) {
                    System.out.println ("краткое_описание):\n . . .");
                    file_path = String.valueOf (new File (targetDirGo + "/descriptions.txt"));
                } else if (numOfElem == 3) {
                    System.out.println ("название_статьи):\n . . .");
                    file_path = String.valueOf (new File (targetDirGo + "/titles.txt"));
                } else {
                    System.out.println ("автор_статьи):\n . . .");
                    file_path = String.valueOf (new File (targetDirGo + "/authors.txt"));
                }
                // Объявляем абстрактный путь к файлу
                File firstFile = new File (file_path);
                // Проверка ложных значений
                assert false;
                // Объявляем метод FileInputStream() для считывания данных из файла
                try (FileInputStream fis = new FileInputStream (firstFile);
                     // Создаём мост от потоков байтов к потокам символов
                     InputStreamReader isr = new InputStreamReader (fis);
                     // Читаем текст из потока ввода символов, буферизуя символы
                     BufferedReader reader = new BufferedReader (isr)) {
                    while ((replStr_Final = reader.readLine()) != null) {
                        arrayStrInfo[z] = String.valueOf (replStr_Final);
                        if (duoArray_articleStr[indexPlusPlus][numOfElem] == null) {
                            arrayStrInfo[z] = Arrays.deepToString (new String[] {arrayStrInfo[z]})
                                    .replace ("_", " ")
                                    .replace ("[", "")
                                    .replace ("]", "")
                                    .trim();
                            duoArray_articleStr[indexPlusPlus][numOfElem] = arrayStrInfo[z];
                        }
                        if (indexPlusPlus < x - 1) {
                            indexPlusPlus ++;
                        } else indexPlusPlus = 0;
                    }
                    if (replStr_Final == null) z ++;
                    indexPlusPlus = 0;
                }
            }
        }
        articleData = duoArray_articleStr;
        mainMVC();
    }
    // Объявляем метод replaceForData() для заполнения arrayStrInfo - массива строк, содержащих
    // заголовки столбцов, и для duoArray_articleStr - массива строк под заголовками в столбцах
    private void replaceForData (String[] arrayStrInfo, String[][] duoArray_articleStr) {
        for (int j = 0; j < duoArray_articleStr.length; j ++) {
            duoArray_articleStr[j][numOfElem] = userInput.next();
            arrayStrInfo[j] = duoArray_articleStr[j][numOfElem];
            lineToWrite = Arrays.toString (arrayStrInfo)
                    .replace ("[", "")
                    .replace ("]", "")
                    .replace (", ", "\n")
                    .trim();
        }
    }

    void numOfChar() throws IOException {
        path = "src/main/java/com/training/model_view_controller/code_001/dataArticles/save";
        System.out.println (path + "\nВвод названия файла:");
        selectFile = userInput.next();
        File firstFile;
        whereToRecord = new File (path + "/" + selectFile + ".txt");
        firstFile = new File (whereToRecord.toURI());
        String replStr_Final;
        strForDialog = new StringBuilder();
        getFirstFile = firstFile;
        assert false;
        int punctuationMarks = 0, letters = 0, numUnits = 0, allOfChar;
        try (FileInputStream fis = new FileInputStream (firstFile);
             InputStreamReader isr = new InputStreamReader (fis);
             BufferedReader reader = new BufferedReader (isr)) {
            while ((replStr_Final = reader.readLine()) != null) {
                // Заменяем каждую подстроку этой строки, которая соответствует заданному
                // регулярному выражению
                punctuationMarks += replStr_Final.replaceAll
                        ("[^,.:;!?]", "").length();
                letters += replStr_Final.replaceAll
                        ("[^a-zA-Z\\u0400-\\u04FF]", "").length();
                numUnits += replStr_Final.replaceAll
                        ("[^0-9]", "").length();
                strForDialog.append(replStr_Final).append("\n");
            }
        }
        replStr_Final = String.valueOf (strForDialog).substring (0, strForDialog.length() - 1);
        // Используем метод showMessageDialog() с параметром null, сообщением и заголовком
        // в графической оболочке, заданной архитектурой Swing
        JOptionPane.showMessageDialog (null,
                "<html><body><p style='width: 520px;'>" + replStr_Final,
                selectFile, JOptionPane.INFORMATION_MESSAGE);
        allOfChar = punctuationMarks + letters + numUnits;
        System.out.println ("Отчёт по статье:");
        System.out.println ("Количество знаков препинания: " + punctuationMarks
                + "\nКоличество букв: " + letters
                + "\nКоличество цифр: " + numUnits
                + "\nОбщее количество символов: " + allOfChar);
    }

    void exitMessage() {
        exitBool = true;
        // Выход из среды выполнения кода
        Runtime.getRuntime().exit(0);
    }

}