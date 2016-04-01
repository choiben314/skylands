
public class IslandBody extends Sprite{
	private int health;
	
	public IslandBody(int x, int y, String imageName) {
		super(x, y, imageName);
		health = 1000;
	}
	
	//affects the main body thing
	public void takeDamage(int dmg) {
		health -= dmg;
	}
	
	//IslandManager will be checking
	public boolean checkDead() {
		return health <= 0;
	}
}
