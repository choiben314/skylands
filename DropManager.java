import java.awt.Graphics;
import java.util.ArrayList;

public class DropManager {
	
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
			if (drops.get(i).outOfBounds() || drops.get(i).collision((Sprite)Board.PIM.getIsland().getBody())) {
				drops.remove(i);
				i--;
			} else {
				Drop d = drops.get(i);
				g.drawImage(d.getImage(), d.getX() - d.getWidth(), d.getY() - d.getHeight(), null);
			}
		}
	}
}
