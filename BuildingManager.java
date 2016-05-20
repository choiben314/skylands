import java.util.ArrayList;

public class BuildingManager {

	public static void placeBuilding(double x, double y, PlayerIslandManager pim) {

		double mx = x;
		double my = y;

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
				System.out.println(shortestIndex);
			}
		}

		int sx = Island.BUILDING_POS[shortestIndex][0];
		int sy = Island.BUILDING_POS[shortestIndex][1];

		if (checkCoords(sx, sy, pim.getIsland().getBuildings())) {
			pim.getIsland().addBuilding(
					new ResourceProducer(sx, sy, ImagePaths.BUILDING_TEST, true,
							"wood"));
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
