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
	private boolean shoot;

	public PlayerIslandManager() {
		playerIsland = new Island(300, 300, ImagePaths.ISLAND_BODY_1);
		shoot = false;
	}
	
	public Island getIsland() {return playerIsland;}
	public void setIsland(Island i) {playerIsland = i;}
	
	public void Update(Graphics g, boolean moveIsland, boolean moveUp) {
		if (Board.SCENE == 0) {
			// start screen
		} else if (Board.SCENE == 1) {
			Island.LARGE = false;
			if (moveIsland && moveUp)
				movePlayerIslandUp();
			else if (moveIsland && !moveUp)
				movePlayerIslandDown();
			updateBullets(g);
			playerIsland.drawIsland(g);
		} else if (Board.SCENE == 2) {
			Island.LARGE = true;
			playerIsland.drawIsland(g);
		} else if (Board.SCENE == 2) {
			Island.LARGE = true;
			playerIsland.drawIsland(g);
		}
	}
	
	public void setShoot(boolean bool) {
		shoot = bool;
	}
	
	public void updateBullets(Graphics g) {
		if (shoot) {
			shoot = false;
			for (Building b : playerIsland.getBuildings()) {
//				int newX = playerIsland.getBody().getX() + (b.getX() / Island.SCALE_FACTOR)
//						- b.getWidth() / 2;
//				int newY = playerIsland.getBody().getY() + (b.getY() / Island.SCALE_FACTOR)
//						- b.getHeight() / 2;
				
//				int newX = b.getGlobalX(playerIsland.getBody().getX());
//				int newY = b.getGlobalY(playerIsland.getBody().getY());
				if (b instanceof Gun) {	
					int newX = playerIsland.getBody().getX() + (((Gun)(b)).getBase().getX() / Island.SCALE_FACTOR);
//							- b.getWidth() / 2;
					int newY = playerIsland.getBody().getY() + (((Gun)(b)).getBase().getY() / Island.SCALE_FACTOR)
							+ b.getHeight() / 6;
					
					Bullet newB = new Bullet(newX, newY, new double[] {newX, newY}, Board.MOUSE_COORDS, ImagePaths.BULLET1);
					playerIsland.getBullets().add(newB);
				}
			}
		}
		
		for (int i = 0; i < playerIsland.getBullets().size(); i++) {
			Bullet b = playerIsland.getBullets().get(i);
			b.Update();
			b.drawBullet(g);
			if (b.outOfBounds()) {
				System.out.println("Yup");
				playerIsland.getBullets().remove(b);
				i--;
			}
				
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
			FileInputStream fin = new FileInputStream(FOLDER + "/"
					+ PLAYER_ISLAND_FILE);
			ObjectInputStream ois = new ObjectInputStream(fin);
			playerIsland = (Island) ois.readObject();
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
