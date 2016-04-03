import java.io.Serializable;


public class IslandBody extends Sprite implements Serializable {
	private static final long serialVersionUID = -2180544871224239940L;
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
	
	@Override
	public String toString() {
		return "An island body.\n";
	}
}
