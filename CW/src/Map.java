


import java.util.ArrayList;
import java.util.Random;

public class Map {
    private ArrayList<String> BossNames = new ArrayList<>();
    private ArrayList<Room> roomArray  = new ArrayList<>();
    private Character a;
    private ArrayList itemsArr,roomArrayList = new ArrayList();
    private Items I = new Items();
    private int difficulty;

    public Map(Character a) {
        this.a = a;
        this.itemsArr = I.getItemsArr();
        BossNames.add("Alfred");
        BossNames.add("Ursula");
        BossNames.add("Dominic");
        BossNames.add("Marvin");
        BossNames.add("Hector");
        BossNames.add("Thanos");
        BossNames.add("Rob");
        BossNames.add("Lucifer");

    }
    // making a "map" based off of the difficulty
    public int setDifficulty(){
        if(a.getType().equals("Mage")){
            difficulty = 2;
        }
        else if(a.getType().equals("Soldier")){
            difficulty = 3;
        }
        else if(a.getType().equals("Tank")){
            difficulty = 4;
        }
        generateRooms(difficulty);
        return difficulty;
    }

    public void generateRooms(int difficulty){
        System.out.println("Generating rooms");
        for(int i =0;i<difficulty*2;i++){
            int numberItemsForRoom = getRandomNumberObjectsInRoom();
            loopThroughMaxItems(numberItemsForRoom,i);
        }
}
    public void loopThroughMaxItems(int numberItemsForRoom,int roomIndex){
        Boss b;
        for(int i=0;i<numberItemsForRoom;i++){
            Random random = new Random();
            int arrayLen = itemsArr.size();
            if(arrayLen != 0){
                int ranNum = random.nextInt(arrayLen);
                if(itemsArr.get(ranNum) instanceof Consumables){
                    ArrayList<Consumables> consumablesArr = I.getItemsArr();
                    roomArrayList.add(consumablesArr.get(ranNum));
                    itemsArr.remove(ranNum);
                }
                else if(itemsArr.get(ranNum) instanceof  Weapons){
                    ArrayList<Weapons> weaponsArr = I.getItemsArr();
                    roomArrayList.add(weaponsArr.get(ranNum));
                    itemsArr.remove(ranNum);
                }
            }
        }
        if(roomIndex==0){
            b = null;
        }else{
            b = makeBoss(roomIndex);
        }
        Room room = new Room(roomArrayList,I,roomIndex+1,b);
        roomArray.add(roomIndex,room);
        roomArrayList = new ArrayList();
    }

    public ArrayList<Room> getRoomArray(){
        return roomArray;
    }
    public int getRandomNumberObjectsInRoom(){
        Random random = new Random();
        int randomNumber = random.nextInt();
        if(randomNumber == 0){
            return 0;
        }
        else{
            return 1;
        }
    }

    public Boss makeBoss(int roomNumber){
        Random random = new Random();
        int ranInt = random.nextInt(BossNames.size());
        String ranName = BossNames.get(ranInt);
        BossNames.remove(ranInt);
        Boss boss = new Boss(ranName,((roomNumber+1)*a.getHealth())-50,roomNumber*2+3, (int) ((roomNumber+a.getDamage())*1.05));
        return boss;
    }
    public ArrayList getBossDrops(){
        return I.getBossDropsArrayList();
    }
}