package main.java.com.training.rand_num;

import java.io.File;
import java.io.IOException;
import java.util.Random;

// (new FileReader("src/main/java/com/example/data/transactions.txt")))

public class RandomNumber {
    public static void main(String[] args) throws IOException {
        // Генерация случайного числа
        Random random = new Random();
        int randomNumber = random.nextInt(9000) + 1000;

        // Запись числа в текстовый документ
        File file = new File("src/main/java/com/training/randNum/random_number.txt");
        try (var writer = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
                new java.io.FileOutputStream(file, true)))) {
            writer.write(String.valueOf(randomNumber));
            writer.newLine();
            writer.flush();
        }

        System.out.println("Сгенерированное случайное 4-х значное число: " + randomNumber);
        System.out.print("Введите это число для проверки: ");

        /*       do {
            System.out.println("Ошибка ввода! Повторите попытку.");
            System.out.print("Введите число: ");
            input = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();
        } while (!input.equals(String.valueOf(randomNumber)));
*/
        Random random_1 = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 20; i ++) {
            char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                    'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                    'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
            char randomLetter = alphabet[random_1.nextInt(62)];
            stringBuilder.append(randomLetter);
        }

        String str2 = stringBuilder.toString().trim();
        System.out.println(str2);

        System.out.println("Введённый код верен, авторизация произошла успешно!");
    }
}
