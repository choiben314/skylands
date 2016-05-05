import java.awt.Graphics;
import java.io.Serializable;

/* This class refers to an extension of Building which
 * is just a normal building that produces resources (hopefully)
 */
public class ResourceProducer extends Building implements Serializable {
	private static final long serialVersionUID = 3858208105148098762L;
	String produces;

	/*
	 * Can add variables for what it produces, how much it produces, and how
	 * often it produces
	 */

	public ResourceProducer(int x, int y, String imageName, String _produces) {
		super(x, y, imageName);
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
	public void drawBuilding(Graphics g, int bx, int by) {
//		if (Island.LARGE) {
//			g.drawImage(getImage(),
//					bx + getX() - (getWidth() * Island.SCALE_FACTOR / 2), by + Island.FIRST_BUILDING_POS[1] - (getHeight() * Island.SCALE_FACTOR / 2),
//					getWidth() * Island.SCALE_FACTOR, getHeight()
//							* Island.SCALE_FACTOR, null);
//		} else {
//			g.drawImage(getImage(), bx + (getX() / Island.SCALE_FACTOR)
//					- getWidth() / 2, by + (getY() / Island.SCALE_FACTOR)
//					- getHeight() / 2, null);
//		}
		if (Island.LARGE) {
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
		}
	}

	@Override
	public String toString() {
		return "A resource producer.\n";
	}
}
