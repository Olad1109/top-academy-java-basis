/* Объявляем пакет arrays, необходимый для размещения в нём классов.*/
package main.java.com.training.arrays;
// Объявляем класс InitArray, использующий модификатор public, открывающий
// доступ для всех пакетов и классов данного java-приложения.
public class InitArray {
    // Объявляем метод main()
    public static void main (String[] argCommStr) {
        // Объявляем двумерный массив elemArray.
        int[][] elemArray = new int[3][3];
        // Присваиваем значения элементам массива.
        elemArray[0][0] = 14;
        elemArray[0][1] = 29;
        elemArray[1][0] = 0;
        elemArray[1][1] = 42;
        elemArray[0][2] = 40;
        elemArray[1][2] = 2;
        elemArray[2][0] = 6;
        elemArray[2][1] = 7;
        elemArray[2][2] = 27;
        // Создаём условие для корректного вывода двумерного массива в консоль.
        for (int i = 0; i < elemArray.length; i ++) {
            for (int j = 0; j < elemArray[i].length; j ++)
                System.out.print (elemArray[i][j] + "\t");
            System.out.println ();
        }

    }

}