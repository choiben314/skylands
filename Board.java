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

/*
 * https://www.google.com/search?client=safari&rls=en&q=constantly+update+in+jpanel+java&ie=UTF-8&oe=UTF-8
 */
public class Board extends JPanel implements ActionListener, KeyListener,
		MouseListener, MouseMotionListener {
	private static final long serialVersionUID = -1543062753010683501L;
	public static Board BOARD;
	/*
	 * Scene 0 = start screen
	 * Scene 1 = game
	 * Scene 2 = zoomed in scene of island
	 */
	public static int SCENE;
	
	private int frameCount;
	private Timer timer;
	private Color turquoise;
	private PlayerIslandManager playerIslandManager;
	
	private boolean moveIsland;
	private boolean moveUp;
	Gun gun;

	public Board() {
		/* 
		 * Variable declarations here
		 */
		BOARD = this;
		SCENE = 1;
		
		frameCount = 0;
		timer = new Timer(1000, this);
		timer.start();
		
//		timer = new Timer(1000, new ActionListener(){
//            public void actionPerformed(ActionEvent e) {
//            	System.out.println("something performed");
//                repaint();
//            	timer.start();
//            }
//        });

		turquoise = new Color(0, 255, 255);
		playerIslandManager = new PlayerIslandManager();
		
//		testSaving();
//		testLoading();
		
		for (int i = 0; i < 20; i++) {
			ResourceProducer rNew = new ResourceProducer(Island.FIRST_BUILDING_POS[0] + 42 * i, Island.FIRST_BUILDING_POS[1], ImagePaths.BUILDING_TEST, "wood");
			playerIslandManager.getIsland().addBuilding(rNew);
		}
		
		moveIsland = false;
		moveUp = false;
		gun = new Gun(30, 30, ImagePaths.MISSILE, 33);
		
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(turquoise);
		setDoubleBuffered(true);

		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		frameCount++;
		if (frameCount > 1000) {
			frameCount %= 1000;
			
			BOARD = this;
			System.out.println("blahblahblah");
			System.out.println(moveIsland + " " + moveUp);
			playerIslandManager.Update(g, moveIsland, moveUp);
			
		}
//		repaint();
	}
	
//	@Override
//	public void paintComponent(Graphics g) {
//		super.paint(g);
//		BOARD = this;
//		System.out.println("blahblahblah");
//		System.out.println(moveIsland + " " + moveUp);
//		playerIslandManager.Update(g, moveIsland, moveUp);
//		
////		gun.drawBuilding(g);
//	}

	public void actionPerformed(ActionEvent e) {}

	private class TAdapter extends KeyAdapter {}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_Q) {
			System.exit(0);
		}
		
		if (SCENE == 0) {
			
		} else if (SCENE == 1) {
			if (key == KeyEvent.VK_W){
//				playerIslandManager.movePlayerIslandUp();
				moveIsland = true;
				moveUp = true;
				repaint();
			} else if (key == KeyEvent.VK_S) {
				moveIsland = true;
				moveUp = false;
//				playerIslandManager.movePlayerIslandDown();
				repaint();
			}
		} else if (SCENE == 2) {
			
		}	
		e.consume();
	}

	/*
	 * Remember to e.consume();
	 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (SCENE == 0) {
			
		} else if (SCENE == 1) {
			if (key == KeyEvent.VK_W){
				moveIsland = false;
				repaint();
			} else if (key == KeyEvent.VK_S) {
				moveIsland = false;
				repaint();
			}
		} else if (SCENE == 2) {
			
		}
		e.consume();
	}
	public void keyTyped(KeyEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {
		System.out.println(e.getX() + " " + e.getY());
		e.consume();
	}
	public void mouseDragged(MouseEvent e) {}

	public void testSaving() {
		Island pi = playerIslandManager.getIsland();
		ResourceProducer rp = new ResourceProducer(500, 300, ImagePaths.MISSILE, "steel");
		Gun g = new Gun(30, 30, ImagePaths.MISSILE, 38);
		Gun g1 = new Gun(50, 50, ImagePaths.MISSILE, 89);
		pi.addBuilding(rp);
		pi.addBuilding(g);
		pi.addBuilding(g1);
		
		System.out.println(pi);
		
		playerIslandManager.saveIsland();
		System.exit(0);
	}
	
	public void testLoading() {
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
	}
}
