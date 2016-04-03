import java.awt.Graphics;


/* This class refers to an extension of Building which
 * is just a normal building that produces resources (hopefully)
 */
public class ResourceProducer extends Building {
	/*
	 * Can add variables for what it produces, how
	 * much it produces, and how often it produces
	 */
	
	public ResourceProducer(int x, int y, String imageName) {
		super(x, y, imageName);
	}
	
	@Override
	public void drawBuilding(Graphics g) {
		//code to draw building at those coords
	}
}
