//
//
//import java.lang.reflect.Array;
//import java.nio.file.Watchable;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class TestClasses {
//    private static ArrayList<Room> roomArray;
////    private static = new );
//    private static SimulateBattle simulation = new SimulateBattle();
//    private static String currentInput = "";
//    private static boolean start;
//
//    public TestClasses(){
//
//    }
//
////    public static void main(String[] args){
////        startGame();
////    }
//    public static String Input(){
//        Scanner scanner = new Scanner(System.in);
//        String str = scanner.nextLine();
//        return str;
//    }
//    public static int INT(){
//        Scanner scanner = new Scanner(System.in);
//        int ans = scanner.nextInt();
//        return ans;
//    }
//    public void sendToTest(String str,boolean b){
//        this.currentInput = str;
//        start = b;
//        System.out.println("the current input is:" + str + " the boolean value is:" + b);
//    }
//    public static void startGame(){
//        Scanner scanner = new Scanner(System.in);
//
//        addTextMain("Do you wish to start the game?");
//        addTextMain("1.Start Game");
//        addTextMain("2.Quit");
//
//        int counter = 0;
//        while(!start){
//            counter ++;
//            if(counter == 150) System.out.println("Waiting for an input");
//        }
//        System.out.println(currentInput+ " this is the current input");
//        try{
//            if(Integer.parseInt(currentInput) == 1){
//                setWaitFalse();
//                addTextMain("Choose a class for your character");
//                addTextMain("1.Mage");
//                addTextMain("2.Soldier");
//                addTextMain("3.Tank");
//                int count =0;
//                while(!waitingForInput()){
//                    count ++;
//                    if(count == 150) System.out.println("Waiting for an input");
//                }
//                addTextMain("You find yourself in a room- it is pitch black");
//                addTextMain("You fumble around looking for some source of light");
//                addTextMain("A used torch will suffice- as you light it you seem the stone walls that surround you and a mysterious door");
//
//                if(Integer.parseInt(currentInput)== 1){
//                    addTextMain("Name your character");
//                    String name = currentInput;
//                    Character mage = new Mage(name);
//                    Map gameMap = new Map(mage);
//                    printCharacterDetails(mage);
//                    gameMap.setDifficulty();
//                    callGameLoop(mage,gameMap);
//                }else if(Integer.parseInt(currentInput) == 2){
//                    addTextMain("Name your character");
//                    String name = currentInput;
//                    Character soldier = new Soldier(name);
//                    Map gameMap = new Map(soldier);
//                    printCharacterDetails(soldier);
//                    gameMap.setDifficulty();
//                    callGameLoop(soldier,gameMap);
//                }
//                else if(Integer.parseInt(currentInput) == 3){
//                    addTextMain("Name your character");
//                    String name = currentInput;
//                    Character tank = new Tank(name);
//                    Map gameMap = new Map(tank);
//                    printCharacterDetails(tank);
//                    gameMap.setDifficulty();
//                    callGameLoop(tank,gameMap);
//                }
//            }
//        } catch(Exception e){
//            System.out.println(e);
//        }
//    }
//
//    private static void callGameLoop(Character a, Map gameMap) {
//        roomArray = gameMap.getRoomArray();
//            for(int i=0;i<roomArray.size();i++){
//                GameLoop(a,roomArray,i);
//            }
//    }
//
//    public static void printCharacterDetails(Character a){
//        addTextMain("");
//        addTextMain(a.getName()+ " is a " +a.getType()+" and has the following properties:");
//        addTextMain("Inventory size of:"+ a.getMaxInventorySize() + " health of: " + a.getHealth()+ " probability to hit:"+ a.getProbability()+ " an armour class of:"+a.getArmourClass()+" and damage:"+a.getDamage());
//        addTextMain(a.getName()+" has an ability of:"+a.getAbility());
//    }
//
//    public static void printBossDetails(Boss b){
//        addTextMain("");
//        addTextMain("You have encountered "+ b.getName()+" they are a "+b.getType()+", here are their details:");
//        addTextMain("He has a health of:" + b.getHealth() + " a probability to hit of:"+ b.getProbability() +" an armour class of:"+b.getArmourClass()+" and does "+ b.getDamage() + " damage");
//        addTextMain(b.getName()+" has an ability of:"+b.getAbility());
//    }
//    public static void displayOptions(ArrayList<Room> roomArray,int iteration,Character a ){
//        if(!roomArray.get(iteration).getItemsArr().isEmpty()){
//            roomArray.get(iteration).displayItemsInRoom(roomArray.get(iteration));
//            addTextMain("Which item(s) do you wish to pickup?\n0 to skip");
//            if(Integer.parseInt(currentInput) !=0){
//                a.placeItemInInventory(Integer.parseInt(currentInput)-1,roomArray.get(iteration),roomArray.get(iteration).getItems());
//                addTextMain("Your inventory now contains:");
//                a.displayPlayerInventory(false);
//            }
//        }
//        else addTextMain("This room is now empty");
//        addTextMain("Do you wish to proceed to the next room? (Y/N)");
//        String proceed = Input();
//        if(proceed.equals("N")){
//            displayOptions(roomArray,iteration,a);
//        }
//    }
//    public static void GameLoop(Character a, ArrayList<Room> roomArray, int iteration){
//        boolean playerDead = a.checkPlayerDead(a);
//        int i =0;
//        if(!playerDead){
//            addTextMain("Your inventory contains:");
//            a.displayPlayerInventory(false);
//            displayOptions(roomArray,iteration,a);
//            if(iteration>0){
//                addTextMain("A boss has appeared-You must fight them if you wish to continue to the next room");
//                addTextMain("");
//                printBossDetails(roomArray.get(iteration).getBoss());
//                while(a.getHealth()>0 && roomArray.get(iteration).getBoss().getHealth()>0){
//                    i ++;
//                    simulation.simulateBattle(a,roomArray.get(iteration).getBoss(),roomArray.get(iteration).getItems(),i);
//                }
//                if(a.getHealth()<=0){simulation.endGame();}
//                a.resetHealth();
//                i=0;
//            }
//        }
//    }
//}