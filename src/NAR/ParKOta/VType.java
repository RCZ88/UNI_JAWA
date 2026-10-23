package NAR.ParKOta;

public enum VType {
    MOTORCYCLE(0),
    CAR(1),
    TRUCK(2);

    private int index;
    private VType(int index){
        this.index = index;
    }

    public int getIndex(){
        return index;
    }
}
