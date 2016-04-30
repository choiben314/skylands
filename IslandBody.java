import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.Serializable;

public class IslandBody extends Sprite implements Serializable {
	private static final long serialVersionUID = -2180544871224239940L;
	private int health;

	public IslandBody(int x, int y, String imageName) {
		super(x, y, imageName);
		health = 1000;
	}

	public void drawIslandBody(Graphics g) {
		if (Island.LARGE) {
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
			// System.out.println("Blah" + getX() + " " + getY());
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
