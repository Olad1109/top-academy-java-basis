package main.java.com.training.java_io.next;

import static main.java.com.training.java_io.next.WorkWithFiles_part_1.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.swing.JFrame;
// Используем расширение JFrame для поддержки архитектуры компонентов
// JFC/Swing - набор библиотек классов для создания графического интерфейса
public class WorkWithFiles_part_2 extends JFrame {
    // Объявляем метод wordDelete()
    void wordDelete() throws IOException {
        int[] strLength;
        String[] arrStr;
        String search_Word;
        int del;
        if (getSelectFile == null) {
            userOut.println ("Программе необходимо прочитать текст!");
        } else {
            userOut.println ("Ввод количества слов, предлагаемых к удалению из "
                    + getSelectFile + ":");
            del = userGo.nextInt();
            arrStr = new String[del];
            strLength = new int[del];
            boolean addCurrentStr = true;
            userOut.println ("Вводим слова, предлагаемые к удалению:");
            while (addCurrentStr) {
                for (del = 0; del < arrStr.length; del ++) {
                    String nxt = userGo.next();
                    arrStr[del] = nxt;
                    strLength[del] = nxt.length();
                    userOut.println ("Количество букв в слове[" + arrStr[del] + "]: "
                            + strLength[del]);
                }
                if (del == arrStr.length) addCurrentStr = false;
            }
            for (del = 0; del < arrStr.length; del ++) {
                int countDel = 0;
                try (FileInputStream fis_2 = new FileInputStream(getFirstFile);
                     InputStreamReader isr_2 = new InputStreamReader(fis_2);
                     BufferedReader br_2 = new BufferedReader(isr_2)) {
                    // Используем метод getProperty(), что бы сохранить конструкцию текста
                    String END = System.getProperty ("line.separator");
                    // Используем построитель строк
                    StringBuilder sb_2 = new StringBuilder();
                    String ln_2;
                    while ((ln_2 = br_2.readLine()) != null) {
                        search_Word = arrStr[del];
                        sb_2.append (ln_2.replaceAll(search_Word + " ",
                                "")).append(END);
                        countDel ++;
                    }
                    br_2.close();
                    // Записываем текст в буферизированный поток вывода
                    BufferedWriter bw_2 = new BufferedWriter (new FileWriter(getFirstFile));
                    bw_2.write (sb_2.toString());
                    bw_2.close();
                    userOut.println ("Количество удалений слова[" + arrStr[del] + "]: "
                            + countDel);
                }
            }
        }
    }

    void dataTransfer() throws IOException {
        int fileCount, requireOfFiles, readByte;
        String selectObject, extractText;
        File[] needFiles;
        boolean addCurrentFile = true;
        userOut.println ("Ввод количества файлов, в которых хранятся необходимые данные:");
        requireOfFiles = userGo.nextInt();
        needFiles = new File[requireOfFiles];
        userOut.println ("Введём названия файлов:");
        while (addCurrentFile) {
            for (fileCount = 0; fileCount < requireOfFiles; fileCount ++) {
                selectObject = userGo.next();
                needFiles[fileCount] = new File (TargetDirGo + "/" + selectObject);
                ByteArrayOutputStream readContent = new ByteArrayOutputStream();
                try (FileInputStream is = new FileInputStream (needFiles[fileCount])) {
                    while ((readByte = is.read()) != -1) {
                        readContent.write(readByte);
                    }
                }
                extractText = readContent.toString();
                // Кодируем эту строку в последовательность байтов
                byte[] utf8_Bytes = extractText.getBytes (StandardCharsets.UTF_8);
                File newFile = new File (getFirstFile.toURI());
                FileWriter write_1 = new FileWriter (newFile, true);
                // (OutputStream fos = new FileOutputStream(newFile);)
                write_1.append(extractText).append("\n");
                write_1.flush();
                write_1.close();
                // (fos.write(extractText.getBytes());
                // fos.close();)
                userOut.println ("Количество перенесённых байт из файла[" + fileCount + "]: "
                        + utf8_Bytes.length);
            }
            if (fileCount == requireOfFiles) addCurrentFile = false;
        }
    }
}