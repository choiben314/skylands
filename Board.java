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
	public static final int FRAME_WIDTH = 1000;
	public static final int FRAME_HEIGHT = 600;

	public static Board BOARD;
	public static PlayerIslandManager PIM;
	/*
	 * Scene 0 = start screen Scene 1 = game Scene 2 = zoomed in scene of island
	 */
	public static int SCENE;
	public static double[] MOUSE_COORDS;

	private Timer timer;
	private Color turquoise;
	private PlayerIslandManager playerIslandManager;
	private BuildingManager buildingManager;
	private String bType;

	private boolean mouseEntered;
	private boolean moveIsland;
	private boolean moveUp;

	private EnemyIslandManager enemyIslandManager;

	public Board() {
		/*
		 * Variable declarations here
		 */
		BOARD = this;
		
		SCENE = 0;
		MOUSE_COORDS = new double[] {0.0, 0.0};
		timer = new Timer(1000, this);
		timer.start();

		turquoise = new Color(0, 255, 255);
		playerIslandManager = new PlayerIslandManager();
		PIM = playerIslandManager;
		enemyIslandManager = new EnemyIslandManager();
		buildingManager = new BuildingManager(playerIslandManager);
		bType = "wood";

		// testSaving();
		// testLoading();

		// for (int i = 0; i < 20; i++) {
		// if (i == 2 || i == 5 || i == 7) {
		// Gun gNew = new Gun(Island.FIRST_BUILDING_POS[0] + 42 * i,
		// Island.FIRST_BUILDING_POS[1], ImagePaths.GUN1_42x42, 10);
		// playerIslandManager.getIsland().addBuilding(gNew);
		// } else {
		// ResourceProducer rNew = new
		// ResourceProducer(Island.FIRST_BUILDING_POS[0] + 42 * i,
		// Island.FIRST_BUILDING_POS[1], ImagePaths.BUILDING_TEST, "wood");
		// playerIslandManager.getIsland().addBuilding(rNew);
		// }
		// }

		mouseEntered = false;
		moveIsland = false;
		moveUp = false;

		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(turquoise);
		setDoubleBuffered(true);

		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	/*
	 * This is the game loop (like Update() in Unity)
	 */
	public void Update() {
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		BOARD = this;
		playerIslandManager.Update(g, enemyIslandManager, moveIsland, moveUp);
		enemyIslandManager.Update(g, playerIslandManager);
		buildingManager.Update(g, mouseEntered, bType);
	}

	public void actionPerformed(ActionEvent e) {
	}

	private class TAdapter extends KeyAdapter {
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_Q) {
			System.exit(0);
		} else if (key == KeyEvent.VK_0) {
			SCENE = 0;
		} else if (key == KeyEvent.VK_1) {
			playerIslandManager.getIsland().getBody().setX(200);
			playerIslandManager.getIsland().getBody().setY(300);
			SCENE = 1;
		} else if (key == KeyEvent.VK_2) {
			SCENE = 2;
		}
		
		if (SCENE == 0) {

		} else if (SCENE == 1) {
			if (key == KeyEvent.VK_SPACE) {
				playerIslandManager.setShoot(true);
			}
			if (key == KeyEvent.VK_W) {
				moveIsland = true;
				moveUp = true;
			} else if (key == KeyEvent.VK_S) {
				moveIsland = true;
				moveUp = false;
			}
		} else if (SCENE == 2) {
			if (key == KeyEvent.VK_J) {
				bType = "wood";
			} else if (key == KeyEvent.VK_K) {
				bType = "gun";
			}
			// if (key == KeyEvent.VK_B) {
			// ResourceProducer rNew = new
			// ResourceProducer(Island.FIRST_BUILDING_POS[0] + 42 * (k++),
			// Island.FIRST_BUILDING_POS[1], ImagePaths.BUILDING_TEST, "wood");
			// playerIslandManager.getIsland().addBuilding(rNew);
			// }
			if (key == KeyEvent.VK_D) {
				ArrayList<Building> b = playerIslandManager.getIsland()
						.getBuildings();
				if (b.size() > 0) {
					b.remove(b.size() - 1);
				}
			}
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
			if (key == KeyEvent.VK_W) {
				moveIsland = false;
			} else if (key == KeyEvent.VK_S) {
				moveIsland = false;
			}
		} else if (SCENE == 2) {

		}
		e.consume();
	}

	public void keyTyped(KeyEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		mouseEntered = true;
	}

	public void mouseExited(MouseEvent e) {
		mouseEntered = false;
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
		if (Board.SCENE == 2) {
			BuildingManager.placeBuilding(e.getX(), e.getY(), bType);
			e.consume();
		}
	}

	public void mouseMoved(MouseEvent e) {
//		System.out.println("Mouse at: " + e.getX() + " " + e.getY());
		MOUSE_COORDS = new double[] { e.getX(), e.getY() };
		e.consume();
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void testSaving() {
		Island pi = playerIslandManager.getIsland();
		ResourceProducer rp = new ResourceProducer(500, 300,
				ImagePaths.MISSILE, "steel");
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
