package main.java.com.training.pet;

/*
   ДЗ по пройденному материалу:
добавить в созданный класс Собака поля пол, возраст, порода.
Создать несколько конструкторов в данном классе с различными
вариантами параметров, создать несколько перегруженных методов change
с различными вариантами и количеством параметров, создать несколько
объектов данного класса согласны разным конструкторам и продемонстрировать
работу методов в методе main. */

class DogDescription {
    static class Pet {
        String pet, name, gender;
        int age = 0;
        double weight = 0.0;
        String getPet() {
            return this.pet;
        }

        void setPet (String pet) {
            this.pet = pet;
        }

        String getName() {
            return this.name;
        }

        void setName (String name) {
            this.name = name;
        }

        String getGender() {
            return this.gender;
        }

        void setGender (String gender) {
            this.gender = gender;
        }

        int getAge() {
            return this.age;
        }

        void setAge (int age) {
            this.age = age;
        }

        double getWeight() {
            return this.weight;
        }

        void setWeight (double weight) {
            this.weight = weight;
        }
    }

    static class Dog extends Pet {
        String breed;
        // default constructor
        Dog() {
            breed = "";
        }
        // accessor and mutator
        String getBreed() {
            return breed;
        }

        void setBreed (String breed) {
            this.breed = breed;
        }
        // method for lifespan
        String getLifespan() {
            if (this.getWeight() > 16) {
                return "примерно 5 лет";
            } else return "примерно 7 лет";
        }
    }

    static public void main (String[] args) {
        Pet pet = new Pet();
        pet.setPet ("Собака");
        System.out.println ("Животное: " + pet.getPet());
        // CREATING INSTANCES
        Dog dog = new Dog();
        dog.setName ("Тучка");
        dog.setGender ("Самка");
        dog.setAge (10);
        dog.setWeight (14.5);
        dog.setBreed ("Мопс");
        System.out.println ("[Кличка: " + dog.getName() + ", Пол: " + dog.getGender() +
                ", " + "Возраст: " + dog.getAge() + " лет, Вес: " + dog.getWeight() + " кг," +
                "\n" + "        " +
                "Порода: " + dog.getBreed()+", Осталось жить: " + dog.getLifespan() + "]");
        dog.setName ("Рекс");
        dog.setGender ("Самец");
        dog.setAge (15);
        dog.setWeight (20);
        dog.setBreed ("Овчарка");
        System.out.println ("[Кличка: " + dog.getName() + ", Пол: " + dog.getGender() +
                ", Возраст: " + dog.getAge() + " лет, Вес: " + dog.getWeight() + " кг," +
                "\n" + "        " +
                "Порода: " + dog.getBreed() + ", Осталось жить: " + dog.getLifespan() + "]");
        pet.setPet ("Слон");
        System.out.println ("Животное: " + pet.getPet());
        System.err.println ("Сбой! Информация отсутствует!");
    }
}
