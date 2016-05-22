import java.awt.Graphics;
import java.util.ArrayList;


public class EnemyIsland extends Island {
	private static final long serialVersionUID = 2077383742968327390L;
	
	public static int FIRE_DELAY = 200;
	
	private int level;
	private int fireTime;
	
	public EnemyIsland(int x, int y, String imageName, int _level) {
		super(x, y, imageName);
		level = _level;
		fireTime = 0;
	}
	
	public void Update(Graphics g, PlayerIslandManager pim) {
		super.Update(g);
		fireBullets(pim);
		for (int i = 0; i < getBullets().size(); i++) {
			Bullet b = getBullets().get(i);
			b.Update();
			b.drawBullet(g);
			if (b.outOfBounds()) {
				getBullets().remove(b);
				i--;
			}
		}
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
	
	public void fireBullets(PlayerIslandManager pim) {
		fireTime = ++fireTime % FIRE_DELAY;
		if (fireTime == 0) {
			addBullets(pim.getIsland());
		}
	}
	
	public void addBullets(Island pi) {
		for (Building b : getBuildings()) {
			if (b instanceof Gun) {	
				int newX = getBody().getX() + (((Gun)(b)).getBase().getX() / Island.SCALE_FACTOR);
				int newY = getBody().getY() + (((Gun)(b)).getBase().getY() / Island.SCALE_FACTOR)
						+ b.getHeight() / 6;
				double[] target = {pi.getBody().getX(), pi.getBody().getY()};
				double[] targetWithOffset = {target[0] + (int)(Math.random() * (2 * PlayerIslandManager.INACCURACY + 1) - PlayerIslandManager.INACCURACY), 
						target[1] + (int)(Math.random() * (2 * PlayerIslandManager.INACCURACY + 1) - PlayerIslandManager.INACCURACY)};
				Bullet newB = new Bullet(newX, newY, new double[] {newX, newY}, targetWithOffset, ImagePaths.BULLET1);
				getBullets().add(newB);
			}
		}
	}
}