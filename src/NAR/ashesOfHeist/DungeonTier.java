package NAR.ashesOfHeist;

public enum DungeonTier {
    EASY(1, 1),
    INTERMEDIATE(1, 1),
    HARD(4, 2),
    MAX(10, 3);

    private int statMulti;
    private int numberOfArtifact;

    DungeonTier(int statMulti,  int numberOfArtifact){
        this.statMulti = statMulti;
        this.numberOfArtifact = numberOfArtifact;
    }

    private int getLevel( int teamLevel){
        switch(this){
            case EASY -> {
                return Math.min(15, 1+teamLevel/2);
            }
            case INTERMEDIATE -> {
                return Math.min(25, 10+teamLevel/2);
            }
            default -> {
                return 25;
            }
        }
    }

    private int getXpPerCharReceive(int enemyLevel){
        switch(this){
            case EASY -> {
                return 3 + enemyLevel/3;
            }
            case INTERMEDIATE -> {
                return 10 + enemyLevel;
            }
            case HARD -> {
                return 20 + enemyLevel * 2;
            }
            case MAX ->{
                return (int) (100 + enemyLevel * 3.5);
            }
        }
        return -1;
    }

    public int getStatMulti() {
        return statMulti;
    }

    public void setStatMulti(int statMulti) {
        this.statMulti = statMulti;
    }

    public int getNumberOfArtifact() {
        return numberOfArtifact;
    }

    public void setNumberOfArtifact(int numberOfArtifact) {
        this.numberOfArtifact = numberOfArtifact;
    }
}
