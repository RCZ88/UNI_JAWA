package NAR.digiBattle;

import java.util.*;

/** Runtime state for one Digimon. Base stats are level-1 values. */
public class Digimon {

    private String species;
    private int atSlot;
    private Element element;
    private Stage stage;
    private int level, exp, hp, mp;
    private final int baseHp, baseMp, baseAttack, baseDefence, baseSpeed;
    private final int hpGrowth, mpGrowth, attackGrowth, defenceGrowth, speedGrowth;
    private int attackChange, defenceChange, speedChange, effectTurns;
    private boolean guarding;
    private final List<Skill> skills;

    public Digimon(String species, Element element, Stage stage, int level, int exp,
                   int baseHp, int baseMp, int baseAttack, int baseDefence, int baseSpeed,
                   int hpGrowth, int mpGrowth, int attackGrowth, int defenceGrowth,
                   int speedGrowth, List<Skill> skills, int levelCap) {
        this.species=species;
        this.element=element;
        this.stage=stage;
        this.level=level;
        this.exp=exp;
        this.baseHp=baseHp;
        this.baseMp=baseMp;
        this.baseAttack=baseAttack;
        this.baseDefence=baseDefence;
        this.baseSpeed=baseSpeed;
        this.hpGrowth=hpGrowth;
        this.atSlot = -1;
        this.mpGrowth=mpGrowth;
        this.attackGrowth=attackGrowth;
        this.defenceGrowth=defenceGrowth;
        this.speedGrowth=speedGrowth;
        this.skills = new ArrayList<>();
        if (skills != null) this.skills.addAll(skills);
        this.hp=getMaxHp();
        this.mp=getMaxMp();
    }

    public String getSpecies(){
        return species;
    }
    public Element getElement(){
        return element;
    }
    public Stage getStage(){return stage;}
    public int getLevel(){return level;}
    public int getExp(){return exp;}
    public int getHp(){return hp;}
    public int getMp(){return mp;}
    public List<Skill> getSkills(){return Collections.unmodifiableList(skills);}
    public boolean isAlive(){return hp > 0;}
    public boolean isGuarding(){return guarding;}
    public int getMaxHp(){return baseHp + hpGrowth * (level-1);}
    public int getMaxMp(){return baseMp + mpGrowth * (level-1);}
    public int getAttack(){return baseAttack + attackGrowth*(level-1) + attackChange;}
    public int getDefence(){return baseDefence + defenceGrowth*(level-1) + defenceChange;}
    public int getSpeed(){return baseSpeed + speedGrowth*(level-1) + speedChange;}


    public void takeDamage(int amount) {
        // TODO: Subtract damage from HP, with a minimum HP of 0.
        if(hp-amount >= 0){
            hp-=amount;
            return;
        }
        hp = 0;
    }
    public int heal(int amount) {
        // TODO: Restore HP up to max HP; return the actual amount restored.
        int amt = Math.min(getMaxHp() - hp, amount);
        hp += amt;
        return amt;
    }
    public boolean spendMp(int amount) {
        if(amount > mp) return false;
        mp -= amount;
        return true;
    }
    public void defend() {
        guarding = true;
        mp += Math.min(getMaxMp()-5, 5);
    }
    public double consumeGuardMultiplier() {
        // TODO: Return 0.5 if guarding, else 1.0, then clear guarding.
        double g =  guarding ? 0.5 : 1.0;
        guarding = false;
        return g;
    }
    public void applyStatChange(int atk, int def, int spd, int turns) {
        // TODO: Apply temporary stat changes and duration. Negative values are debuffs.
        attackChange += atk;
        defenceChange += def;
        speedChange += spd;
        effectTurns += turns;
    }
    public boolean gainExp(int amount) {
        int levelRaise = 0;
        while(amount- (level * 80) > 0){
            levelRaise++;
            amount -= (level * 80);
        }
        exp += (amount + exp) % (level * 80);
        level += levelRaise;
        return levelRaise > 0;
    }

    public void  digivolve(){

    }
    public void learnSkill(Skill skill) {
        skills.add(skill);
    }


    public int getAtSlot() {
        return atSlot;
    }

    public void setAtSlot(int atSlot) {
        this.atSlot = atSlot;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getBaseHp() {
        return baseHp;
    }

    public int getBaseMp() {
        return baseMp;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public int getBaseDefence() {
        return baseDefence;
    }

    public int getBaseSpeed() {
        return baseSpeed;
    }

    public int getHpGrowth() {
        return hpGrowth;
    }

    public int getMpGrowth() {
        return mpGrowth;
    }

    public int getAttackGrowth() {
        return attackGrowth;
    }

    public int getDefenceGrowth() {
        return defenceGrowth;
    }

    public int getSpeedGrowth() {
        return speedGrowth;
    }

    public int getAttackChange() {
        return attackChange;
    }

    public void setAttackChange(int attackChange) {
        this.attackChange = attackChange;
    }

    public int getDefenceChange() {
        return defenceChange;
    }

    public void setDefenceChange(int defenceChange) {
        this.defenceChange = defenceChange;
    }

    public int getSpeedChange() {
        return speedChange;
    }

    public void setSpeedChange(int speedChange) {
        this.speedChange = speedChange;
    }

    public int getEffectTurns() {
        return effectTurns;
    }

    public void setEffectTurns(int effectTurns) {
        this.effectTurns = effectTurns;
    }

    public void setGuarding(boolean guarding) {
        this.guarding = guarding;
    }
}
