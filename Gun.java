import java.awt.Graphics;
import java.io.Serializable;


/*
 * This is a building that's actually a gun.
 * Should have methods to track the mouse and shoot
 * bullets. Maybe the bullet arraylist can be associated
 * with a Gun...
 */
public class Gun extends Building implements Serializable {
	private static final long serialVersionUID = -1147110027479523024L;
	private int power;
	
	public Gun(int x, int y, String imageName, int _power) {
		super(x, y, imageName);
		power = _power;
	}
	
	//this is temporary
	public int getPower() {return power;}
	public void setPower(int _power) {power = _power;}
	
	@Override
	public void drawBuilding(Graphics g, int bx, int by) {
		if (Island.LARGE) {
			g.drawImage(getImage(),
					bx + getX() - (getWidth() * Island.SCALE_FACTOR / 2), by + Island.FIRST_BUILDING_POS[1] - (getHeight() * Island.SCALE_FACTOR / 2),
					getWidth() * Island.SCALE_FACTOR, getHeight()
							* Island.SCALE_FACTOR, null);
		} else {
			int newX = bx + (getX() / Island.SCALE_FACTOR) - getWidth() / 2;
			int newY = by + (getY() / Island.SCALE_FACTOR) - getHeight() / 2;
			g.drawImage(getImage(), newX, newY, null);
		}
	}
	
	@Override
	public String toString() {
		return "A gun.\n";
	}
}
