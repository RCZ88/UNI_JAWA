package NAR.ashesOfHeist;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.IntConsumer;

public abstract class Unit {
    public static final double[][] multiplier = new double[][]{
            new double[]{1, 1.5, 0.8},
            new double[]{1.5, 1, 0.8},
            new double[]{0.8, 1.5, 1},
    };
    protected static final int BASE_STAT = 0;
    protected static final int ARTIFACT_STAT = 1;
    protected static final int NO_CUSTOM_MULTIPLIER = 1;
    public static double getMultiplier(Unit from, Unit to){
        return multiplier[from.element.getIndex()][to.element.getIndex()];
    }

    public final List<IntConsumer> actions = List.of(
            this::addAtk, this::addHp,
            this::addCritDamage, this::addCritRate,
            this::addDef, this::addMaxEnergy,
            this::addSpeed
    );


    private Element element;
    private int level;
    private int[] maxHp = new int[2];
    private int[] atk = new int[2];
    private int[] def = new int[2];
    private int[] maxEnergy = new int[2];
    private int[] speed = new int[2];
    private double[] critRate = new double[2];
    private double[] critDamage = new double[2];
    private HashMap<Integer, Integer> elementDamageBonus = new HashMap<>();
    private Attack[]attacks;

    private int currentHp;
    private ArrayList<Status> statuses = new ArrayList<>();
    private int currentEnergy;

//    public Unit() {
//        this.statuses = new ArrayList<>();
//    }
    public Unit(Element element, int maxHp, int baseAtk, int baseDef, int baseSpd, int maxEnergy, int attackTypeCount){
        this.element = element;
        this.maxHp[BASE_STAT] = maxHp;
        this.atk[BASE_STAT] = baseAtk;
        this.def[BASE_STAT] = baseDef;
        this.speed[BASE_STAT] = baseSpd;
        this.maxEnergy[BASE_STAT] = maxEnergy;
        this.currentEnergy = 0;
        this.attacks = new Attack[attackTypeCount];
    }

    public abstract void loadAttacks();

    public void addNewAttack(Attack attack){
        this.attacks[attack.getAtkType().getIndex()] = attack;
    }

