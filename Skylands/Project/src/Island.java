import java.util.ArrayList;

public class Island extends Sprite {
	private int health;
	private IslandBody body;
	private ArrayList<Building> buildings;
	
	public Island(int x, int y, String imageName) {
		super(x, y, imageName);
		health = 1000;
		body = generateBody();
	}
	
	public IslandBody generateBody() {
		return new IslandBody();
	}
	
	public void drawIsland(int x, int y) {
		
	}
	
	//affects the main body thing
	public void takeDamage(int dmg) {
		health -= dmg;
	}
	
	//IslandManager gon be checking
	public boolean checkDead() {
		return health <= 0;
	}
}
