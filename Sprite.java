import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Sprite {
	private int x;
	private int y;
	private int width;
	private int height;
	private Image image;
	private Rectangle rect;
	
	public Sprite(int _x, int _y, String imageName) {
		x = _x;
		y = _y;
		loadImage(imageName);
		getImageDimensions();
		rect = new Rectangle (x, y, width, height);
	}
	
	public void loadImage(String imageName) {
		ImageIcon ii = new ImageIcon(imageName);
		image = ii.getImage();
	}
	
	public void getImageDimensions() {
		width = image.getWidth(null);
		height = image.getHeight(null);
	}
	
	public Image getImage() {return image;}
	public void setImage(Image img) {image = img;}
	
	public int getX() {return x;}
	public void setX(int _x) {x = _x;}
	
	public int getY() {return y;}
	public void setY(int _y) {y = _y;}
	
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	
	public Rectangle getRect() {return rect;}
	
	/*
	 * Detects collision with another sprite
	 */
	public boolean collision(Sprite other) {
		return rect.intersects(other.rect);
	}
	
	/*
	 * Detect collision with another list of sprites
	 * (for example, an arraylist keeping track of enemy bullets)
	 */
	public boolean collision(ArrayList<Sprite> others) {
		for (Sprite spr : others) {
			if (rect.intersects(spr.rect)) {
				return true;
			}
		}
		return false;
	}
}
