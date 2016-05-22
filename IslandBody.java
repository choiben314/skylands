import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;

public class IslandBody extends Sprite implements Serializable {
	private static final long serialVersionUID = -2180544871224239940L;
	
//	private static int HEIGHT = 231;
//	private static int RANDOMLY_SUBTRACT = 37;
//	private static int HEIGHT = 231 - RANDOMLY_SUBTRACT;
//	private static int Y_OFFSET = -1 * (300 - HEIGHT);
	
	private int health;

	public IslandBody(int x, int y, String imageName) {
		super(x, y, imageName);
		health = 1000;
	}
	
	public void Update(Graphics g) {
		System.out.println("IslandBody" + getX() + " " + getY());
		setRect(calcRect());
		System.out.println("IslandRect" + getRect());
	}
	
	@Override
	public Rectangle calcRect() {
		return new Rectangle(
			getX() - getWidth()/4,
			getY() - getHeight()/2,
			getWidth()/2,
			getHeight()/2
			);
	}

	public void drawIslandBody(Graphics g, boolean isPlayerBody) {
		if (Island.LARGE && isPlayerBody) {
			setX(Board.FRAME_WIDTH / 2);
			setY((Board.FRAME_HEIGHT / 2) + (getHeight() * Island.SCALE_FACTOR / 2));

			// draws from center.
			g.drawImage(getImage(), getX()
					- (getWidth() * Island.SCALE_FACTOR / 2), getY()
					- (getHeight() * Island.SCALE_FACTOR / 2), getWidth()
					* Island.SCALE_FACTOR, getHeight() * Island.SCALE_FACTOR,
					null);
		} else {
			g.drawImage(getImage(), getX() - getWidth() / 2, getY()
					- getHeight() / 2, null);
//			 System.out.println("Blah" + getX() + " " + getY());
		}
	}

	// affects the main body thing
	public void takeDamage(int dmg) {
		health -= dmg;
	}

	// IslandManager will be checking
	public boolean checkDead() {
		return health <= 0;
	}

	@Override
	public String toString() {
		return "An island body.\n";
	}
}
