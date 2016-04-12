import java.awt.Graphics;
import java.io.Serializable;

/*
 * Building acts to encapsulate(?) the legit 
 * resource producing buildings and guns
 * and provides a way to place buildings/guns
 * without rewriting the code... It'll make
 * sense hopefully maybe.
 */
public abstract class Building extends Sprite implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//health will be displayed as number above building
	private int health;
	
	public Building(int x, int y, String imageName) {
		super(x, y, imageName);
		health = 100;
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
	public abstract void drawBuilding(Graphics g, int bx, int by);
	public abstract String toString();
	
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
