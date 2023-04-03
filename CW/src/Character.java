import java.lang.reflect.Array;
import java.util.ArrayList;

abstract public class Character{
    private int maxInventorySize,health;
    private String name;
    private int maxHealth,damage,maxDamage,damageBuff,armourClass;
    private String type;
    private ArrayList inventory;
    private Weapons[] equippedArr = new Weapons[1];
    private boolean guaranteeHit,ability= false;

    public Character(String name,int health,String type,int damage,int armourClass){
        this.name = name;
        this.type = type;
        this.health = health;
        this.maxHealth = health;
        this.damage = damage;
        this.maxDamage = damage;
        this.armourClass = armourClass;
        this.inventory = new ArrayList();
        this.maxInventorySize = 5;
    }
    public void activateAbility(){
        GUI.addTextAbility("Ability has been activated",false);
    }
    public void setHealth(int health){
        this.health = health;
    }
    public int getProbability(){
        return 0;
    }
    public int getHealth(){
        return health;
    }
    public int getMaxDamage(){return maxDamage;}
    public String getName(){
        return name;
    }
    public String getType(){
        return type;
    }
    public String getAbility(){return "";}
    public int getDamage(){
        return damage;
    }
    public int getArmourClass(){
        return armourClass;
    }
    public ArrayList getInventory(){
        return inventory;
    }
    public void setInventory(ArrayList inventory){
        this.inventory = inventory;
    }
    public int getMaxInventorySize(){
        return maxInventorySize;
    }
    public Weapons getEquippedWeapon(){
        return equippedArr[0];
    }
    public void takeDamage(int damage){
        health -= damage;
    }
    public void setProbability(){GUI.addTextMain("Probability set");}
    public void removeAbilities(){GUI.addTextMain("Ability removed");}
    public void resetHealth(){this.health = maxHealth;}
    public void increaseDamage(int damage){
        this.damage = damage+getDamage();
        GUI.addTextMain("Damage is now:"+getDamage());
    }
    public void addHealth(int health){this.health += health;}
    public int getMaxHealth(){
        return maxHealth;
    }
    public boolean checkPlayerInventoryFull(){
        return inventory.size() >= getMaxInventorySize();
    }
    public boolean checkPlayerDead(Character a){
        return a.getHealth() <= 0;
    }
    public void displayPlayerInventory(boolean YN){
        for(int i =0;i<getInventory().size();i++){
            if(!inventory.isEmpty() && YN){
                if(inventory.get(i) instanceof Consumables){

                    ArrayList<Consumables> consumablesArrayList = inventory;
                    GUI.addTextMain(i+1+"."+consumablesArrayList.get(i).getName()+" effect:"+consumablesArrayList.get(i).getEffect());
                }
                else{

                    ArrayList<Weapons> weaponsArr = inventory;
                    if(weaponsArr.get(i) == equippedArr[0]){
                        GUI.addTextMain((i+1)+"."+weaponsArr.get(i).getName()+"(Equipped) damage:"+weaponsArr.get(i).getDamage());
                    }else{
                        GUI.addTextMain((i+1)+"."+weaponsArr.get(i).getName()+" damage:"+weaponsArr.get(i).getDamage());
                    }
                }
            }else if(!inventory.isEmpty()){
                if(inventory.get(i) instanceof Consumables){
                    System.out.println("Doesnt like this");
                    ArrayList<Consumables> consumablesArrayList = inventory;
                    GUI.addTextMain(i+1+"."+consumablesArrayList.get(i).getName());
                }
                else{
                    System.out.println("Hates this");
                    ArrayList<Weapons> weaponsArr = inventory;
                    if(weaponsArr.get(i) == equippedArr[0]){
                        GUI.addTextMain((i+1)+"."+weaponsArr.get(i).getName()+"(Equipped)");
                    }else{
                        GUI.addTextMain((i+1)+"."+weaponsArr.get(i).getName());
                    }
                }
            }
        }
    }
    public void placeItemInInventory(int itemIndexToPlace,Room a,Items I){
        boolean Full = checkPlayerInventoryFull();
        if(!Full && a != null){
            for(int i=0;i<getMaxInventorySize();i++) {
                ArrayList itemsInRoom = a.getItemsArr();
                inventory.add(itemsInRoom.get(itemIndexToPlace));
                GUI.displayInventoryGUI(getInventory());
                i = getMaxInventorySize();
            }
            a.removeItemFromRoom(itemIndexToPlace);
        }else if(!Full && a== null){
            for(int i=0;i<getMaxInventorySize();i++){
                inventory.add(I.getItemsDropped().get(itemIndexToPlace));
                GUI.displayInventoryGUI(getInventory());
                i = getMaxInventorySize();
            }
            I.getBossDropsArrayList().remove(itemIndexToPlace);
            I.getItemsDropped().remove(itemIndexToPlace);
        }else{
            GUI.addTextMain("Your inventory is full, you can no longer pickup items");
            GUI.addTextMain("Do you wish to remove an item from your inventory? (Y/N)");
            String remove = GUI.getInput();
            if(remove.equals("Y")){
                GUI.addTextMain("Select the item you wish to remove");
                displayPlayerInventory(false);
                int itemToRemove = Integer.parseInt(GUI.getInput());
                removeFromInventory(itemToRemove);
                GUI.addTextMain("Item has been removed from your inventory");
            }
        }
    }
    public void addToEquippedItems(Weapons equippedWeapon){
        equippedArr[0] = equippedWeapon;
    }
    public boolean equipped(){
        return equippedArr[0] != null;
    }
    public boolean getGuaranteeHit(){
        return guaranteeHit;
    }
    public void setGuaranteeHit(boolean guarantee){
        guaranteeHit = guarantee;
    }
    public void removeFromInventory(int index){
        inventory.remove(index);
    }
    public boolean applyEffect(Consumables item,int damageApply){
        if(item.getEffect().equals("Heal half of characters health")){
            if(getHealth()!=getMaxHealth()){
                addHealth(getMaxHealth()/2);
                return true;
            }else{
                GUI.addTextMain("You cannot heal at full health");
            }
        }else if(item.getEffect().equals("Guarantees a hit on the opponent")){
            setGuaranteeHit(true);
            return true;
        }else if(item.getEffect().equals("Damage increase of 4")){
            this.damageBuff = damageApply;
            increaseDamage(4);
            return true;
        }
        return false;
    }
    public void checkActivateAbility(int iteration){
        if (!getAbilityBool()){
            activateAbility();
            GUI.addTextAbility("Active",false);
        }
        else GUI.addTextMain("You cannot activate this ability more than once per 5 rounds");
        if (iteration > 5){
            deactivateAbility();
            GUI.addTextAbility("Deactivated",true);
        }
        iteration = 0;
    }
    public void activateAbilityBool(){
        ability = true;
    }
    public void deactivateAbility(){
        ability = false;
        GUI.addTextAbility("Ability has been deactivated",false);
    }
    public boolean getAbilityBool(){
        return ability;
    }
}