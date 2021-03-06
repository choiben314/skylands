import java.awt.Graphics;
import java.io.Serializable;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/*
 * Building acts to encapsulate(?) the legit 
 * resource producing buildings and guns
 * and provides a way to place buildings/guns
 * without rewriting the code... It'll make
 * sense hopefully maybe.
 */
public abstract class Building extends Sprite implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int bx;
	private int by;
	//health will be displayed as number above building
	private int health;
	
	public Building(int x, int y, String imageName) {
		super(x, y,	imageName);
		health = 100;
		bx = 0;
		by = 0;
	}
	
	public void Update(Graphics g, int _bx, int _by) {
//		System.out.println(bx + " " + by);
		bx = _bx;
		by = _by;
		setRect(calcRect());
	}
	
	@Override 
	public Rectangle calcRect() {
		return new Rectangle(
			getGlobalX(bx),
			getGlobalY(by) + getHeight()/Island.SCALE_FACTOR,
			getWidth(),
			getHeight());
		
	}
	
	/* Also need to draw how much health it has
	 * 
	 * Is abstract because a gun consists of two parts,
	 * so drawing a gun is different from drawing a building,
	 * which is just one chunk
	 */
	/*
	 * bx and by are the x and y coordinates of the island body
	 */
	public abstract void drawBuilding(Graphics g, int bx, int by, boolean isPlayer);
	public abstract String toString();
	
	public void drawHealthBar(Graphics g) {
		int offsetX = 2, offsetY = -5, numBars = 10;
		int startx = getGlobalX(bx) + offsetX, starty = getGlobalY(by) + offsetY;
		int numBarsToDraw = health/numBars;
		
		String image;
		if (numBarsToDraw > 4) {
			image = ImagePaths.BHB_GREEN;
		} else if (numBarsToDraw > 2) {
			image = ImagePaths.BHB_YELLOW;
		} else {
			image = ImagePaths.BHB_RED;
		}
		
		for (int i = 0; i < numBarsToDraw; i++) {
			g.drawImage(new ImageIcon(image).getImage(), startx + i, starty, null);
		}
	}
	
	/*
	 * These two work with images that are drawn as the small scale version,
	 * not when you start off with images that are the large scale version.
	 */
	public int getGlobalX(int bx) {
		if (Island.LARGE) {
			return bx + getX() - (getWidth() * Island.SCALE_FACTOR / 2);
		} else {
			return bx + (getX() / Island.SCALE_FACTOR) - getWidth() / 2;
		}
	}
	
	public int getGlobalY(int by) {
		if (Island.LARGE) {
			return by + Island.FIRST_BUILDING_POS[1] - (getHeight() * Island.SCALE_FACTOR / 2);
		} else {
			return by + (getY() / Island.SCALE_FACTOR) - getHeight() / 2;
		}
	}
	
	//may do according to bullet speed
	public void takeDamage(int dmg) {
		health -= dmg;
	}
	
	public int getHealth() {
		return health;
	}
	
	//Island 
	public boolean checkDead() {
		return health <= 0;
	}
}