    public static ArrayList<Unit> loadAllUnits(String filepath){
        Path path = Path.of(filepath);
        ArrayList<Unit>units = new ArrayList<>();
        try{
            List<String> lines = Files.readAllLines(path);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return units;
    }
//    public int calculateFinalDamage(Unit target,  AttackType attackType){
//        double amplifiedDamage = (getScalingStat() * getDamageMultiplier(attackType)) * getMultiplier(this, target) * (1+ (double) getElementBonus() /100);
//        double mitigatedDamage = amplifiedDamage * 1000/ (1000+ Stat.sumStat(target.def));
//        boolean crit = Math.random()  * 100 < Stat.sumStat(critRate);
//        return (int) (crit ? (1 + Stat.sumStat(critDamage)/100) * mitigatedDamage : mitigatedDamage);
//    }
    public int calculateFinalDamage(Unit target,  Attack attack){
        double customMultiplier = 1;
        for (double attackMultiplier : attack.getMultipliers()) {
            customMultiplier *= attackMultiplier;
        }
        double amplifiedDamage = (getScalingStat() * customMultiplier) * getMultiplier(this, target) * (1+ (double) getElementBonus() /100);
        double mitigatedDamage = amplifiedDamage * 1000/ (1000+ Stat.sumStat(target.def));
        boolean crit = Math.random()  * 100 < Stat.sumStat(critRate);
        return (int) (crit ? (1 + Stat.sumStat(critDamage)/100) * mitigatedDamage : mitigatedDamage);
    }



    public int getScalingStat(){
        if(this instanceof Enemy){
            return atk[0];
        }
        switch(getElement()){
            case FIRE -> {
                return Stat.sumStat(getAtk());
            }
            case GRASS -> {
                return Stat.sumStat(getMaxHp());
            }
            case WATER -> {
                return Stat.sumStat(getDef());
            }
        }
        return -1;
    }


//    public abstract double getDamageMultiplier(AttackType attackType);

    public void doAttack(Attack attack, Unit target){
        int dmg = calculateFinalDamage(target, attack);
        addEnergy(attack.getEnergyRegeneration());
        removeEnergy(attack.getEnergyCost());
        applyDamage(dmg, target);
    }

    public void applyDamage(int dmg, Unit target){
        target.currentHp -= dmg;
    }

    public void applyAllStatusDamage(){
        for(Status s : statuses){
            applyDamage(s.getStatusDmg(), this);
            if(s.deductRoundsRemaining() == 0){
                statuses.remove(s);
            }
        }
    }

    public void addStatus(Status status){
        statuses.add(status);
    }

//    public abstract int[] getEnergyCost();
//    public abstract void attack(Unit unit);

    private int getElementBonus(){
        return elementDamageBonus.get(element.getHashIndex());
    }

    private int getHpPercentage(){
        return (int)((double) this.currentHp / Stat.sumStat(this.maxHp));
    }

    private void addHp(int hp){
        this.maxHp[ARTIFACT_STAT] += hp;
    }
    private void addAtk(int atk){
        this.atk[ARTIFACT_STAT] += atk;
    }

    private void addCritRate(int cr){
        this.critRate[ARTIFACT_STAT] +=  cr;
    }


    private void addCritDamage(int cd){
        this.critDamage[ARTIFACT_STAT] +=  cd;
    }

    private void addDef(int def){
        this.def[ARTIFACT_STAT] +=  def;
    }

    private void addSpeed(int speed){
        this.speed[ARTIFACT_STAT] +=  speed;
    }

    private void addMaxEnergy(int energy){
        this.maxEnergy[ARTIFACT_STAT] +=  energy;
    }

    public void addElementDamageBonus(int elementIndex, int edg){
        if(!elementDamageBonus.containsKey(elementIndex)){
            elementDamageBonus.putIfAbsent(elementIndex, edg);
            return;
        }
        elementDamageBonus.compute(elementIndex, (_, currentBonus) -> currentBonus + edg);
    }

    public void addEnergy(int amount){
        this.currentEnergy += amount;
    }

    public void consumeEnergy(int amt){
        this.currentEnergy -= amt;
    }

    public List<IntConsumer> getActions() {
        return actions;
    }
    public void upgradeLevel(){
        this.level++;
    }

    public int[] getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(int[] maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public int[] getSpeed() {
        return speed;
    }

    public void setSpeed(int[] speed) {
        this.speed = speed;
    }

    public double[] getCritRate() {
        return critRate;
    }

    public void setCritRate(double[] critRate) {
        this.critRate = critRate;
    }

    public double[] getCritDamage() {
        return critDamage;
    }

    public void setCritDamage(double[] critDamage) {
        this.critDamage = critDamage;
    }

    public HashMap<Integer, Integer> getElementDamageBonus() {
        return elementDamageBonus;
    }

    public void setElementDamageBonus(HashMap<Integer, Integer> elementDamageBonus) {
        this.elementDamageBonus = elementDamageBonus;
    }

    public ArrayList<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(ArrayList<Status> statuses) {
        this.statuses = statuses;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public void setCurrentEnergy(int currentEnergy) {
        this.currentEnergy = currentEnergy;
    }

    public void removeEnergy(int amount){
        this.currentEnergy -= amount;
    }

    public void removeAllEnergy(){
        this.currentEnergy = 0;
    }

    public void updateBaseAtk(int newAtk){
        this.atk[BASE_STAT] = newAtk;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }

    public int[] getAtk() {
        return atk;
    }

    public void setAtk(int[] atk) {
        this.atk = atk;
    }

    public int[] getDef() {
        return def;
    }


    public void setDef(int[] def) {
        this.def = def;
    }

    public int[] getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int []maxHp) {
        this.maxHp = maxHp;
    }

}


/*
* PreTraining	Attack	0	100% ATK to one enemy, regen 80 + level
NAR 26-1	Basic	100	100% ATK to all
NAR 27-1	Intermediate	200	200% ATK to all, regen 100

Mob max energy is fixed at 200 and never scaled.

Boss skills
Bosses have a full table in the doc. For example, EscaNor:

Attack: 0 cost, 100% to all, +50 energy.
Basic: 60 cost, 100% to all plus Burn.
Intermediate: 120 cost, 200% to all.
Ultimate: 200 cost, 400% to all plus Burn.
* */

