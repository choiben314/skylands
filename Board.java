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
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener, KeyListener,
		MouseListener, MouseMotionListener {
	private static final long serialVersionUID = -1543062753010683501L;
	private static Board BOARD;
	private static int SCENE; //Scene 1 is the game, Scene 0 is a start screen
	
	private Timer timer;
	private Color turquoise;
	private PlayerIslandManager playerIslandManager;
	
	Gun gun;

	public Board() {
		/* 
		 * Variable declarations here
		 */
		BOARD = this;
		SCENE = 1;
		
		timer = new Timer(1000, this);
		timer.start();
		turquoise = new Color(0, 255, 255);
		playerIslandManager = new PlayerIslandManager();
		
		/*
		 * Testing code
		 */
		
//		Island pi = playerIslandManager.getIsland();
//		ResourceProducer rp = new ResourceProducer(500, 300, "Missile.png", "steel");
//		Gun g = new Gun(30, 30, "Missile.png", 37);
//		Gun g1 = new Gun(50, 50, "Missile.png", 89);
//		pi.addBuilding(rp);
//		pi.addBuilding(g);
//		pi.addBuilding(g1);
//		
//		System.out.println(pi);
//		
//		playerIslandManager.saveIsland();
		
		playerIslandManager.loadIsland();
		Island pi = playerIslandManager.getIsland();
		ArrayList<Building> bs = pi.getBuildings();
		for (Building b : bs) {
			if (b instanceof ResourceProducer) {
				System.out.println("Produces: " + ((ResourceProducer)b).getProduces());
			} else {
				System.out.println("Power is: " + ((Gun)b).getPower());
			}
		}
		
		System.out.println(playerIslandManager.getIsland());
		System.exit(0);
		
		/*
		 * Testing code ends
		 */
		
		gun = new Gun(30, 30, "Missile.png", 33);
		
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
