import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;

public class PlayerIslandManager {
	private static String PLAYER_ISLAND_FILE = "playerIslandData.txt";
	private static String FOLDER = "Player";

	public static int INACCURACY = 10;
	
	private static int VERT_MOVE_SPEED = 3;

	private Island playerIsland;
	private boolean shoot;

	public PlayerIslandManager() {
		playerIsland = new Island(300, 300, ImagePaths.ISLAND_BODY_1);
		shoot = false;
	}
	
	public Island getIsland() {return playerIsland;}
	public void setIsland(Island i) {playerIsland = i;}
	
	public void Update(Graphics g, EnemyIslandManager eim, boolean moveIsland, boolean moveUp) {
		if (Board.SCENE == 0) {
			g.drawImage(new ImageIcon(ImagePaths.START_SCREEN).getImage(), 0, 0, null);
		} else if (Board.SCENE == 1) {
			g.drawImage(new ImageIcon(ImagePaths.BACKGROUND).getImage(), 0, 0, null);
			Island.LARGE = false;
			if (moveIsland && moveUp)
				movePlayerIslandUp();
			else if (moveIsland && !moveUp)
				movePlayerIslandDown();
			updateBullets(g, eim);
			playerIsland.Update(g);
		} else if (Board.SCENE == 2) {
			Island.LARGE = true;
			playerIsland.Update(g);
		} else if (Board.SCENE == 2) {
			Island.LARGE = true;
			playerIsland.Update(g);
		} else if (Board.SCENE == 3) {
			g.drawImage(new ImageIcon(ImagePaths.ISLAND_BODY_1).getImage(), 10, 10, null);
			String text = "Game Over";
			String text2 = "Press q to quit.";
			g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
			Rectangle2D bounds = g.getFontMetrics().getStringBounds(text, g);
			int width = (int)(bounds.getWidth());
			int height = (int)(bounds.getHeight());
			g.drawString(text, Board.FRAME_WIDTH/2 - width/2, Board.FRAME_HEIGHT/2 - height/2);
			bounds = g.getFontMetrics().getStringBounds(text2, g);
			width = (int)(bounds.getWidth());
			height = (int)(bounds.getHeight());
			g.drawString(text2, Board.FRAME_WIDTH/2 - width/2, Board.FRAME_HEIGHT/2 - height/2 + 50);
		}
	}
	
	public void setShoot(boolean bool) {
		shoot = bool;
	}
	
	public void updateBullets(Graphics g, EnemyIslandManager eim) {
		if (shoot) {
			shoot = false;
			for (Building b : playerIsland.getBuildings()) {
				if (b instanceof Gun) {	
					int newX = playerIsland.getBody().getX() + (((Gun)(b)).getBase().getX() / Island.SCALE_FACTOR);
					int newY = playerIsland.getBody().getY() + (((Gun)(b)).getBase().getY() / Island.SCALE_FACTOR)
							+ b.getHeight() / 6;
					double[] target = {Board.MOUSE_COORDS[0] + (int)(Math.random() * (2 * INACCURACY + 1) - INACCURACY), Board.MOUSE_COORDS[1] + (int)(Math.random() * (2 * INACCURACY + 1) - INACCURACY)};
					Bullet newB = new Bullet(newX, newY, new double[] {newX, newY}, target, ImagePaths.BULLET1);
					playerIsland.getBullets().add(newB);
				}
			}
		}
		
		for (int i = 0; i < playerIsland.getBullets().size(); i++) {
			Bullet b = playerIsland.getBullets().get(i);
			b.Update();
			b.drawBullet(g);
			if (b.outOfBounds()) {
				playerIsland.getBullets().remove(b);
				i--;
			} else {
//				for (EnemyIsland e : eim.getEnemyIslands()) {
				for (int j = 0; j < eim.getEnemyIslands().size(); j++) {
					EnemyIsland e = eim.getEnemyIslands().get(j);
					if (b.collision((Sprite)e.getBody())) {
						e.getBody().takeDamage(10);
						if (e.getBody().checkDead()) {
							System.out.println("Enemy defeated");
							Board.DM.addDrop(e.getBody().getX(), e.getBody().getY());
							eim.getEnemyIslands().remove(e);
						}
						playerIsland.getBullets().remove(b);
						i--;
						break;
					} else {
						for (Building bu : e.getBuildings()) {
							if (b.collision((Sprite)bu)) {
								//do stuff to building's health and stuff
								bu.takeDamage(10);
								if (bu.checkDead()) {
									e.getBuildings().remove(bu);
								}
								playerIsland.getBullets().remove(b);
								i--;
								break;
							}
						}
					}
				}
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