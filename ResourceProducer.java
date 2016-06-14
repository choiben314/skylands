import java.awt.Graphics;
import java.io.Serializable;

/* This class refers to an extension of Building which
 * is just a normal building that produces resources (hopefully)
 */
public class ResourceProducer extends Building implements Serializable {
	private static final long serialVersionUID = 3858208105148098762L;
	
	private static int MAX_TIME = 200; 
	private int time;
	
	private String produces;

	/*
	 * Can add variables for what it produces, how much it produces, and how
	 * often it produces
	 */

	public ResourceProducer(int x, int y, String imageName, String _produces) {
		super(x, y, imageName);
		time = 0;
		produces = _produces;
	}

	// this is temporary
	public String getProduces() {
		return produces;
	}

	public void setProduces(String _produces) {
		produces = _produces;
	}

	@Override
	public void drawBuilding(Graphics g, int bx, int by, boolean isPlayerBuilding) {
		if (Island.LARGE && isPlayerBuilding) {
			g.drawImage(
				getImage(),
				getGlobalX(bx), 
				getGlobalY(by),
				getWidth() * Island.SCALE_FACTOR, 
				getHeight()	* Island.SCALE_FACTOR, 
				null);
		} else {
			g.drawImage(
				getImage(), 
				getGlobalX(bx), 
				getGlobalY(by), 
				null);
			drawHealthBar(g);
		}
	}
	
	public void addMaterials() {
		time = ++time % MAX_TIME;
		if (time == 1) {
			Board.MM.addResource(produces, 1);
		}
	}

	@Override
	public String toString() {
		return "A resource producer.\n";
	}
}
