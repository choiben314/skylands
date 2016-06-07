
public class Button extends Sprite {

	private String text;

	public Button(int _x, int _y, String _text, String imageName) {
		super(_x, _y, imageName);
		this.text = _text;
	}

	public boolean mouseIntersect(int mx, int my) {
		System.out.println(mx > getX() - getWidth() / 2
				&& mx < getX() + getWidth() / 2
				&& my > getY() - getHeight() / 2
				&& my < getY() + getHeight() / 2);
		return mx > getX() - getWidth() / 2 && mx < getX() + getWidth() / 2
				&& my > getY() - getHeight() / 2
				&& my < getY() + getHeight() / 2;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
