import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;

import javax.swing.ImageIcon;

public class IslandBody extends Sprite implements Serializable {
	private static final long serialVersionUID = -2180544871224239940L;
	
	public static int MAX_HEALTH = 2000;
	public static int MAX_TIME = 50;
	
	private int health;
	private int time;

	public IslandBody(int x, int y, String imageName) {
		super(x, y, imageName);
		health = MAX_HEALTH;
		time = 0;
	}
	
	public void Update(Graphics g) {
		setRect(calcRect());
		regen();
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
			drawHealthBar(g);
		}
	}
	
	public void regen() {
		time = ++time % MAX_TIME;
		if (time == 1) {
			health = clamp(health + 1, MAX_HEALTH);
		}
	}
	
	public static int clamp(int num, int max) {
		if (num > max) {
			return max;
		} else {
			return num;
		}
	}
	
	public void drawHealthBar(Graphics g) {
		int offsetX = -140, offsetY = -194, numBars = 280;
		int startX = getX() + offsetX, starty = getY() + offsetY;
//		int numBarsToDraw = health/numBars;
		int numBarsToDraw = (int)(((double)health / MAX_HEALTH) * numBars);
		
		String image;
		if (numBarsToDraw > 112) {
			image = ImagePaths.IBHB_GREEN;
		} else if (numBarsToDraw > 56) {
			image = ImagePaths.IBHB_YELLOW;
		} else {
			image = ImagePaths.IBHB_RED;
		}
		
		for (int i = 0; i < numBarsToDraw; i++) {
			g.drawImage(new ImageIcon(image).getImage(), startX + i, starty, null);
		}
	}
	
	public int getHealth() { return health; }

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
