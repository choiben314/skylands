import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Sprite implements Serializable {
	private static final long serialVersionUID = -5316908334124456474L;
	private int x;
	private int y;
	private int width;
	private int height;
	private ImageIcon imageIcon;
	private Rectangle rect;

	public Sprite(int _x, int _y, String imageName) {
		x = _x;
		y = _y;
		loadImage(imageName);
		getImageDimensions();
		rect = calcRect();
	}

	public void loadImage(String imageName) {
		imageIcon = new ImageIcon(imageName);
		/*
		 * If -4 something is wrong If 8 I guess things are okay
		 */
		//System.out.println(imageIcon.getImageLoadStatus());
	}

	public void getImageDimensions() {
		width = imageIcon.getImage().getWidth(null);
		height = imageIcon.getImage().getHeight(null);
	}

	public Image getImage() {
		return imageIcon.getImage();
	}

	public void setImageIcon(ImageIcon ii) {
		imageIcon = ii;
	}

	public int getX() {
		return x;
	}

	public void setX(int _x) {
		x = _x;
	}

	public int getY() {
		return y;
	}

	public void setY(int _y) {
		y = _y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void setRect(Rectangle _rect) {
		rect = _rect;
	}

	public Rectangle getRect() {
		return rect;
	}
	
	public Rectangle calcRect() {
		return new Rectangle(x - width/2, y - height/2, width, height);
	}

	/*
	 * May need to consider the case when an object is rotated... If a bullet is
	 * flying and rotates, the collider should rotate accordingly. Use
	 * AffineTransform :( May not need to if we just have a cannonball (then it
	 * doens't need to rotate yay.
	 */

	/*
	 * Detects collision with another sprite
	 */
	public boolean collision(Sprite other) {
		return rect.intersects(other.rect);
	}

	/*
	 * Detect collision with another list of sprites (for example, an arraylist
	 * keeping track of enemy bullets)
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
