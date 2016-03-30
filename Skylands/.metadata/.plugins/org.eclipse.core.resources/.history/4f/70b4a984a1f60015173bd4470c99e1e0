import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
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

	private boolean animate = false;
	// 0 means dead, 1 means alive
	private int[][] board = new int[50][30];
	private int[][] holderboard = new int[50][30];
	private int width = 50;
	private int height = 30;
	private int cellSize = 20;

	private Timer timer;

	private Color turqoise = new Color(0, 255, 255);

	public Board() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(turqoise);
		setDoubleBuffered(true);

		timer = new Timer(1000, this);
		timer.start();

		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);

	}

	public void paint(Graphics g) {
		super.paint(g);

		drawLines(g);
		drawCells(g);

		if (animate == true) {
			checkCells();
			checkEmpty();
		}
		System.out.println(animate);
	}

	public void actionPerformed(ActionEvent e) {

	}

	private class TAdapter extends KeyAdapter {

	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_SPACE) {
			animate = !animate;
			repaint();
		}
		else if (key == KeyEvent.VK_C){
			animate = false;
			clearCells();
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
		System.out.println("Clicked at " + e.getX() + " " + e.getY());
		
		int x = e.getX() / 20;
		int y = e.getY() / 20;
		
		System.out.println(x + " " + y);
		
		if (board[x][y] == 1) {
			board[x][y] = 0;
		} else if (board[x][y] == 0){
			board[x][y] = 1;
		}
		
		repaint();
		e.consume();
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
//		System.out.println(e.getX() + " " + e.getY());
//
//		repaint();
//		e.consume();
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void drawLines(Graphics g) {
		for (int i = 0; i < width + 1; i++) {
			g.drawLine(i * cellSize, 0, i * cellSize, height * cellSize);
		}
		for (int j = 0; j < height + 1; j++) {
			g.drawLine(0, j * cellSize, width * cellSize, j * cellSize);
		}
	}

	public void drawCells(Graphics g) {
		g.setColor(Color.BLACK);
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 30; j++) {
				if (board[i][j] == 1) {
					g.setColor(Color.GRAY);
					g.fillRect(i * cellSize + 1, j * cellSize + 1, 19, 19);
				} else {
					g.setColor(turqoise);
					g.fillRect(i * cellSize + 1, j * cellSize + 1, 19, 19);
				}
			}
		}
	}
	
	public void checkCells() {
		int counter = 0;
		int x = 0, y = 0;
		
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 50; j++) {
				counter = 0;
				
				x = j;
				y = i;
				x += width;
				y += height;
				x %= width;
				y %= height;
				
				//top left
				x = j;
				y = i;
				x -= 1;
				y -= 1;
				x += width;
				y += height;
				x %= width;
				y %= height;
				System.out.println(x + " " + y);
				
				if (board[x][y] == 1) {
					counter++;
				}
				//top middle
				x = j;
				y = i;
				y -= 1;
				x += width;
				y += height;
				x %= width;
				y %= height;
				System.out.println(x + " " + y);
				
				if (board[x][y] == 1) {
					counter++;
				}
				//top right
				x = j;
				y = i;
				x += 1;
				y -= 1;
				x += width;
				y += height;
				x %= width;
				y %= height;
				System.out.println(x + " " + y);
				
				if (board[x][y] == 1) {
					counter++;
				}
				//left
				x = j;
				y = i;
				x -= 1;
				x += width;
				y += height;
				x %= width;
				y %= height;
				System.out.println(x + " " + y);
				
				if (board[x][y] == 1) {
					counter++;
				}
				//right
				x = j;
				y = i;
				x += 1;
				x += width;
				y += height;
				x %= width;
				y %= height;
				System.out.println(x + " " + y);
				
				if (board[x][y] == 1) {
					counter++;
				}
				//bottom left
				x = j;
				y = i;
				x -= 1;
				y += 1;
				x += width;
				y += height;
				x %= width;
				y %= height;
				System.out.println(x + " " + y);
				
				if (board[x][y] == 1) {
					counter++;
				}
				//bottom middle
				x = j;
				y = i;
				y += 1;
				x += width;
				y += height;
				x %= width;
				y %= height;
				System.out.println(x + " " + y);
				
				if (board[x][y] == 1) {
					counter++;
				}
				//bottom right
				x = j;
				y = i;
				x += 1;
				y += 1;
				x += width;
				y += height;
				x %= width;
				y %= height;
				System.out.println(x + " " + y);
				
				if (board[x][y] == 1) {
					counter++;
				}
				
				x = j;
				y = i;
				
				if (counter < 2) {
					holderboard[x][y] = 0;
				} else if (counter == 2) {
					if (board[x][y] == 1) {
						holderboard[x][y] = 1;
					}
				} else if (counter == 3) {
					holderboard[x][y] = 1;
				} else if (counter > 3) {
					holderboard[x][y] = 0;
				}
			}
		}
		
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 30; j++) {
				board[i][j] = holderboard[i][j];
				holderboard[i][j] = 0;
			}
		}
		
		System.out.println(counter);
		repaint();
	}
	
	public void checkEmpty() {
		boolean empty = true;
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 50; j++) {
				if (board[j][i] == 1) {
					empty = false;
				}
			}
		}
		
		if (empty == true) {
			animate = false;
		}
	}
	
	public void clearCells() {
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 30; j++) {
				board[i][j] = 0;
				holderboard[i][j] = 0;
			}
		}
	}
}
