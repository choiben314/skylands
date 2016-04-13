import java.util.ArrayList;
import java.awt.Graphics;
import java.io.Serializable;

/*
 * Island has two parts: body and buildings.
 */
public class Island implements Serializable {
	private static final long serialVersionUID = -4990081285229491555L;
	
	public static int SCALE_FACTOR = 3;
	public static boolean LARGE = false;
//	public static int[] FIRST_BUILDING_POS = {-414, -486};
	public static int[] FIRST_BUILDING_POS = {-399, -471};
	
	private IslandBody body;
	private ArrayList<Building> buildings;
	
	/*
	 * imageName is the name of the body...
	 */
	public Island(int x, int y, String imageName) {
		body = new IslandBody(x, y, imageName);
		buildings = new ArrayList<Building>();
	}
	
	public void drawIsland(Graphics g) {
		body.drawIslandBody(g);
		for (Building b : buildings) {
			b.drawBuilding(g, body.getX(), body.getY());
		}
	}
	
	public boolean checkDead() {
		return body.checkDead();
	}
	
	public IslandBody getBody() { return body; }
	
	public void addBuilding(Building b) {
		buildings.add(b);
	}
	
	public ArrayList<Building> getBuildings() {
		return buildings;
	}
	
	public void removeBuilding(Building b) {
		buildings.remove(b);
	}
	
	public void moveUp(int amt) {
		body.setY(body.getY() - amt);
		for (Building b : buildings) {
			b.setY(b.getY() - amt);
		}
	}
	
	public void moveDown(int amt) {
		body.setX(body.getX() + amt);
		for (Building b : buildings) {
			b.setX(b.getX() + amt);
		}
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
