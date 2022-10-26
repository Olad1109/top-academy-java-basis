package main.java.com.training.stream_app_program_interface.recursive;

/*   ДЗ - Stream API.
   Посчитать произведение 700 элементов массива типа double с применением
   параллельных вычислений, используя класс RecursiveTask для задания
   логики выполняемой задачи, запустить выполнение задачи с применением
   потоков вычисления, заданных ForkJoinPool. Пороговое значение подобрать
   самостоятельно. */

// Реализация ExecutorService - интерфейса для отслеживания асинхронных задач,
// где ForkJoinPool необходим для выполнения задач, отправленных в Pool
import java.util.concurrent.ForkJoinPool;
// Класс для получения рекурсивного результата через ForkJoinTask
import java.util.concurrent.RecursiveTask;

class MultiplicationBase {

    public static void main (String[] argCommStr) throws InterruptedException {
        MultiplicationBase multiplicationBase = new MultiplicationBase();
        multiplicationBase.taskLogic();
    }
    // Объявление метода taskLogic() с проверкой на ожидание, либо прерывание потока
    void taskLogic() throws InterruptedException {
        double roundNum, multiplication;
        // Распределение задач по рабочим потокам в пуле потоков
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        double[] nums = new double[700];
        for (int n = 0; n < nums.length; n ++) {
            nums[n] = 0 + (Math.random() * (4));
            roundNum = nums[n];
            nums[n] =  Math.round (roundNum * 1000) / 1000.0d;
            System.out.println (nums[n] + " ");
            Thread.sleep (1);
        }
        Multi task = new Multi (nums, 0, nums.length);
        // Выполнение задачи, возвращая ее результат по завершении
        multiplication = forkJoinPool.invoke (task);
        System.out.println ("Произведение элементов массива: ");
        Thread.sleep (300);
        System.out.printf ("%.4f", multiplication);
    }

    static class Multi extends RecursiveTask<Double> {

        final int thresholdValue = 70;
        double[] data;
        int start, end;

        Multi (double[] data, int start, int end) {
            this.data = data;
            this.start = start;
            this.end = end;
        }

        @Override
        // Основные вычисления, выполняемые этой задачей
        protected Double compute() {
            double multi = 1;
            if ((end - start) < thresholdValue) {
                for (int indexVal = start; indexVal < end; indexVal ++) {
                    multi *= data[indexVal];
                }
            } else {
                int middle = (start + end) / 2;
                Multi subtaskA = new Multi (data, start, middle);
                Multi subtaskB = new Multi (data, middle, end);
                // Асинхронное выполнение этой задачи в пуле с помощью метода fork()
                // посредством разделения на потоки
                subtaskA.fork();
                subtaskB.fork();
                // Используем join() для объединения потоков
                multi *= subtaskA.join() * subtaskB.join();
            } return multi;

        }

    }

}