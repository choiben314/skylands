import javax.swing.JFrame;

public class Main extends JFrame {
	private static final long serialVersionUID = 4648172894076113183L;

	public Main() {

		add(new Board());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Board.FRAME_WIDTH, Board.FRAME_HEIGHT);
		setLocationRelativeTo(null);
		setTitle("Skylands");
		setResizable(false);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Main();
	}
}
