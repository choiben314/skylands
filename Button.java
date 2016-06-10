
public class Button extends Sprite {

	private String text;
	private String imageName;

	public Button(int _x, int _y, String _text, String baseImage, String imageName) {
		super(_x, _y, baseImage);
		this.text = _text;
		this.imageName = imageName;
	}

	public boolean mouseIntersect(int mx, int my) {
		return mx > getX() - getWidth() / 2 && mx < getX() + getWidth() / 2
				&& my > getY() - getHeight() / 2
				&& my < getY() + getHeight() / 2;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getText() {
		return text;
	}

	public void setText(String _text) {
		this.text = _text;
	}

}
