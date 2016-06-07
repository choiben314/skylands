import java.awt.Graphics;
import java.util.ArrayList;

public class DropManager {
	public static int SPEED = 1;
	
	private ArrayList<Drop> drops;
	
	public DropManager() {
		drops = new ArrayList<Drop>();
	}
	
	public void Update(Graphics g) {
		updateRects();
		drawDrops(g);
	}
	
	public void updateRects() {
		for (Drop d : drops) {
			d.calcRect();
		}
	}
	
	public void drawDrops(Graphics g) {
		for (int i = 0; i < drops.size(); i++) {
			if (drops.get(i).outOfBounds()) {
				//add materials here
				System.out.println("removed drop");
				drops.remove(i);
				i--;
			} else if (drops.get(i).collision((Sprite)Board.PIM.getIsland().getBody())) {
				System.out.println("touched body");
				drops.remove(i);
				i--;
			} else {
				Drop d = drops.get(i);
				d.setX(d.getX() - SPEED);
				g.drawImage(d.getImage(), d.getX() - d.getWidth(), d.getY() - d.getHeight(), null);
			}
		}
	}
	
	public void addDrop(int x, int y) {
		drops.add(new Drop(x, y, ImagePaths.ITEM_DROP));
	}
}
