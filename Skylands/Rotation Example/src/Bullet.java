public class Bullet {
	private Sprite sprite;
	private int t;
	private int v0, h0;
	
	//tx = target x
	public Bullet(int x, int y, int tx, int ty) {
		sprite = new Sprite(x, y, "Missile.png");
		t = 0;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
}
