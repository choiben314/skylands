import java.awt.Graphics;
import java.util.ArrayList;

public class BuildingManager {
	private static PlayerIslandManager pim;

	public BuildingManager(PlayerIslandManager _pim) {
		pim = _pim;
	}

	public void Update(Graphics g, boolean mouseEntered, String type) {
		if (mouseEntered)
			drawTransparent(g, type);
	}

	public static void placeBuilding(double mx, double my, String type) {

		int sIndex = getClosest(mx, my);

		int sx = Island.BUILDING_POS[sIndex][0];
		int sy = Island.BUILDING_POS[sIndex][1];

		if (type.equals("wood")) {
			if (checkCoords(sx, sy, pim.getIsland().getBuildings())) {
				pim.getIsland().addBuilding(
						new ResourceProducer(sx, sy, ImagePaths.BUILDING_TEST,
								type));
			}
		} else if (type.equals("gun")) {
			if (checkCoords(sx, sy, pim.getIsland().getBuildings())) {
				pim.getIsland().addBuilding(
						new Gun(sx, sy, ImagePaths.GUN1_42x42, 10));
			}
		}
	}

	public static int getClosest(double mx, double my) {

		int bx = pim.getIsland().getBody().getX();
		int by = pim.getIsland().getBody().getY();

		int shortestIndex = 0;
		double shortestDist = Math.sqrt(Math.pow((mx
				- (Island.BUILDING_POS[0][0]) + bx), 2)
				+ Math.pow((my - (Island.BUILDING_POS[0][1] + by)), 2));

		for (int i = 0; i < Island.BUILDING_POS.length; i++) {

			double distToBuilding = Math.sqrt(Math.pow(
					(mx - (Island.BUILDING_POS[i][0] + bx)), 2)
					+ Math.pow((my - (Island.BUILDING_POS[i][1] + by)), 2));

			if (distToBuilding < shortestDist) {
				shortestIndex = i;
				shortestDist = distToBuilding;
			}
		}
		return shortestIndex;
	}

	public void drawTransparent(Graphics g, String type) {
		if (Board.SCENE == 2) {
			int sIndex = getClosest(Board.MOUSE_COORDS[0],
					Board.MOUSE_COORDS[1]);
			String ip = null;

			if (type.equals("wood")) {
				ip = ImagePaths.BUILDING_TEST_TRANSPARENT;
			} else if (type.equals("gun")) {
				ip = ImagePaths.GUN1_TRANSPARENT_42x42;
			}

			Sprite t = new Sprite(Island.BUILDING_POS[sIndex][0],
					Island.BUILDING_POS[sIndex][1], ip);

			if (checkCoords(Island.BUILDING_POS[sIndex][0],
					Island.BUILDING_POS[sIndex][1], pim.getIsland()
							.getBuildings())) {
				g.drawImage(
						t.getImage(),
						t.getX() + pim.getIsland().getBody().getX()
								- (t.getWidth() / 2),
						t.getY() + pim.getIsland().getBody().getY()
								- (t.getHeight() / 2), null);
			}
		}
	}

	private static boolean checkCoords(int x, int y, ArrayList<Building> bList) {
		for (Building b : bList) {
			if (b.getX() == x && b.getY() == y) {
				return false;
			}
		}
		return true;
	}
}