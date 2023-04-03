public class Soldier extends Character{
    private int probability;
    private String guaranteeHit;
    public Soldier(String name) {
        super(name,50,"Soldier",15,7);
        setProbability();
        setAbility();
    }
    public void setAbility(){
        guaranteeHit = "Guarantee hit";
    }
    public String getAbility(){
        return guaranteeHit;
    }
    public void setProbability(){
        this.probability = 10;
    }
    public void activateAbility() {
        super.activateAbilityBool();
        System.out.println("Player has been guaranteed a hit on the boss");
        super.setGuaranteeHit(true);
    }
    public void removeAbilities(){
        super.setGuaranteeHit(false);
        super.deactivateAbility();
    }
    public int getProbability(){return probability;}
}
