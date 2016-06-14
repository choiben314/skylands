import java.util.ArrayList;
import java.awt.Graphics;
import java.io.Serializable;

/*
 * Island has two parts: body and buildings.
 */
public class Island implements Serializable {
	private static final long serialVersionUID = -4990081285229491555L;

	public static int SCALE_FACTOR = 3;
	public static boolean LARGE = true;
	
	public static int BODY_HEIGHT = 231 * SCALE_FACTOR;
	public static int MAX_BUILDING_NUM = 20;
	public static int[] FIRST_BUILDING_POS = {-399, -1 * (BODY_HEIGHT/2 + 21)};
	public static int[][] BUILDING_POS;
	
	private IslandBody body;
	private ArrayList<Building> buildings;
	private ArrayList<Bullet> bullets;
	
	/*
	 * imageName is the name of the body...
	 */
	public Island(int x, int y, String imageName) {
		body = new IslandBody(x, y, imageName);
		buildings = new ArrayList<Building>();
		if (BUILDING_POS == null) {
			BUILDING_POS = new int[20][2];
			for (int i = 0; i < 20; i++) {
				Island.BUILDING_POS[i][0] = Island.FIRST_BUILDING_POS[0] + 42
						* i;
				Island.BUILDING_POS[i][1] = Island.FIRST_BUILDING_POS[1];
			}
		}

		bullets = new ArrayList<Bullet>();
	}
	
	public void Update(Graphics g) {
		body.Update(g);
		for (Building b : buildings) {
			b.Update(g, body.getX(), body.getY());
		}
		drawIsland(g);
	}
	
	/*
	 * You shouldn't have to edit this function, but if you do,
	 * there's an overridden version in the EnemyIsland class, so just
	 * make sure that does what you want it to do.
	 */
	public void drawIsland(Graphics g) {
		ArrayList<Building> drawLate = new ArrayList<Building>();
		body.drawIslandBody(g, true);
		for (Building b : buildings) {
			if (b instanceof Gun) {
				drawLate.add(b);
			} else {
				b.drawBuilding(g, body.getX(), body.getY(), true);				
			}
		}
		for (Building b : drawLate) {
			b.drawBuilding(g, body.getX(), body.getY(), true);
		}
	}
	
	public boolean checkDead() {
		return body.checkDead();
	}

	public IslandBody getBody() {
		return body;
	}

	public void addBuilding(Building b) {
		buildings.add(b);
	}

	public ArrayList<Building> getBuildings() {
		return buildings;
	}

	public void removeBuilding(Building b) {
		buildings.remove(b);
	}

	public void moveVert(int amt) {
		if (body.getY() - amt > 25 && body.getY() - amt < Board.FRAME_HEIGHT)
		body.setY(body.getY() - amt);
	}

	public void moveHoriz(int amt) {
		body.setX(body.getX() + amt);
	}
	
	public ArrayList<Bullet> getBullets() {
		return bullets;
	}

	@Override
	public String toString() {
		String temp = "";
		temp += body.toString();
		for (Building b : buildings)
			temp += b.toString();
		return temp;
	}
}
