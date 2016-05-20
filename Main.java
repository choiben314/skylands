import javax.swing.JFrame;

public class Main extends JFrame {
	private static final long serialVersionUID = 4648172894076113183L;

	public static void main(String[] args) throws InterruptedException {
		// new Main();

		JFrame frame = new JFrame("Skylands");
		Board board = new Board();
		frame.add(board);
		frame.setSize(Board.FRAME_WIDTH, Board.FRAME_HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		long oldTime = System.currentTimeMillis();
		System.out.println(System.currentTimeMillis());
		
		while (true) {
			if (System.currentTimeMillis() - oldTime > 5) {
				oldTime = System.currentTimeMillis();
				board.Update();
			}
//			Thread.sleep(10);
		}

	}
}
