import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.io.Serializable;


/*
 * This is a building that's actually a gun.
 * Should have methods to track the mouse and shoot
 * bullets. Maybe the bullet arraylist can be associated
 * with a Gun...
 */
public class Gun extends Building implements Serializable {
	private static final long serialVersionUID = -1147110027479523024L;
	
	private Sprite base;
	private Sprite shaft;
	private int power;
	
	public Gun(int x, int y, String imageName, int _power) {
		super(x, y, imageName);
		base = new Sprite(x, y, ImagePaths.GUN1_14x14_BASE);
		shaft = new Sprite(x, y, ImagePaths.GUN1_14x14_SHAFT);
		power = _power;
	}
	
	//this is temporary
	public int getPower() {return power;}
	public void setPower(int _power) {power = _power;}
	
	@Override
	public void drawBuilding(Graphics g, int bx, int by) {
		if (Island.LARGE) {
			g.drawImage(
				getImage(),
				bx + getX() - (getWidth() / 2), 
				by + Island.FIRST_BUILDING_POS[1] - (getHeight() / 2),
				getWidth(),
				getHeight(), 
				null);			
		} else {
			int newShaftX = bx + (shaft.getX() / Island.SCALE_FACTOR) - shaft.getWidth() / 2;
			int newShaftY = by + (shaft.getY() / Island.SCALE_FACTOR) - shaft.getHeight() / 2;
//			double x = Board.MOUSE_COORDS[0];
//			double y = Board.MOUSE_COORDS[1];
//			
//			double dx = x - newShaftX;
//			double dy = newShaftY - y;
//			double radians = -Math.atan2(dy, dx) + Math.PI/2;
			
			double radians = Bullet.calcAngle(new double[] {newShaftX, newShaftY}, Board.MOUSE_COORDS);
			
			AffineTransform identity = AffineTransform.getTranslateInstance(newShaftX, newShaftY);
			Graphics2D g2d = (Graphics2D)g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			AffineTransform trans = new AffineTransform();
			trans.setTransform(identity);
			trans.rotate(radians, shaft.getWidth()/2, shaft.getHeight());
			g2d.drawImage(shaft.getImage(), trans, null);
//			g.drawImage(shaft.getImage(), newShaftX, newShaftY, null);
			
			int newBaseX = bx + (base.getX() / Island.SCALE_FACTOR) - base.getWidth() / 2;
			int newBaseY = by + (base.getY() / Island.SCALE_FACTOR) - base.getHeight() / 2;
			g.drawImage(base.getImage(), newBaseX, newBaseY, null);
		}
	}
	
	@Override
	public String toString() {
		return "A gun.\n";
	}
}
