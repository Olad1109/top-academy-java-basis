package main.java.com.training.java_util.other_class3;

/*    JAVA. Util - other classes 3.
    Создать директорию, поместить в нее несколько файлов, в том числе файл с
    расширением *.rtf. Далее вывести все файлы, содержащиеся в этом каталоге
    с расширением *.rtf */

import java.io.File;
import java.util.Objects;
import java.util.Scanner;
// Объявляем класс DirectoryWithRTF
class DirectoryWithRTF {
    // Объявляем считыватель данных в переменной scanner
    Scanner scanner = new Scanner (System.in);
    // Создаём объект File и объявляем его в переменных TargetDir и TargetDirGo
    File TargetDir = null;
    File TargetDirGo;
    // Применяем метод main, активируя Exception, для выявления возможных исключений,
    // когда работает JVM
    public static void main (String[] argCommStr) throws Exception {
        // Объявляем метод DirectoryWithRTF(), открывающий доступ к управлению над
        // параметрами значений чисел и строк, которые используются в методе menuRTF()
        DirectoryWithRTF dirLittleWithRTF = new DirectoryWithRTF();
        dirLittleWithRTF.menuRTF();
    }
    // Объявляем метод menuRTF() через public - общедоступный модификатор доступа
    void menuRTF() {
        System.out.println ("Работа с директориями: ");
        System.out.println ("[directory][outRTF][exit]");
        // Создаём цикл рабочего меню
        while (true) {
            System.out.println ("Нужно ввести одну из команд: ");
            // Устанавливаем switch - оператор выбора case-ов, запускающий
            // нужную часть кода, доступ к которой открывается, в данном случае,
            // через переменную scanner и метод next()
            switch (scanner.next()) {
                case "directory" -> BuildDir();
                case "outRTF" -> OutputRTF();
                case "exit" -> {
                    System.out.println ("Выход из программы!");
                    return;
                } default -> {}
            }
        }
    }
    // Объявляем метод BuildDir() через приватный модификатор доступа
    private void BuildDir() {
        // Создание нового экземпляра File, с последующим использованием абстрактного
        // пути, что бы записать его в переменную TargetDir
        TargetDir = new File ("src/main/java/com/training/java_util/other_class3");
        // Потребность в создании каталога
        boolean needToCreateADir;
        System.out.println ("Укажем название директории: ");
        // Создаём абстрактный каталог
        TargetDirGo = new File (TargetDir + "/" + scanner.next());
        System.out.print ("Необходимая директория ");
        // Проверка на реальность каталога
        if (TargetDirGo.exists() && TargetDirGo.isDirectory()) {
            System.out.println ("существует");
        } else {
            System.out.println ("не существует");
            // Применяем метод, создающий новый пустой неабстрактный каталог
            needToCreateADir = TargetDirGo.mkdir();
            if (needToCreateADir) System.out.println ("Создаём новую директорию!");
        }
        System.out.println ("Сохраняем адрес директории:\n" + TargetDirGo);
    }

    private void OutputRTF() {
        File folder = TargetDirGo;
        if (folder == null) {
            System.out.println ("Папка ещё не выбрана!");
            return;
        }
        // Объявляем массив строк, используя list() - метод фильтра
        String[] list = folder.list ((dirStr, name) ->
                // Проверка на наличие суффикса - последовательности символов *.rtf с
                // преобразованием в нижний регистр
                name.toLowerCase().endsWith(".rtf"));
        System.out.println ("Вывод файлов с расширением *.rtf:");
        // Условие вывода AssertionError
        assert list != null;
        for (String formatRTF : list) System.out.println (formatRTF);
        System.out.println ("Активация вывода файлов всех расширений (true or false): ");
        // Получение логического значения из токена ввода через метод nextBoolean()
        boolean outputAdd = scanner.nextBoolean();
        if (outputAdd) {
            System.out.println ("Выводим:");
            // Проверка указанной ссылки на нулевое значение объекта и получения
            // абстрактного пути, что бы вывести список имён
            for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
                if (fileEntry.isDirectory()) {
                    // Выводим имена файлов
                    System.out.println (fileEntry);
                } else {
                    // Вывод последнего имени в последовательности имён файлов
                    System.out.println (fileEntry.getName());
                }
            }
        }
    }

}
