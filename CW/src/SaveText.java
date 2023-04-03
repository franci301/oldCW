import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;   
import java.util.ArrayList;

public class SaveText {
    public static void SaveToText(ArrayList inventory,Character a, int roomNumber) throws IOException {
        PrintWriter outputStream = new PrintWriter(new FileWriter("GameSave.txt"));
        ArrayList inventoryArr = new ArrayList();
        for(int i=0;i<inventory.size();i++){
            if(inventory.get(i) instanceof Weapons){
                ArrayList<Weapons> weaponsArr = inventory;
                inventoryArr.add(weaponsArr.get(i).getName());
            }else{
                ArrayList<Consumables> consumablesArr = inventory;
                inventoryArr.add(consumablesArr.get(i).getName());
            }
        }
        outputStream.println(a.getName());
        outputStream.println(a.getType());
        outputStream.println(a.getHealth());
        outputStream.println(a.getMaxHealth());
        outputStream.println(a.getDamage());
        outputStream.println(a.getArmourClass());
        outputStream.println(a.getMaxInventorySize());
        outputStream.println(a.getProbability());
        outputStream.println(a.getAbility());
        outputStream.println(Integer.toString(roomNumber));
        outputStream.println(inventoryArr);
        outputStream.close();
        GUI.addTextMain("File has been created");
        GUI.addTextMain("Exiting...");
        System.exit(0);
    }
    public static ArrayList loadTextFile() throws IOException{
        ArrayList detailsArr = new ArrayList();
        BufferedReader inStream = new BufferedReader(new FileReader("GameSave.txt"));
        String nextWord = inStream.readLine();
        while(nextWord!=null){
            detailsArr.add(nextWord);
            nextWord = inStream.readLine();
        }
        return detailsArr;
    }
}