import java.awt.Graphics;
import java.util.ArrayList;


public class EnemyIsland extends Island {
	private static final long serialVersionUID = 2077383742968327390L;
	
	public static int LEFT_SPEED = 1;
	public static int NUM_BURST = 5;
	public static int FIRE_DELAY = 200;
	public static int FIRE_BURST_DELAY = 50;
	
	private int level;
	private int fireTime;
	private boolean burst;
	private int burstCount;
	
	public void fireBullets(PlayerIslandManager pim) {
//		if (burst) {
//			System.out.println("burst");
//			fireTime = fireTime++ % FIRE_BURST_DELAY;
//		} else {
//			System.out.println("here");
//			fireTime = ++fireTime % FIRE_DELAY;
//		}
//		
//		if (fireTime == 0) {
//			System.out.println("here too");
//			if (!burst) {
//				burst = true;
//			} else {
//				burstCount++;
//				if (burstCount == NUM_BURST) {
//					System.out.println("is below");
//					burst = false;
//					burstCount = 0;
////					fireTime++;
//				}
//				addBullets(pim.getIsland());
//			}
//		}
//		System.out.println(fireTime + "  asdf" + burstCount + " " + burst);
		if (burst) {
			
		} else {
			fireTime = ++fireTime % FIRE_DELAY;
			if (fireTime == 1) {
				addBullets(pim.getIsland());
//				burst = true;
			}
		}
	}
	
	public EnemyIsland(int x, int y, String imageName, int _level) {
		super(x, y, imageName);
		level = _level;
		fireTime = 0;
		burst = false;
		burstCount = 0;
	}
	
	public void Update(Graphics g, PlayerIslandManager pim) {
		super.Update(g);
		moveLeft();
		fireBullets(pim);
		for (int i = 0; i < getBullets().size(); i++) {
			Bullet b = getBullets().get(i);
			b.Update();
			b.drawBullet(g);
			if (b.outOfBounds()) {
				getBullets().remove(b);
				i--;
			} else {
				if (b.collision((Sprite)pim.getIsland().getBody())) {
					pim.getIsland().getBody().takeDamage(10);
					if (pim.getIsland().getBody().checkDead()) {
						//ummmmmm the body is dead you lose I guess
					}
					getBullets().remove(b);
					i--;
					break;
				} else {
					for (Building bu : pim.getIsland().getBuildings()) {
						if (b.collision((Sprite)bu)) {
							bu.takeDamage(10);
							if (bu.checkDead()) {
								pim.getIsland().getBuildings().remove(bu);
							}
							getBullets().remove(b);
							i--;
							break;
						}
					}
				}
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
	
	public void moveLeft() {
		if (getBody().getX() > 700) {
			getBody().setX(getBody().getX() - LEFT_SPEED);
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
