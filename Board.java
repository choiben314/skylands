import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener, KeyListener,
		MouseListener, MouseMotionListener {
	private static Board BOARD;
	private Timer timer;
	private Color turquoise;
	private PlayerIslandManager playerIslandManager;
	
	Gun gun;

	public Board() {
		/* 
		 * Variable declarations here
		 */
		BOARD = this;
		timer = new Timer(1000, this);
		timer.start();
		turquoise = new Color(0, 255, 255);
		playerIslandManager = new PlayerIslandManager();
		
		gun = new Gun(30, 30, "Missile.png");
		
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(turquoise);
		setDoubleBuffered(true);

		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);

	}

	public void paint(Graphics g) {
		super.paint(g);
		BOARD = this;
		
		gun.drawBuilding(g);
	}

	public void actionPerformed(ActionEvent e) {}

	private class TAdapter extends KeyAdapter {}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_SPACE) {
			
		} else if (key == KeyEvent.VK_Q) {
			System.exit(0);
		} else if (key == KeyEvent.VK_C){
			repaint();
		}
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
}
