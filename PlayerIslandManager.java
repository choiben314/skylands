import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PlayerIslandManager {
	private static String PLAYER_ISLAND_FILE = "playerIslandData.txt";
	private static String FOLDER = "Player";
	private Island playerIsland;
	
	public PlayerIslandManager() {
		playerIsland = new Island(30, 30, "Missile.png");
	}
	
	public Island getIsland() {return playerIsland;}
	public void setIsland(Island i) {playerIsland = i;}
	
	public void saveIsland() {
		File folder = new File(FOLDER);
		folder.mkdir();
		
		File f = new File(FOLDER + "/" + PLAYER_ISLAND_FILE);
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				System.out.println("Failed to create new player save file.");
			}
		} 
		try {
			FileOutputStream fout = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(playerIsland);
			oos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Failed to write player island to file.");
		}
	}
	
	public void loadIsland() {
		try {
			FileInputStream fin = new FileInputStream(FOLDER + "/" + PLAYER_ISLAND_FILE);
			ObjectInputStream ois = new ObjectInputStream(fin);
			playerIsland = (Island)ois.readObject();
			ois.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Something went wrong.");
		}
	}

	@Override
	public String toString() {
		return playerIsland.toString();
	}
}
