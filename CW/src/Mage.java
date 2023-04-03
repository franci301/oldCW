public class Mage extends Character{
    private int probability;
    private String Heal;
    public Mage(String name) {
        super(name,40,"Mage",10,10);
        setProbability();
        setAbility();
    }
    public void setAbility(){
        Heal = "Heal";
    }
    public String getAbility(){
        return Heal;
    }
    public void setProbability(){
        this.probability = 9;
    }
    public int getProbability(){
        return probability;
    }
    public void addHealth(int health){
        super.setHealth(super.getHealth()+5);
    }
    public void activateAbility(){
        super.activateAbilityBool();
        System.out.println("Player health has increased");
        addHealth((int) ((super.getMaxHealth()/2)*0.5));
    }
}