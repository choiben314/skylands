import java.awt.Image;

import javax.swing.ImageIcon;


public class Sprite {
	private int x;
	private int y;
	private int width;
	private int height;
	private Image image;
	
	public Sprite(int _x, int _y, String imageName) {
		x = _x;
		y = _y;
		loadImage(imageName);
		getImageDimensions();
	}
	
	public void loadImage(String imageName) {
		ImageIcon ii = new ImageIcon(imageName);
		image = ii.getImage();
	}
	
	public void getImageDimensions() {
		width = image.getWidth(null);
		height = image.getHeight(null);
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image img) {
		image = img;
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
}
