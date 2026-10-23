package NAR.ashesOfHeist;

public enum AttackType {

    ATTACK(0),
    BASIC(1),
    SKILL(2),
    INTERMEDIATE(3),
    ULTIMATE(4);

    public static final AttackType[]ATTACK_TYPES = new AttackType[]{ATTACK, BASIC, SKILL, INTERMEDIATE, ULTIMATE};
    private int index;
    private AttackType(int index){
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
