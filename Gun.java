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
		g.drawImage(getImage(), getX(), getY(), null);
	}
	
	@Override
	public String toString() {
		return "A gun.\n";
	}
}
