

import java.util.ArrayList;
import java.util.Random;

public class Items {
    private ArrayList itemsArr = new ArrayList();
    private ArrayList bossDropsArrayList = new ArrayList();
    private ArrayList itemsDropped;

    public Items(){
        itemsArr.add(new Consumables("Healing potion", "Heal half of characters health"));
        itemsArr.add(new Consumables("Damage potion", "Damage increase of 4"));
        itemsArr.add(new Consumables("Damage potion", "Damage increase of 4"));
        itemsArr.add(new Consumables("Crit potion", "Guarantees a hit on the opponent"));
        itemsArr.add(new Consumables("Crit potion", "Guarantees a hit on the opponent"));
        itemsArr.add(new Weapons("Common Sword", 4));
        itemsArr.add(new Weapons("Common Bow",5));
        itemsArr.add(new Weapons("Common Axe",6));

        bossDropsArrayList.add(new Consumables("Healing potion","Heal half of characters health"));
        bossDropsArrayList.add(new Weapons("Rare Bow",7));
        bossDropsArrayList.add(new Weapons("Legendary Sword",10));
        bossDropsArrayList.add(new Weapons("Rare Sword",6));
        bossDropsArrayList.add(new Consumables("Crit potion", "Guarantees a hit on the opponent"));
        bossDropsArrayList.add( new Consumables("Damage potion", "Damage increase of 4"));
        bossDropsArrayList.add( new Consumables("Healing potion", "Heal half of characters health"));
        bossDropsArrayList.add(new Consumables("Healing potion", "Heal half of characters health"));
        bossDropsArrayList.add(new Weapons("Rare Axe",8));
    }
    public ArrayList getItemsArr(){
        return itemsArr;
    }
    public ArrayList getBossDropsArrayList(){
        return bossDropsArrayList;
    }
    public ArrayList getItemsDropped(){return itemsDropped;}
    public int bossDrops(){
        int numberDrops = getRanNumberDrops();
        itemsDropped = new ArrayList();
        for(int i =0;i<numberDrops;i++){
            int ranNumber = getRanNumber(bossDropsArrayList.size());
            itemsDropped.add(bossDropsArrayList.get(ranNumber));
        }
        return numberDrops;
    }
    public void displayItemsDropped(ArrayList bossDrops){
        for(int i=0;i<bossDrops.size();i++){
            if(bossDrops.get(i) instanceof Weapons){
                Weapons weaponDropped = (Weapons) bossDrops.get(i);
                GUI.addTextMain(i+1+"."+weaponDropped.getName()+" deals: "+weaponDropped.getDamage()+" damage");
            }
            if(bossDrops.get(i) instanceof Consumables){
                Consumables consumablesDropped = (Consumables) bossDrops.get(i);
                GUI.addTextMain(i+1+"."+consumablesDropped.getName()+" effect: "+consumablesDropped.getEffect());
            }
        }
    }
    public int getRanNumberDrops(){
        int ranNum = getRanNumber(2);
        if(ranNum == 0){
            return 1;
        }
        return 2;
    }
    public int getRanNumber(int bound){
        Random random = new Random();
        return random.nextInt(bound);
    }
}