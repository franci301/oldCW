public class Tank extends Character{
    private int probability;
    private String damageIncrease;
    public Tank(String name) {
        super(name,70,"Tank",20,15);
        setProbability();
        setAbility();
    }
    public void setAbility(){
        damageIncrease = "Damage increase of 8";
    }
    public String getAbility(){
        return damageIncrease;
    }
    public void setProbability(){
        this.probability = 12;
    }
    public void activateAbility() {
        super.activateAbilityBool();
        System.out.println("Damage has increased");
        super.increaseDamage(8);
    }
    public void deactivateAbility(){
        super.increaseDamage(-8);
        super.deactivateAbility();
    }
    public int getProbability(){return probability;}
}