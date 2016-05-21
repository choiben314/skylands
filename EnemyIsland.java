import java.awt.Graphics;
import java.util.ArrayList;


public class EnemyIsland extends Island {
	private static final long serialVersionUID = 2077383742968327390L;
	
	private int level;
	
	public EnemyIsland(int x, int y, String imageName, int _level) {
		super(x, y, imageName);
		level = _level;
	}
	
	public int getLevel() { return level; }
	
	@Override
	public void drawIsland(Graphics g) {
		ArrayList<Building> drawLate = new ArrayList<Building>();
		IslandBody body = getBody();
		body.drawIslandBody(g, false);
		for (Building b : getBuildings()) {
			if (b instanceof Gun) {
				drawLate.add(b);
			} else {
				b.drawBuilding(g, body.getX(), body.getY(), false);				
			}
		}
		for (Building b : drawLate) {
			b.drawBuilding(g, body.getX(), body.getY(), false);
		}
	}
}
