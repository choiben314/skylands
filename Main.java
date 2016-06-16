/*    Your name: Justin Li & Ben Choi :)
    Class block:  H               Date: 6/16/26
    Lab: Final
    Title: Quarter 4 Project
    Purpose: Final project. Learn graphics library and version control.
*/

import javax.swing.*;

// cd /Volumes/yog2018-2/18choi9/APCompSci/Skylands
// cd /Volumes/yog2018-4/18li20/APCompSci/Skylands

public class Main extends JFrame {
	private static final long serialVersionUID = 4648172894076113183L;

	public static void main(String[] args) throws InterruptedException {
		// new Main();

		JFrame frame = new JFrame("Skylands");		
		Board board = new Board();
		
		frame.add(board);
		
		frame.setSize(Board.FRAME_WIDTH, Board.FRAME_HEIGHT);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		long oldTime = System.currentTimeMillis();
		//System.out.println(System.currentTimeMillis());

		new AudioPlayer("Images/sky_maneuvers.wav");
		while (true) {
			if (System.currentTimeMillis() - oldTime > 5) {
				oldTime = System.currentTimeMillis();
				board.Update();
			}
		}
	}
}
