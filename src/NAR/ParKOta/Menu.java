package NAR.ParKOta;

public enum Menu {
    MAIN(0),
    LOGIN(1),
    REGISTER(2),
    EXIT(3),
    PARK_CONTROL_USER(4),
    PARKING_IN(5),
    PARKING_OUT(6);

    private final int menuId;
    private Menu(int menuId){
        this.menuId = menuId;
    }
    public int getMenuId(){
        return this.menuId;
    }
}
