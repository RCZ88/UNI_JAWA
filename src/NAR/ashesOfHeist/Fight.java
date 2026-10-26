package NAR.ashesOfHeist;

import java.util.ArrayList;

public abstract class Fight {
    ArrayList<Enemy>enemies;
    Player player;

    public Fight(Player player){
        this.player = player;
        enemies = new ArrayList<>();
    }

    public abstract void onWin();
}

class Dungeon extends Fight{
    DungeonTier dt;
    public Dungeon(Player team, DungeonTier dt) {
        super(team);
        enemies.addAll(MOB.generateMobs(team, dt));
    }

    @Override
    public void onWin() {
        for(int i = 0; i<dt.getNumberOfArtifact(); i++){
            player.getArtifacts().add(Artifact.getInstance());
        }
        for (Ally ally : player.getTeam().getAllies()) {
            ally.receiveXp(dt.getXpPerCharReceive(enemies.getFirst().getLevel()));
        }
    }
}

class BossFight extends Fight{

    public BossFight(Player player, BOSS boss) {
        super(player);
        enemies.add(boss);
    }

    @Override
    public void onWin() {
        BOSS b = (BOSS) enemies.getFirst();
        if(player.getBossesDefeatedIndex().contains(b.getIndex())){
            return;
        }
        if (b.isCharOrArtifact() == BOSS.ARTIFACT) {
            player.increaseArtifactCap();
        } else {
            player.increaseCharacterCap();
        }
    }
}
