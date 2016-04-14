import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PlayerIslandManager {
	private static String PLAYER_ISLAND_FILE = "playerIslandData.txt";
	private static String FOLDER = "Player";
	private static int VERT_MOVE_SPEED = 3;
	
	private Island playerIsland;
	
	public PlayerIslandManager() {
		playerIsland = new Island(300, 300, ImagePaths.ISLAND_BODY_1);
	}
	
	public Island getIsland() {return playerIsland;}
	public void setIsland(Island i) {playerIsland = i;}
	
	public void Update(Graphics g, boolean moveIsland, boolean moveUp) {
		if (Board.SCENE == 0) {
			
		} else if (Board.SCENE == 1) {
			Island.LARGE = false;
			if (moveIsland && moveUp)
				movePlayerIslandUp();
			else if (moveIsland && !moveUp)
				movePlayerIslandDown();
			playerIsland.drawIsland(g);
		} else if (Board.SCENE == 2) {
			Island.LARGE = true;
			playerIsland.drawIsland(g);
		}
	}
	
	public void saveIsland() {
		File folder = new File(FOLDER);
		folder.mkdir();
		
		File f = new File(FOLDER + "/" + PLAYER_ISLAND_FILE);
		// ***BELOW MAYBE REDUNDANT***
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
	
	public void movePlayerIslandUp() {
		playerIsland.moveVert(VERT_MOVE_SPEED);
	}
	
	public void movePlayerIslandDown() {
		playerIsland.moveVert(-VERT_MOVE_SPEED);
	}

	@Override
	public String toString() {
		return playerIsland.toString();
	}
}
