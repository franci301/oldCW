public class Weapons{
    private String name;
    private int damage;
    private boolean equipped = false;

    public Weapons(String name,int damage) {
        this.name = name;
        this.damage = damage;
    }
    public String getName(){
        return name;
    }
    public int getDamage(){return damage;}
    public void setEquipped(boolean eq){
        this.equipped = eq;
    }
    public boolean getEquipped(){
        return equipped;
    }
}