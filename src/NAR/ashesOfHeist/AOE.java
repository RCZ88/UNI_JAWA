package NAR.ashesOfHeist;

public enum AOE {
    SINGLE_TARGET(1),
    TWO_RANDOM(2),
    ALL_TEAM(3),
    ALL_ENEMY(4);

    private int numberOfTarget;
    private AOE(int numberOfTarget){
        this.numberOfTarget = numberOfTarget;
    }

    public int getNumberOfTarget() {
        return numberOfTarget;
    }

    public void setNumberOfTarget(int numberOfTarget) {
        this.numberOfTarget = numberOfTarget;
    }
}
