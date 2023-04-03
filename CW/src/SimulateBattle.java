import java.util.Random;

public class SimulateBattle {

    public boolean getWinner(Character a, Boss b) {
        return a.getHealth() > 0 && b.getHealth() <= 0;
    }
    public boolean checkCharacterDead(Character a){
        return a.getHealth() <= 0;
    }
    public boolean checkBossDead(Boss b){
        return b.getHealth() <= 0;
    }
    public boolean bossAbilityHit(Boss boss){
        int bossProb = boss.getProbability();
        Random random = new Random();
        return random.nextInt(bossProb * 2) < 5;
    }
    public void gameExit(){
        System.exit(0);
    }
    public boolean probabilityToHitCharacter(Character a,Boss b) {
        int highestRoll = 0;
        int dieRoll = 0;
        Random random = new Random();
        //boss attacking script - generate random number based off of armour class
        for(int i=0;i<3;i++){
            int probability = b.getProbability();
            dieRoll = random.nextInt(probability);
            if(dieRoll>highestRoll) highestRoll = dieRoll;
        }
        return highestRoll > a.getArmourClass();
    }
    public boolean probabilityToHitBoss(Character a,Boss b){
        int highestRoll = 0;
        int dieRoll = 0;
        Random random = new Random();
        //player attacking script - generate random number based off of armour class
        for(int i=0;i<3;i++){
            int probability = a.getProbability();
            dieRoll = random.nextInt(probability);
            if(dieRoll> highestRoll) highestRoll = dieRoll;
        }
        return highestRoll >= b.getArmourClass();
    }
    public boolean checkDead(Character a, Boss b){
        int playerHealth = a.getHealth();
        int bossHealth = b.getHealth();
        if(playerHealth <=0|| bossHealth<=0){
            return true;
        }
       return false;
    }
    public void simulateBattle(Character a, Boss boss, Items I, int iteration){
        GUI.addTextMain("");
        GUI.addTextMain("[Details] to view character statistics");
        GUI.addTextMain("Do you wish to [roll], [manage] an item in your inventory or activate your ability [ability]");
        GUI.addTextMain("Note: select manage to equip weapons");
        GUI.waitingForInput();
        String answer = GUI.getInput();
        if(answer.equals("roll")) rollSimulation(a,boss);
        else if(answer.equals("manage")) manageSimulation(a);
        else if(answer.equals("ability")){
            a.checkActivateAbility(iteration);
        }
        else if(answer.equals("Details") || answer.equals("details")){
            GUI.printCharacterDetails(a);
        }
        GUI.addTextMain(a.getName()+" health:" +a.getHealth());
        GUI.addTextMain("Boss health:" +boss.getHealth());
        if(!checkCharacterDead(a)){
            if(getWinner(a,boss)){
                GUI.addTextMain(a.getName()+" is the winner");
                GUI.addTextMain("");
                GUI.addTextMain("Select which items to pickup:");
                a.removeAbilities();
                int numberDropped = I.bossDrops();
                for(int i=0;i<numberDropped;i++){
                    GUI.addTextMain("Enter number to pickup or enter 0 to skip");
                    I.displayItemsDropped(I.getItemsDropped());
                    GUI.waitingForInput();
                    int itemCollect = Integer.parseInt(GUI.getInput());
                    if(itemCollect != 0){
                        a.placeItemInInventory(itemCollect-1,null,I);
                    }
                }
                if(a.getAbilityBool()){
                    a.removeAbilities();
                }
            }
        }
        if(!checkBossDead(boss)){
            if(getWinner(a,boss)){
                GUI.addTextMain(boss.getName()+" is the winner");
            }
        }
    }
    public void rollSimulation(Character a, Boss boss){
        if(!checkDead(a,boss)) {
            //Player's turn
            if(a.getGuaranteeHit()){
                a.setGuaranteeHit(false);
                GUI.addTextMain("You have been guaranteed a hit");
                boss.takeDamage(a.getDamage());
                GUI.addTextMain(a.getName()+ " has hit "+boss.getName()+ " for:"+a.getDamage());
            }else{
                boolean hitBoss = probabilityToHitBoss(a, boss);
                if (hitBoss) {
                    boss.takeDamage(a.getDamage());
                    GUI.addTextMain(a.getName() + " has hit " + boss.getName() + " for:" + a.getDamage());
                }
                else{
                    GUI.addTextMain("You have missed!");
                }
            }
        }
        if(!checkDead(a,boss)){
            //Boss's turn
            boolean hitChar = probabilityToHitCharacter(a,boss);
            boolean abilityHit = bossAbilityHit(boss);
            if(hitChar){
                if(abilityHit){
                    boss.activateAbility();
                }
                a.takeDamage(boss.getDamage());
                GUI.addTextMain(boss.getName()+" has hit "+a.getName()+ " for:"+boss.getDamage());
            }
            else{
                GUI.addTextMain("Boss has missed!");
            }
        }
    }
    public void manageSimulation(Character a){
        GUI.addTextMain("Do you wish to view Item details? (Y/N)");
        GUI.waitingForInput();
        String view = GUI.getInput();
        a.displayPlayerInventory(view.equals("Y"));
        GUI.waitingForInput();
        int selectItem = Integer.parseInt(GUI.getInput())-1;
        for(int i =0;i<a.getInventory().size();i++){
            if(a.getInventory().get(selectItem) instanceof Weapons){
                Weapons selectedWeapon = (Weapons) a.getInventory().get(selectItem);
                selectedWeapon.setEquipped(true);
                GUI.displayInventoryGUI(a.getInventory());
                if(!a.equipped()){
                    a.addToEquippedItems(selectedWeapon);
                    a.increaseDamage(selectedWeapon.getDamage());
                }
                else{
                    GUI.addTextMain("You already have an item equipped- do you wish to replace the item? (Y/N)");
                    String replace = GUI.getInput();
                    if(replace.equals("Y")){
                        a.increaseDamage(-(a.getEquippedWeapon().getDamage()));
                        a.addToEquippedItems(selectedWeapon);
                        a.increaseDamage(selectedWeapon.getDamage());
                    }
                }
                GUI.addTextMain("Player damage is:"+a.getDamage());
                break;
            }
            boolean apply;
            if(a.getInventory().get(selectItem) instanceof Consumables){
                Consumables selectedConsumable = (Consumables) a.getInventory().get(selectItem);
                if(selectedConsumable.getEffect().equals("Damage increase of 4")){
                    apply = a.applyEffect(selectedConsumable,4);
                }else{
                    apply = a.applyEffect(selectedConsumable,0);
                }
                if(apply){
                    a.removeFromInventory(selectItem);
                    GUI.displayInventoryGUI(a.getInventory());
                }
                break;
            }
        }
    }
}