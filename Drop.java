
public class Drop extends Sprite {
	private static final long serialVersionUID = 3453738951998721472L;

	public Drop(int _x, int _y, String imageName) {
		super(_x, _y, imageName);
	}
	
	public boolean outOfBounds() {
		return (getX() < 0 || getX() > Board.FRAME_WIDTH) 
				|| getY() > Board.FRAME_HEIGHT;
	}
}
