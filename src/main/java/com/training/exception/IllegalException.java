package main.java.com.training.exception;

public class IllegalException {

    public static void main (String[] args) {
        try {
            AreaCheck.printResult(90);
        } catch (Exception ex) {
            ex.printStackTrace ();
        }
    }

    static class IllegalAreaException extends Exception {
        public IllegalAreaException (double x) {
            System.err.println ("Значение не допустимо: " + x);
            System.exit (0);
        }
    }

    static class AreaCheck {
        public static void printResult (double x) throws IllegalAreaException {
            if (x >= 1) {
                if (x <= 120) {
                    System.out.println ("Значение допустимо: " + x);
                } else throw new IllegalAreaException (x);
            } else throw new IllegalAreaException (x);
        }
    }

}
