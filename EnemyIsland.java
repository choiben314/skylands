
public class EnemyIsland extends Island {
	private static final long serialVersionUID = 2077383742968327390L;
	
	private int level;
	
	public EnemyIsland(int x, int y, String imageName, int _level) {
		super(x, y, imageName);
		level = _level;
	}
	
	public int getLevel() { return level; }
}
