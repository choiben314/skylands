import java.util.ArrayList;

/*
 * Island has two parts: body and buildings.
 */
public class Island {
	private IslandBody body;
	private ArrayList<Building> buildings;
	
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
	
	public void removeBuilding(Building b) {
		buildings.remove(b);
	}
}
