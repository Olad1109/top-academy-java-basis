package main.java.com.training.java_nio;

import java.io.IOException;

public class Main {

    public static void main (String[] argStrComm) throws IOException {
        // Открываем доступ к методам через метод main()
        CalculateHandler calculateHandler = new CalculateHandler();
        calculateHandler.start();
    }

}
