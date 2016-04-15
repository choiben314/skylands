import javax.swing.JFrame;

public class Main extends JFrame {
	private static final long serialVersionUID = 4648172894076113183L;

	public Main() {
//		add(new Board());
//		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setSize(1000, 600);
//		setLocationRelativeTo(null);
//		setTitle("Skylands");
//		setResizable(false);
//		setVisible(true);
	}
	
	public static void main(String[] args) throws InterruptedException {
//		new Main();
		
		JFrame frame = new JFrame("Skylands");
		Board board = new Board();
		frame.add(board);
		frame.setSize(1000, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while(true) {
			board.Update();
			Thread.sleep(10);
		}
	}
}
