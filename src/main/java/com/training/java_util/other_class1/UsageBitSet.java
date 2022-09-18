package main.java.com.training.java_util.other_class1;

/*    JAVA. Util - other classes.
   Закрепить материал - по возможности реализовать
   задания своими способами:
      * реализовать класс BitSet */

import java.util.Arrays;
import java.util.BitSet;

public class UsageBitSet {
    public static void main (String[] args) {
        // Создание беспорядочного массива значений arrays типа int[]
        int[] arrays = new int[] {
                100, 58, 78, 44, 22, 33, 73, 81, 43, 64 };
        // Следование размеру битового набора, который должен быть больше,
        // чем максимальное значение и должен быть кратен минимальному значению
        BitSet bitArr = new BitSet(128);
        BitSet bitArrAdd = new BitSet(16);
        BitSet bitArrDemo = new BitSet(16);
        for (int index : arrays) {
            // BitSet обновит значение индекса на true
            bitArr.set(index);
        }
        // Обновление значений BitSet размера в переменной i
        for (int i = 0 ; i < bitArr.size() ; i ++) {
            if (bitArrDemo.get(i)) System.out.println(i);
        } // условие для упорядочивания массива значений с помощью класса BitSet
        // Обновление значений в переменной i до 16
        for (int i = 0; i < 16; i ++) {
            if ((i % 2) == 0) bitArrDemo.set(i);
            if ((i % 2) != 0) bitArrAdd.set(i);
        }
        System.out.println("исходные беспорядочные значения в arrays: ");
        System.out.println(Arrays.toString(arrays));
        System.out.println("закономерность, реализованная в bitArr: ");
        // Вывод упорядоченного массива значений arrays типа int[]
        System.out.println(bitArr);
        System.out.println("исходная закономерность в bitArrDemo: ");
        System.out.println(bitArrDemo);
        // Логическое AND, добавляющее содержимое вызываемого объекта BitSet
        bitArrDemo.and(bitArrAdd);
        System.out.println("bitArrDemo AND bitArrAdd: ");
        System.out.println(bitArrDemo);
        // Логическое OR, добавляющее содержимое вызываемого объекта BitSet
        bitArrDemo.or(bitArr);
        System.out.println("bitArrDemo OR bitArr: ");
        System.out.println(bitArrDemo);
        // Логическое XOR, добавляющее содержимое вызываемого объекта BitSet
        bitArrDemo.xor(bitArrAdd);
        System.out.println("bitArrDemo XOR bitArrAdd: ");
        System.out.println(bitArrDemo);
    }
}
