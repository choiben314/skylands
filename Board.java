import java.awt.Color;
import java.awt.Font;
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
	// public static final int FRAME_WIDTH = 1800;
	// public static final int FRAME_HEIGHT = 1000;
	public static final int FIRE_DELAY = 10;

	public static Board BOARD;
	public static PlayerIslandManager PIM;
	public static MaterialsManager MM;
	public static DropManager DM;
	/*
	 * Scene 0 = start screen Scene 1 = game Scene 2 = zoomed in scene of island
	 * Scene 3 = game over?
	 */
	public static int SCENE;
	public static double[] MOUSE_COORDS;

	private Timer timer;
	private Color turquoise;
	private PlayerIslandManager playerIslandManager;
	private BuildingManager buildingManager;
	private String bType;

	private Button changeScene;
	private int[] origIslandCoord;

	private Button woodBuy;
	private Button gunBuy;
	private Button factoryBuy;

	private int[] prices;

	private boolean mouseEntered;
	private boolean moveIsland;
	private boolean moveUp;
	private int fireCount;

	private EnemyIslandManager enemyIslandManager;

	public Board() {
		/*
		 * Variable declarations here
		 */
		BOARD = this;

		MM = new MaterialsManager();
		MM.setResource("wood", 12);
		MM.setResource("metal", 12);
		DM = new DropManager();

		SCENE = 0;
		MOUSE_COORDS = new double[] { 0.0, 0.0 };
		timer = new Timer(1000, this);
		timer.start();

		turquoise = new Color(0, 255, 255);
		playerIslandManager = new PlayerIslandManager();
		PIM = playerIslandManager;
		enemyIslandManager = new EnemyIslandManager();
		buildingManager = new BuildingManager(playerIslandManager);
		bType = "wood";

		changeScene = new Button(60, 30, "   BUILD", ImagePaths.BUTTON1, null);
		origIslandCoord = new int[] { 200, 300 };

		woodBuy = new Button(180, 60, "  FOREST\n\n\n\n\n\n PRICE: 5 WOOD",
				ImagePaths.BUTTON2, ImagePaths.WOOD_PRODUCER);
		gunBuy = new Button(300, 60, "  GUN\n\n\n\n\n\n PRICE: 10 METAL",
				ImagePaths.BUTTON2, ImagePaths.GUN1_42x42);
		factoryBuy = new Button(420, 60,
				"  FACTORY\n\n\n\n\n\n PRICE: 20 METAL", ImagePaths.BUTTON2,
				ImagePaths.METAL_PRODUCER);

		prices = new int[] { 5, 10, 20 }; // forest, gun, factory

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
		fireCount = 0;

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

		if (Board.SCENE == 2) {
			changeScene.setText(" GO BACK");
			drawBuyButton(g, woodBuy);
			drawBuyButton(g, gunBuy);
			drawBuyButton(g, factoryBuy);
			drawStaticButton(g, changeScene);
		} else if (Board.SCENE == 1) {
			changeScene.setText("   BUILD");
			drawStaticButton(g, changeScene);
		}

		DM.Update(g, playerIslandManager);
		MM.Update(g);
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
		} else if (key == KeyEvent.VK_3) {
			SCENE = 3;
		}

		if (SCENE == 0) {

		} else if (SCENE == 1) {
			if (key == KeyEvent.VK_SPACE) {
				fireCount = ++fireCount % FIRE_DELAY;
				if (fireCount == 1) {
					playerIslandManager.setShoot(true);
				}
			}
			if (key == KeyEvent.VK_W) {
				moveIsland = true;
				moveUp = true;
			} else if (key == KeyEvent.VK_S) {
				moveIsland = true;
				moveUp = false;
			}
		}
		// else if (SCENE == 2) {
		// if (key == KeyEvent.VK_J) {
		// bType = "wood";
		// } else if (key == KeyEvent.VK_K) {
		// bType = "gun";
		// }
		// // if (key == KeyEvent.VK_B) {
		// // ResourceProducer rNew = new
		// // ResourceProducer(Island.FIRST_BUILDING_POS[0] + 42 * (k++),
		// // Island.FIRST_BUILDING_POS[1], ImagePaths.BUILDING_TEST, "wood");
		// // playerIslandManager.getIsland().addBuilding(rNew);
		// // }
		// if (key == KeyEvent.VK_D) {
		// ArrayList<Building> b = playerIslandManager.getIsland()
		// .getBuildings();
		// if (b.size() > 0) {
		// b.remove(b.size() - 1);
		// }
		// }
		e.consume();
	}

	/*
	 * Remember to e.consume();
	 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (SCENE == 0) {

		} else if (SCENE == 1) {
			if (key == KeyEvent.VK_SPACE) {
				fireCount = 0;
			}
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
		if (changeScene.mouseIntersect(e.getX(), e.getY())) {
			if (Board.SCENE == 0 || Board.SCENE == 2) {
				Board.SCENE = 1;
				playerIslandManager.getIsland().getBody()
						.setX(origIslandCoord[0]);
				playerIslandManager.getIsland().getBody()
						.setY(origIslandCoord[1]);
			} else {
				Board.SCENE = 2;
				origIslandCoord = new int[] {
						playerIslandManager.getIsland().getBody().getX(),
						playerIslandManager.getIsland().getBody().getY() };
			}
		} else if (woodBuy.mouseIntersect(e.getX(), e.getY())) {
			bType = "wood";
		} else if (factoryBuy.mouseIntersect(e.getX(), e.getY())) {
			bType = "metal";
		} else if (gunBuy.mouseIntersect(e.getX(), e.getY())) {
			bType = "gun";
		} else if (Board.SCENE == 2) {
			System.out.println(BuildingManager.checkCoords(e.getX(), e.getY(),
					playerIslandManager.getIsland().getBuildings()));
			if (BuildingManager.checkCoords(e.getX(), e.getY(),
					playerIslandManager.getIsland().getBuildings())) {
				if (bType == "wood" && MM.checkValue("wood", prices[0])) {
					MM.addResource("wood", -prices[0]);
					BuildingManager.placeBuilding(e.getX(), e.getY(), bType);
				} else if (bType == "metal"
						&& MM.checkValue("metal", prices[2])) {
					MM.addResource("metal", -prices[2]);
					BuildingManager.placeBuilding(e.getX(), e.getY(), bType);
				} else if (bType == "gun" && MM.checkValue("metal", prices[1])) {
					MM.addResource("metal", -prices[1]);
					BuildingManager.placeBuilding(e.getX(), e.getY(), bType);
				}
			}
			e.consume();
		}
	}

	public void mouseMoved(MouseEvent e) {
		// System.out.println("Mouse at: " + e.getX() + " " + e.getY());
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
				System.out.println("Produces: "
						+ ((ResourceProducer) b).getProduces());
			} else {
				System.out.println("Power is: " + ((Gun) b).getPower());
			}
		}

		System.out.println(playerIslandManager.getIsland());
		System.exit(0);
	}

	private void drawStaticButton(Graphics g, Button b) {
		g.drawImage(b.getImage(), b.getX() - b.getWidth() / 2,
				b.getY() - b.getHeight() / 2, null);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
		g.drawString(b.getText(), b.getX() - 25, b.getY() + 5);
	}

	private void drawBuyButton(Graphics g, Button b) {

		g.drawImage(b.getImage(), b.getX() - b.getWidth() / 2,
				b.getY() - b.getHeight() / 2, null);

		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 9));
		drawString(g, b.getText(), b.getX() - 41, b.getY() - 40);

		Sprite preview = new Sprite(b.getX(), b.getY(), b.getImageName());

		if (b.getText().toLowerCase().contains("gun")) {
			g.drawImage(preview.getImage(), preview.getX() - 22,
					preview.getY() - 20, null);
		} else {
			g.drawImage(preview.getImage(), preview.getX() - 22,
					preview.getY() - 20, preview.getWidth()
							* Island.SCALE_FACTOR, preview.getHeight()
							* Island.SCALE_FACTOR, null);
		}
	}

	private void drawString(Graphics g, String text, int x, int y) {
		for (String line : text.split("\n")) {
			g.drawString(line, x, y += g.getFontMetrics().getHeight());
		}
	}
}
