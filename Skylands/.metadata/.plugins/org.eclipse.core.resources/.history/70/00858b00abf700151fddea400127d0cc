
public class Building extends Sprite {
	//health will be displayed as number above building
	private int health;
	
	public Building(int x, int y, String imageName) {
		super(x, y, imageName);
		health = 100;
	}
	
	public void drawBuilding(int x, int y) {
		//will draw in x, y
		//also need to draw how much health it has
	}
	
	//may do according to bullet speed
	public void takeDamage(int dmg) {
		health -= dmg;
	}
	
	//Island 
	public boolean checkDead() {
		return health <= 0;
	}
}
