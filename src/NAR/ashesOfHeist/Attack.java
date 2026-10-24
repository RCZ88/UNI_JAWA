package NAR.ashesOfHeist;

public class Attack {
    private static final int E_REQ = 0;
    private static final int E_COST = 1;
    private static final int E_REG = 2;

    private String attackName;
    private AttackType atkType;
    private double[] multipliers;
    private final MultiplierConstruct multiplierConstruct;
    private final int[]energy = new int[3];
    private final AOE aoe;
    private final boolean burn;
    private final Unit ofUnit;

    public Attack(AttackType at, int energyCost, int energyRegeneration, int multiplierCount, double []multipliers, MultiplierConstruct multiplierConstructAlgo, AOE aoe, boolean burn, Unit ofUnit){
        this.attackName = ofUnit.getAttackNames()[at.getIndex()];
        this.atkType = at;
        this.aoe = aoe;
        this.burn = burn;
        for(int i = 0; i<multiplierCount; i++) multipliers[i] = 1.0;
        energy[E_COST] = energyCost;
        energy[E_REQ] = energyCost;
        energy[E_REG] = energyRegeneration;
        this.multipliers = multipliers;
        multiplierConstruct = multiplierConstructAlgo;
        this.ofUnit = ofUnit;
    }
    public Attack(AttackType at, int energyCost, int energyRegeneration, int energyRequirement, int multiplierCount, double []multipliers, MultiplierConstruct multiplierConstructAlgo, AOE aoe, boolean burn, Unit ofUnit){
        this(at, energyCost, energyRegeneration, multiplierCount, multipliers,  multiplierConstructAlgo, aoe, burn, ofUnit);
        energy[E_REQ] = energyRequirement;
        this.multipliers = multipliers;
    }

    public static double[] makeMulti(double... multipliers){
        double[]ms = new double[multipliers.length];
        for(int i = 0; i<multipliers.length; i++){
            ms[i] = multipliers[i];
        }
        return ms;
    }

    public double getMultiplier(){
        return multiplierConstruct.calculateMultiplier(multipliers, ofUnit);
    }

    public int getEnergyRequirement(){
        return energy[E_REQ];
    }

    public int getEnergyCost(int curEnergy){
        return curEnergy;
    }
    public int getEnergyCost(){
        return energy[E_COST];
    }
    public int getEnergyRegeneration(){
        return energy[E_REG];
    }

    public AttackType getAtkType() {
        return atkType;
    }

    public void setAtkType(AttackType atkType) {
        this.atkType = atkType;
    }

    public double[] getMultipliers() {
        return multipliers;
    }

    public void setMultipliers(double[] multipliers) {
        this.multipliers = multipliers;
    }

    public int[] getEnergy() {
        return energy;
    }

    public AOE getAoe() {
        return aoe;
    }

    public boolean isBurn() {
        return burn;
    }

    public String getAttackName() {
        return attackName;
    }

    public void setAttackName(String attackName) {
        this.attackName = attackName;
    }
}
