import javax.swing.JFrame;

public class ShootingTestMain extends JFrame {
	public ShootingTestMain() {
		add(new Board());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setTitle("Shooting Test");
		setResizable(false);
		setVisible(true);
	}
	
	public static void main (String[] args) {
		new ShootingTestMain();
	}
}
