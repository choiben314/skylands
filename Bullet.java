//import java.awt.Graphics;
//import java.io.Serializable;
//
//public class Bullet extends Sprite implements Serializable {
//	public static double GRAVITY = 0.5;
//	public static double MAX_TOP = 1.0;
//	
//	private double t;
//	private double[] start;
//	private double[] target;
//	private double[] traj;
//	
//	public Bullet(int x, int y, double[] _start, double[] _target, String imageName) {
//		super(x, y, imageName);
//		t = 0.0;
//		start = _start;
//		target = _target;
//		traj = calcTrajectory(start, target);
//	}
//	
//	public void drawBullet(Graphics g) {
//		g.drawImage(getImage(), getX() - getWidth()/2, getY() - getHeight()/2, null);
//	}
//	
//	public void Update() {
//		t += 0.1;
//		double x = start[0] + traj[0] * t;
//		double y = start[1] - 0.5 * GRAVITY * Math.pow(t, 2) + traj[1] * t;
//		setX((int)x);
//		setY((int)y);
//	}
//	
//	/*
//	 * If it's out of bounds, the bullet should be removed from
//	 * the bullet array.
//	 */
//	public boolean outOfBounds() {
//		return (getX() < 0 || getX() > Board.FRAME_WIDTH) 
//				|| getY() > Board.FRAME_HEIGHT;
//	}
//	
//	public static double[] calcTrajectory(double[] start, double[] target) {
//		double[] traj = new double[2];
//		double vx, vy;
//		double a, b, c, tAtTarget;
//		double highest = start[1] > target[1] ? start[1] : target[1];
//		vy = Math.sqrt(2 * GRAVITY * (highest - start[1] + MAX_TOP));
//		a = -0.5 * GRAVITY;
//		b = vy;
//		c = start[1] - target[1];
//		tAtTarget = (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c))/(2 * a);
//		vx = (target[0] - start[0])/tAtTarget;
//		traj[0] = vx;
//		traj[1] = vy;
//		return traj;
//	}
//
//	/*
//	 * Returns angle (in radians) of the bullet at time t = 0
//	 * This is used by Gun to see where to point the shaft.
//	 */
//	public static double calcAngle(double[] start, double[] target) {
//		double ivx, ivy;
//		double t = 0.0;
//		double theta;
//		double[] traj = calcTrajectory(start, target);
//		ivx = traj[0];
//		ivy = -GRAVITY * t * traj[1];
//		theta = Math.atan2(ivy, ivx);
//		return theta;
//	}
//}
import java.awt.Graphics;
import java.io.Serializable;

public class Bullet extends Sprite implements Serializable {
	private static final long serialVersionUID = 696223701749554369L;
	
	/*
	 * Make this more negative to make the bullet
	 * travel faster
	 */
	public static double GRAVITY = -0.5;
	/*
	 * Between the mouse coordinates and the gun coordinates, the bullet
	 * will travel as high as MAX_TOP units above the highest of the two.
	 */
	public static double MAX_TOP = 100.0;
	/*
	 * Determines the width and height of the bullet
	 * (equal length for height and width)
	 */
	private static int BULLET_LENGTH = 2;
	
	private double t;
	private double[] start;
	private double[] target;
	private double[] traj;
	
	public Bullet(int x, int y, double[] _start, double[] _target, String imageName) {
		super(x, y, imageName);
		t = 0.0;
		start = _start;
		target = _target;
		traj = calcTrajectory(start, target);
	}
	
	public void drawBullet(Graphics g) {
		g.drawImage(
			getImage(), 
//			getX() - getWidth()/2, 
//			getY() - getHeight()/2, 
			getX() - BULLET_LENGTH/2,
			getY() - BULLET_LENGTH/2,
			BULLET_LENGTH, 
			BULLET_LENGTH, 
			null);
	}
	
	public void Update() {
		t -= 0.1;
		double x = start[0] + traj[0] * t;
		double y = start[1] - 0.5 * GRAVITY * Math.pow(t, 2) + traj[1] * t;
		setX((int)x);
		setY((int)y);
	}
	
	/*
	 * If it's out of bounds, the bullet should be removed from
	 * the bullet array.
	 */
	public boolean outOfBounds() {
		return (getX() < 0 || getX() > Board.FRAME_WIDTH) 
				|| getY() > Board.FRAME_HEIGHT;
	}
	
	public static double[] calcTrajectory(double[] start, double[] target) {
		double[] traj = new double[2];
		double vx, vy;
		double a, b, c, tAtTarget;
		double highest = start[1] < target[1] ? start[1] : target[1];
		vy = Math.sqrt(2 * GRAVITY * (highest - start[1] - MAX_TOP));
		a = -0.5 * GRAVITY;
		b = vy;
		c = start[1] - target[1];
		tAtTarget = (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c))/(2 * a);
		vx = (target[0] - start[0])/tAtTarget;
		traj[0] = vx;
		traj[1] = vy;
		return traj;
	}

	/*
	 * Returns angle (in radians) of the bullet at time t = 0
	 * This is used by Gun to see where to point the shaft.
	 */
	public static double calcAngle(double[] start, double[] target) {
//		double ivx, ivy;
//		/*
//		 * Might have to change t...
//		 */
//		double t = 0.1;
//		double theta;
//		double[] traj = calcTrajectory(start, target);
//		System.out.println("traj is: " + traj[0] + " " + traj[1]);
//		ivx = traj[0];
//		ivy = -GRAVITY * t * traj[1];
//		theta = Math.atan2(ivy, ivx);
//		System.out.println("Theta is: " + theta);
//		return theta;
		double[] traj = calcTrajectory(start, target);
		double theta = Math.atan2(traj[1], traj[0]) - Math.PI/2;
		return theta;
	}
}