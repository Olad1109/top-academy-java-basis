package main.java.com.training.exception;

import java.util.Objects;
import java.util.Scanner;

public class FigureException {
    private static boolean hasNextIndividual() {
        return true;
    }

    public static void main (String[] args) {
        class figureCheck {
            public static String Ellipse, Circle, Rectangle;
            public figureCheck (String Ellipse, String Circle, String Rectangle, String Pyramid,
                                String Square, String Parallelogram, String Tetrahedron) {
                figureCheck.Ellipse = Ellipse;
                figureCheck.Circle = Circle;
                figureCheck.Rectangle = Rectangle;
            }

            public static void printResult (String str) throws NoSuchFigureException {
                if (Objects.equals (str, Ellipse)) {
                    System.out.println (str + " - данная фигура проработана");
                } else if (Objects.equals(str, Circle) || Objects.equals (str, Rectangle)) {
                    System.out.println (str + " - данная фигура проработана");
                } else {
                    throw new NoSuchFigureException (str);
                }
            }
        }
        new figureCheck ("Ellipse", "Circle", "Rectangle",
                "Pyramid", "Square", "Parallelogram", "Tetrahedron");
        try (Scanner scanner = new Scanner (System.in)) {
            System.out.println ("Название фигуры (English, With a capital): ");
            while (!scanner.hasNext ("Ellipse") &&
                    !scanner.hasNext ("Circle") &&
                    !scanner.hasNext ("Rectangle") &&
                    !scanner.hasNext ("Pyramid") &&
                    !scanner.hasNext ("Square") &&
                    !scanner.hasNext ("Parallelogram") &&
                    !scanner.hasNext ("Tetrahedron")) {
                System.out.println (scanner.hasNextInt());
                System.err.print ("Введённое не соответствует параметрам: "
                        + scanner.next() + "\nПовторяем ввод: ");
            }
            figureCheck.printResult (scanner.next ());
            System.out.println (hasNextIndividual() + "\nЦикл окончен!");
        } catch (Exception ex) {
            ex.printStackTrace ();
        }
    }

    static class NoSuchFigureException extends Exception {
        public NoSuchFigureException (String str) {
            boolean figureIndicator = false;
            System.out.println (figureIndicator);
            System.err.println (str + " - данная фигура не проработана");
            System.out.println ("Цикл окончен!");
            System.exit (0);
        }
    }

}