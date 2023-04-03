public class Boss extends Character{
    private int probability;
    private String damageIncrease;
    public Boss(String name,int health,int armourClass,int damage) {
        super(name, health, "Boss",damage,armourClass);
        setProbability();
        setAbility();
    }
    public void setAbility(){
        damageIncrease = "Damage increase";
    }
    public String getAbility(){
        return damageIncrease;
    }
    public void setProbability(){
        this.probability = (super.getHealth()/super.getArmourClass())-5;
    }
    public int getProbability(){
        return probability;
    }
    public void activateAbility(){
        System.out.println("Boss damage has increased\n"+"Boss damage is:"+super.getDamage());
        super.increaseDamage((int) ((super.getMaxDamage()/2)*0.2));
    }
    public void deactivateAbility(){
        super.increaseDamage(-(int) ((super.getMaxDamage()/2)*0.2));
    }
}