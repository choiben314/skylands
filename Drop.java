
public class Drop extends Sprite {
//	private String material;
	
	public Drop(int _x, int _y, String imageName) {
		super(_x, _y, imageName);
//		material = _material;
	}
	
//	public String getMaterial() { return material; }
	
	public boolean outOfBounds() {
		return (getX() < 0 || getX() > Board.FRAME_WIDTH) 
				|| getY() > Board.FRAME_HEIGHT;
	}
}
