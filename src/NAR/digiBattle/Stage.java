package NAR.digiBattle;

public enum Stage {
    ROOKIE(20), CHAMPION(30), ULTIMATE(-1);
    int evolveReq;
    Stage(int evolveReq){
        this.evolveReq = evolveReq;
    }
    public static final Stage []stages = new Stage[]{Stage.ROOKIE, Stage.CHAMPION, Stage.ULTIMATE};

}
