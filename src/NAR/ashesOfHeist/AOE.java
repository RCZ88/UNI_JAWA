package NAR.ashesOfHeist;

public enum AOE {
    SINGLE_TARGET(1),
    TWO_RANDOM(2),
    ALL_TEAM(3), //enemy attacking to team
    ALL_ENEMY(4); //team attacking to enemies

    private int numberOfTarget;
    private AOE(int numberOfTarget){
        this.numberOfTarget = numberOfTarget;
    }

    public static AOE getAOE(int index){
        switch (index){
            case 1 -> {
                return SINGLE_TARGET;
            }
            case 2 ->{
                return TWO_RANDOM;
            }
            case 3 ->{
                return ALL_TEAM;
            }
            case 4 ->{
                return ALL_ENEMY;
            }
        }
        return null;
    }

    public int getNumberOfTarget() {
        return numberOfTarget;
    }

    public void setNumberOfTarget(int numberOfTarget) {
        this.numberOfTarget = numberOfTarget;
    }
}
