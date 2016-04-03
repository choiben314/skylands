import java.util.ArrayList;
import java.io.Serializable;

/*
 * Island has two parts: body and buildings.
 */
public class Island implements Serializable {
	private static final long serialVersionUID = -4990081285229491555L;
	private IslandBody body;
	private ArrayList<Building> buildings;
	
	/*
	 * imageName is the name of the body...
	 */
	public Island(int x, int y, String imageName) {
		body = new IslandBody(x, y, imageName);
		buildings = new ArrayList<Building>();
	}
	
	public void drawIsland(int x, int y) {
		
	}
	
	public boolean checkDead() {
		return body.checkDead();
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

	@Override
	public String toString() {
		String temp = "";
		temp += body.toString();
		for (Building b : buildings)
			temp += b.toString();
		return temp;
	}
}
