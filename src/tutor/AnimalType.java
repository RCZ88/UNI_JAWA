package tutor;

public enum AnimalType {
    COW(1),
    SHEEP(2);


    int legs;
    private AnimalType(int legs){
        this.legs = legs;
    }
}
