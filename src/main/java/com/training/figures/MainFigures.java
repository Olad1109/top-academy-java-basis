package main.java.com.training.figures;

import java.util.Random;

public class MainFigures {

    public static void main (String[] args) {
        Figure[] figures = new Figure[4];
        figures[0] = new Ellipse (4, 4, 1, 0);
        figures[1] = new Rectangle (5, 5, 2, 4);
        figures[2] = new Circle (5, 3, 2);
        figures[3] = new Square (4, 3, 1);
        printFiguresArray (figures);
        System.out.println ("---------------------------" +
                "------------------------------------------");
        changeCoordinateOfMovableFigures (figures);
        printFiguresArray (figures);
    }

    private static void changeCoordinateOfMovableFigures (Figure[] figures) {
        int randomX = new Random ().nextInt (5);
        int randomY = new Random ().nextInt (5);
        for (Figure figure : figures) {
            if (figure instanceof Movable) {
                ((Movable) figure).move(randomX, randomY);
            }
            if (figure instanceof Scalable) {
                ((Scalable) figure).getPerimeter();
            }
        }
    }

    public static void printFiguresArray(Figure[] figures) {
        for (Figure figure : figures) {
            figure.getPerimeter ();
            figure.printPerimeter ();
            if (figure instanceof Movable) {
                ((Movable) figure).printCoordinate ();
            }
            if (figure instanceof Scalable) {
                ((Scalable) figure).printPerimeter ();
            }
        }
    }

    public abstract static class Figure {
        int x, y;
        Figure (int x, int y) {
            this.x = x;
            this.y = y;
        }
        int getX () {
            return x;
        }
        void setX (int x) {
            this.x = x;
        }
        int getY () {
            return y;
        }
        void setY (int y) {
            this.y = y;
        }
        abstract double getPerimeter ();
        abstract void printPerimeter ();
    }

    public static class Circle extends Ellipse {
        int r1;
        Circle (int r1, int x, int y) {
            super (r1, r1, x, y);
            this.r1 = r1;
        }
        int getR1 () {
            return r1;
        }
        void setR1 (int r1) {
            this.r1 = Math.max (r1, 0);
        }
        @Override
        public double getPerimeter () {
            return 2 * PI * Math.pow (((Math.pow (x, 2)
                    + Math.pow (y, 2)) / 2), 0.5);
        }
        @Override
        public void printPerimeter () {
            System.out.println ("Периметр круга равен "
                    + String.format ("%.1f", this.getPerimeter ()));
        }
        @Override
        public void move (int x, int y) {
            this.setX (this.getX () + x);
            this.setY (this.getY () + y);
        }
        @Override
        public void printCoordinate () {
            System.out.println ("Координаты центра круга: х = "
                    + this.getX () + ", y = " + this.getY ());
        }
    }

    public static class Ellipse extends Figure implements Movable {
        double PI = Math.PI;
        int r1, r2;
        public Ellipse (int r1, int r2, int x, int y) {
            super (x, y);
            this.r1 = r1;
            this.r2 = r2;
        }
        int getR1 () {
            return r1;
        }
        void setR1 (int r1) {
            this.r1 = Math.max (r1, 0);
        }
        int getR2 () {
            return r2;
        }
        void setR2 (int r2) {
            this.r2 = Math.max (r2, 0);
        }
        @Override
        public double getPerimeter () {
            return 2 * PI * Math.pow (((Math.pow (r1, 2)
                    + Math.pow (r2, 2)) / 2), 0.5);
        }
        @Override
        public void printPerimeter () {
            System.out.println ("Периметр эллипса равен "
                    + String.format ("%.1f", this.getPerimeter ()));
        }
        @Override
        public void move (int r1, int r2) {
            this.setR1(this.getR1 () + r1);
            this.setR2(this.getR2 () + r2);
        }
        @Override
        public void printCoordinate () {
            System.out.println ("Координаты центра эллипса: r1 = " + this.getR1 ()
                    + ", r2 = " + this.getR2 ());
        }
    }

    public static class Rectangle extends Figure implements Movable {
        int sideA, sideB;
        public Rectangle (int sideA, int sideB, int x, int y) {
            super (x, y);
            this.sideA = sideA;
            this.sideB = sideB;
        }
        int getSideA () {
            return sideA;
        }
        void setSideA (int sideA) {
            this.sideA = Math.max (sideA, 0);
        }
        int getSideB () {
            return sideB;
        }
        void setSideB (int sideB) {
            this.sideB = Math.max (sideB, 0);
        }
        @Override
        public double getPerimeter () {
            return 2 * (this.getSideA () + this.getSideB ());
        }
        @Override
        public void printPerimeter () {
            System.out.println ("Периметр прямоугольника равен "
                    + this.getPerimeter ());
        }
        @Override
        public void move (int a, int b) {
            this.setSideA (this.getSideA () + a);
            this.setSideB (this.getSideB () + b);
        }
        @Override
        public void printCoordinate () {
            System.out.println ("Координаты центра прямоугольника: sideA = "
                    + this.getSideA () + ", sideB = " + this.getSideB ());
        }
    }

    static class Square extends Rectangle {
        public Square (int sideA, int x, int y) {
            super (x, y, sideA, sideA);
        }
        @Override
        public double getPerimeter () {
            return 4 * (this.getSideA ()) + Math.pow (y, 1);
        }
        @Override
        public void printPerimeter () {
            System.out.println ("Периметр квадрата равен "
                    + this.getPerimeter ());
        }
        @Override
        public void move (int x, int y) {
            this.setX (this.getX () + x);
            this.setY (this.getY () + y);
        }
        @Override
        public void printCoordinate () {
            System.out.println ("Координаты центра квадрата: х = "
                    + this.getX () + ", y = " + this.getY ());
        }
    }

    interface Movable {
        void move (int x, int y);
        void printCoordinate ();
    }

    interface Scalable {
        void getPerimeter ();
        void printPerimeter ();
    }

}