package tutor;

public class Animal {
    String name;
    String color;
    int age;
    int hunger;
    static int animalCount;
    AnimalType animalType;

    public Animal(String name, String color, AnimalType type){
        this.name = name;
        this.color = color;
        this.animalType = type;
    }
    public Animal(String name, String  color, int age){
        this.name = name;
        this.color = color;
        this.age = age;
    }

    public static int getAnimalCount(){
        return animalCount;
    }

    public void eat(Food food){
        hunger += food.value;
    }
}

class Sapi extends Animal{
    String mooSound;

    public Sapi(String cowName, String color){
        super(cowName,  color, AnimalType.COW);
    }
}