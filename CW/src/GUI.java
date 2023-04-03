import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class GUI extends Frame {
    private static ArrayList<Room> roomArray;
    private static SimulateBattle simulation = new SimulateBattle();
    private static String currentInput = "";
    private static Character character;
    private static int ITERATION= 0;
    private static ArrayList gameLoadArrayList;


    private static Label inventoryLabel, abilitiesLabel;
    private static TextArea inventoryArea,abilitiesArea, mainArea = new TextArea();
    private static TextField inputField;
    private static Frame frame;
    private static Button exitButton, saveButton;
    private static ArrayList<String> keysTypedArr = new ArrayList<String>();
    private static boolean inputGot = false;

    public static  void main(String[] args){
        GUI gui = new GUI();
        startGame();
    }
    public GUI(){

        frame = new Frame();
        frame.setVisible(true);
        frame.setSize(800,600);
        frame.setLayout(null);

        inputField = new TextField("Input Here:");
        inputField.setBounds(10, 555, 500, 35);
        inputField.setVisible(true);

        mainArea.setBounds(20,50,485,480);
        mainArea.setVisible(true);
        mainArea.setEditable(false);

        inventoryArea = new TextArea();
        inventoryArea.setBounds(530, 100, 250, 150);
        inventoryArea.setVisible(true);
        inventoryArea.setEditable(false);

        abilitiesArea = new TextArea();
        abilitiesArea.setBounds(530, 300, 250, 100);
        abilitiesArea.setVisible(true);
        abilitiesArea.setEditable(false);

        exitButton = new Button("Exit");
        exitButton.setBounds(580, 555, 200, 35);
        exitButton.setVisible(true);

        saveButton = new Button("Exit and save");
        saveButton.setBounds(580, 505, 200, 35);
        saveButton.setVisible(true);

        inventoryLabel = new Label("Inventory:");
        inventoryLabel.setBounds(635, 50, 100, 50);
        inventoryLabel.setVisible(true);

        abilitiesLabel = new Label("Abilities:");
        abilitiesLabel.setBounds(635, 250, 100, 50);
        abilitiesLabel.setVisible(true);

        frame.add(inventoryArea);
        frame.add(abilitiesArea);
        frame.add(exitButton);
        frame.add(inventoryLabel);
        frame.add(abilitiesLabel);
        frame.add(inputField);
        frame.add(mainArea);
        frame.add(saveButton);

        inputField.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
          public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_ENTER){
                if(!inputField.getText().equals("")){
                    addTextMain(inputField.getText());
                    currentInput = inputField.getText();
                    inputGot = true;

                    inputField.setText("");
                }
                else{
                    addTextMain("Please enter something");
                }
            }
          }
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainArea.append("\nExiting...");
                System.exit(0);
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToText();
            }
        });
    }
    public static Character getCharacter(){
        return character;
    }
    public static int getITERATION(){
        return ITERATION;
    }

    public static void setCharacter(Character a){
        character = a;
    }
    public static void addTextMain(String text){
        mainArea.append("\n"+text);
    }
    public static void addTextInventory(String text){
        inventoryArea.append("\n"+text);
    }
    public static void addTextAbility(String text,boolean reset){
        if(!reset) abilitiesArea.append("\n"+text);
        else abilitiesArea.setText(null);
    }
    public static String getInput(){
        return currentInput;
    }

    public static void waitingForInput(){
        while(!inputGot){
            System.out.println();
        }
        getInput();
        inputGot = false;
    }

    public static void saveToText(){
        try {
            Character a = getCharacter();
            SaveText.SaveToText(a.getInventory(),a,getITERATION());
        } catch (IOException ioException) {
            addTextMain("Printing stack trace");
            ioException.printStackTrace();
        }
    }
    public static void startGame(){
        addTextMain("Do you wish to start the game?");
        addTextMain("1.Start Game");
        addTextMain("2.Load file");
        addTextMain("9.Quit");
        waitingForInput();
        try{
            if(Integer.parseInt(currentInput) == 1){
                addTextMain("Choose a class for your character");
                addTextMain("1.Mage");
                addTextMain("2.Soldier");
                addTextMain("3.Tank");
                waitingForInput();
                String choice = getInput();
                addTextMain("You find yourself in a room- it is pitch black");
                addTextMain("You fumble around looking for some source of light");
                addTextMain("A used torch will suffice- as you light it you seem the stone walls that surround you and a mysterious door");
                addTextMain("Name your character");
                waitingForInput();
                String name = getInput();
                if(Integer.parseInt(choice)== 1){
                    Character mage = new Mage(name);
                    setCharacter(mage);
                    Map gameMap = new Map(mage);
                    printCharacterDetails(mage);
                    gameMap.setDifficulty();
                    callGameLoop(mage,gameMap,false);
                }else if(Integer.parseInt(choice) == 2){
                    Character soldier = new Soldier(name);
                    setCharacter(soldier);
                    Map gameMap = new Map(soldier);
                    printCharacterDetails(soldier);
                    gameMap.setDifficulty();
                    callGameLoop(soldier,gameMap,false);
                }
                else if(Integer.parseInt(choice) == 3){
                    Character tank = new Tank(name);
                    setCharacter(tank);
                    Map gameMap = new Map(tank);
                    printCharacterDetails(tank);
                    gameMap.setDifficulty();
                    callGameLoop(tank,gameMap,false);
                }
            }
            else if(Integer.parseInt(currentInput) == 2){
                gameLoadArrayList = SaveText.loadTextFile();
                if(gameLoadArrayList.get(1).equals("Mage")){
                    Character mage = new Mage((String)gameLoadArrayList.get(0));
                    setCharacter(mage);
                    mage.setHealth(Integer.parseInt((String)gameLoadArrayList.get(2)));
                    GUI.addTextMain("Character creation has worked");
                    Map gameMap = new Map(mage);
                    gameMap.setDifficulty();
                    callGameLoop(mage,gameMap,true);
                }
                else if(gameLoadArrayList.get(1).equals("Soldier")){
                    Character soldier = new Soldier((String)gameLoadArrayList.get(0));
                    setCharacter(soldier);
                    soldier.setHealth(Integer.parseInt((String)gameLoadArrayList.get(2)));
                    GUI.addTextMain("Character creation has worked");
                    Map gameMap = new Map(soldier);
                    gameMap.setDifficulty();
                    GUI.addTextMain("This has been called");
                    callGameLoop(soldier,gameMap,true);
                }
                else if(gameLoadArrayList.get(1).equals("Tank")){
                    Character Tank = new Tank((String)gameLoadArrayList.get(0));
                    setCharacter(Tank);
                    Tank.setHealth(Integer.parseInt((String)gameLoadArrayList.get(2)));
                    GUI.addTextMain("Character creation has worked");
                    Map gameMap = new Map(Tank);
                    gameMap.setDifficulty();
                    System.out.println("here");
                    callGameLoop(Tank,gameMap,true);
                }
            }
            else{
                System.exit(0);
            }
        } catch(Exception e){
            GUI.addTextMain("Enter a valid input");
            startGame();
            System.out.println(e);
        }
    }

    private static void callGameLoop(Character a, Map gameMap,boolean savedFile) {
        int index = 0;
        roomArray = gameMap.getRoomArray();
        if(savedFile){
            ArrayList loadedArr = new ArrayList();
            index = Integer.parseInt((String) gameLoadArrayList.get(9));
            String inventoryLoaded = (String) gameLoadArrayList.get(10);
            boolean skip = false;
            String item = "";
            for(int x=0;x<inventoryLoaded.length();x++){
                if(inventoryLoaded.charAt(x) != ('[') && inventoryLoaded.charAt(x) != ',' && inventoryLoaded.charAt(x) != ']'){
                    if(skip){
                        x+=1;
                        skip = false;
                    }
                    item += inventoryLoaded.charAt(x);
                }
                if(inventoryLoaded.charAt(x) == ','|| inventoryLoaded.charAt(x) == ']'){
                    skip = true;
                    GUI.addTextMain(item);
                    if(item.equals("Common Axe")){
                        loadedArr.add(new Weapons("Common Axe",6));
                    }else if(item.equals("Common Bow")){
                        loadedArr.add(new Weapons("Common Bow",5));
                    }else if(item.equals("Common Sword")){
                        loadedArr.add(new Weapons("Common Sword", 4));
                    }else if(item.equals("Rare Bow")){
                        loadedArr.add(new Weapons("Rare Bow", 4));
                    }else if(item.equals("Legendary Sword")){
                        loadedArr.add(new Weapons("Legendary Sword", 4));
                    }else if(item.equals("Rare Sword")){
                        loadedArr.add(new Weapons("Rare Sword", 4));
                    }else if(item.equals("Rare Axe")){
                        loadedArr.add(new Weapons("Rare Axe", 4));
                    }else if(item.equals("Healing potion")){
                        loadedArr.add(new Consumables("Healing potion", "Heal half of characters health"));
                    }else if(item.equals("Damage potion")){
                        loadedArr.add(new Consumables("Damage potion", "Damage increase of 4"));
                    }else if(item.equals("Crit potion")){
                        loadedArr.add(new Consumables("Crit potion", "Guarantees a hit on the opponent"));
                    }
                    item = "";
                }
            }
            a.setInventory(loadedArr);
            displayInventoryGUI(a.getInventory());
            printCharacterDetails(a);
        }
        for(int i=0;i<roomArray.size();i++){
            ITERATION ++;
            if(savedFile)i = index-1;
            GameLoop(a,roomArray,i);
        }
    }

    public static void printCharacterDetails(Character a){
        addTextMain("");
        addTextMain(a.getName()+ " is a " +a.getType()+" and has the following properties:");
        addTextMain("Inventory size of:"+ a.getMaxInventorySize() + " health of: " + a.getHealth()+ " probability to hit:"+ a.getProbability()+ " an armour class of:"+a.getArmourClass()+" and damage:"+a.getDamage());
        addTextMain(a.getName()+" has an ability of:"+a.getAbility());
        addTextAbility(a.getAbility(),false);
    }

    public static void printBossDetails(Boss b){
        addTextMain("");
        addTextMain("You have encountered "+ b.getName()+" they are a "+b.getType()+", here are their details:");
        addTextMain("He has a health of:" + b.getHealth() + " a probability to hit of:"+ b.getProbability() +" an armour class of:"+b.getArmourClass()+" and does "+ b.getDamage() + " damage");
        addTextMain(b.getName()+" has an ability of:"+b.getAbility());
    }

    public static void displayOptions(ArrayList<Room> roomArray,int iteration,Character a ){
        if(!roomArray.get(iteration).getItemsArr().isEmpty()){
            roomArray.get(iteration).displayItemsInRoom(roomArray.get(iteration));
            addTextMain("Which item(s) do you wish to pickup?\n0 to skip");
            waitingForInput();
            currentInput = getInput();
            if(Integer.parseInt(currentInput) !=0){
                a.placeItemInInventory(Integer.parseInt(currentInput)-1,roomArray.get(iteration),roomArray.get(iteration).getItems());
                addTextMain("Your inventory now contains:");
                a.displayPlayerInventory(false);
            }
        }
        else addTextMain("This room is now empty");
        addTextMain("Do you wish to proceed to the next room? (Y/N)");
        waitingForInput();
        String proceed = getInput();
        if(proceed.equals("N")){
            displayOptions(roomArray,iteration,a);
        }
    }
    public static void displayInventoryGUI(ArrayList inventory){
        inventoryArea.setText(null);
        for(int i=0;i<inventory.size();i++){
            if(inventory.get(i) instanceof Consumables){
                ArrayList<Consumables> consumablesArrayList = inventory;
                addTextInventory(consumablesArrayList.get(i).getName());
            }
            else{
                ArrayList<Weapons> weaponsArrayList = inventory;
                if(weaponsArrayList.get(i).getEquipped()){
                    addTextInventory(weaponsArrayList.get(i).getName()+ " (equipped)");
                }else addTextInventory(weaponsArrayList.get(i).getName());
            }
        }
    }
    public static void GameLoop(Character a, ArrayList<Room> roomArray, int iteration){
        boolean playerDead = a.checkPlayerDead(a);
        int i =0;
        if(!playerDead){
            addTextMain("Your inventory contains:");
            a.displayPlayerInventory(false);
            System.out.println("here");
            displayOptions(roomArray,iteration,a);
            if(iteration>0){
                addTextMain("A boss has appeared-You must fight them if you wish to continue to the next room");
                addTextMain("");
                printBossDetails(roomArray.get(iteration).getBoss());
                while(a.getHealth()>0 && roomArray.get(iteration).getBoss().getHealth()>0){
                    i ++;
                    simulation.simulateBattle(a,roomArray.get(iteration).getBoss(),roomArray.get(iteration).getItems(),i);
                }
                if(a.getHealth()<=0){simulation.gameExit();}
                a.resetHealth();
                i=0;
            }
        }
    }
}