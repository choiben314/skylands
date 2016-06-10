import java.awt.Graphics;
import java.util.ArrayList;

public class DropManager {
	public static int SPEED = 1;
	
	private ArrayList<Drop> drops;
	
	public DropManager() {
		drops = new ArrayList<Drop>();
	}
	
	public void Update(Graphics g, PlayerIslandManager pim) {
		if (Board.SCENE == 1) {
			updateRects();
			drawDrops(g, pim);
		}
	}
	
	public void updateRects() {
		for (Drop d : drops) {
			d.setRect(d.calcRect());
		}
	}
	
	public void drawDrops(Graphics g, PlayerIslandManager pim) {
		for (int i = 0; i < drops.size(); i++) {
			Drop d = drops.get(i);
			if (d.outOfBounds()) {
				drops.remove(i);
				i--;
				break;
			} else if (d.collision((Sprite)Board.PIM.getIsland().getBody())) {
				//add materials here
				Board.MM.addResource("wood", (int)(Math.random() * 10));
				Board.MM.addResource("metal", (int)(Math.random() * 10));
				drops.remove(i);
				i--;
			} else {
				d.setX(d.getX() - SPEED);
				g.drawImage(d.getImage(), d.getX() - d.getWidth(), d.getY() - d.getHeight(), null);
			}
		}
	}
	
	public void addDrop(int x, int y) {
		int yoffset = (int)(Math.random() * 200) - 100;
		drops.add(new Drop(x, y + yoffset, ImagePaths.ITEM_DROP));
	}
}
