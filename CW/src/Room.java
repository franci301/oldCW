import java.util.ArrayList;
import java.util.Optional;

public class Room {
    private ArrayList itemsArr;
    private int roomNumber;
    private Boss boss;
    private Items I;
    public Room(ArrayList itemsArr,Items I,int roomNumber,Boss boss){
        this.itemsArr = itemsArr;
        this.roomNumber = roomNumber;
        this.boss = boss;
        this.I = I;
    }

    public void displayItemsInRoom(Room currentRoom){
        GUI.addTextMain("In the room there is:");
        ArrayList currentRoomArr = currentRoom.getItemsArr();
        for(int i =0;i<currentRoomArr.size();i++){
            if(currentRoomArr.get(i) instanceof Weapons){
                ArrayList<Weapons> weaponsInRoom = currentRoom.getItemsArr();
                GUI.addTextMain(i+1 +"."+ weaponsInRoom.get(i).getName());
            }
            else if(currentRoomArr.get(i) instanceof Consumables){
                ArrayList<Consumables> consumablesInRoom = currentRoom.getItemsArr();
                GUI.addTextMain(i+1+"."+consumablesInRoom.get(i).getName());
            }
        }
    }
    public ArrayList getItemsArr(){return itemsArr;}
    public Boss getBoss(){return boss;}
    public void removeItemFromRoom(int itemIdexToRemove){
        itemsArr.remove(itemIdexToRemove);
    }
    public Items getItems(){
        return I;
    }

}