import java.awt.Graphics;


/*
 * This is a building that's actually a gun.
 * Should have methods to track the mouse and shoot
 * bullets. Maybe the bullet arraylist can be associated
 * with a Gun...
 */
public class Gun extends Building {
	
	public Gun(int x, int y, String imageName) {
		super(x, y, imageName);
	}

	@Override
	public void drawBuilding(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), null);
	}
}
