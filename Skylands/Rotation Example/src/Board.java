import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener, KeyListener,
		MouseListener, MouseMotionListener {

	private Timer timer;
	private Color turqoise = new Color(0, 255, 255);
	private Sprite gun;
	private ArrayList<Sprite> bullets = new ArrayList<Sprite>();
	private int x, y;

	public Board() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(turqoise);
		setDoubleBuffered(true);

		timer = new Timer(1000, this);
		timer.start();
		
		gun = new Sprite(300, 60, "Missile.png");

		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);

	}

	public void paint(Graphics g) {
		super.paint(g);

		updateBullets();
		drawBullets(g);
		drawGun(g);
	}

	public void actionPerformed(ActionEvent e) {

	}

	private class TAdapter extends KeyAdapter {

	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_SPACE) {
		}
		else if (key == KeyEvent.VK_C){
			repaint();
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
		fireGun(e.getX(), e.getY());
		e.consume();
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
//		rotateGun(e.getX(), e.getY());
		x = e.getX();
		y = e.getY();

		repaint();
		e.consume();
	}

	public void mouseDragged(MouseEvent e) {
	}
	
	public void updateBullets() {
		
	}
	
	public void drawBullets(Graphics g) {
		
	}

	public void drawGun(Graphics g) {
//		g.drawImage(gun.getImage(), gun.getX(), gun.getY(), this);
		rotateGun(x, y, g);
	}
	
	public BufferedImage toBufferedImage(Image img) {
		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();
		
		return bimage;
	}
	
	public void rotateGun(int x, int y, Graphics g) {
		double dx = x - gun.getX();
		double dy = gun.getY() - y;
		double radians = -Math.atan2(dy, dx) + Math.PI/2;
		
		AffineTransform identity = AffineTransform.getTranslateInstance(gun.getX(), gun.getY());
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		AffineTransform trans = new AffineTransform();
		trans.setTransform(identity);
		trans.rotate(radians, gun.getWidth()/2, gun.getHeight()/2);
		g2d.drawImage(gun.getImage(), trans, this);
	}
	
	public void fireGun(int x, int y) {
		
	}
}