import java.awt.Graphics;
import java.util.ArrayList;

public class EnemyIslandManager {
	private static String[] BODY_IMAGES = new String[] {
		ImagePaths.ISLAND_BODY_ENEMY_1
	};
	
	private ArrayList<EnemyIsland> enemyIslands;
	
	public EnemyIslandManager() {
		enemyIslands = new ArrayList<EnemyIsland>();
		EnemyIsland enemy = makeRandomEnemyIsland();
		enemyIslands.add(enemy);
	}
	
	public ArrayList<EnemyIsland> getEnemyIslands() {return enemyIslands;}
	
	public void Update(Graphics g, PlayerIslandManager pim) {
		if (Board.SCENE == 0) {
			
		} else if (Board.SCENE == 1) {
			if (enemyIslands.size() == 0) {
				enemyIslands.add(makeRandomEnemyIsland());
			}
			for (EnemyIsland enemy : enemyIslands) {
				enemy.Update(g, pim);
			}
		} else if (Board.SCENE == 2) {
			
		}
	}
	
	public EnemyIsland makeRandomEnemyIsland() {
		String imageName = BODY_IMAGES[(int)(Math.random() * BODY_IMAGES.length)];
		int level = (int)(Math.random() * 21);
		EnemyIsland enemy = new EnemyIsland(1200, 300, imageName, level);
		ArrayList<Building> eb = enemy.getBuildings();
		int[] gunPos = new int[Island.MAX_BUILDING_NUM];
		int gunCount = 0;
		while (gunCount < enemy.getLevel()) {
			int index = (int)(Math.random() * Island.MAX_BUILDING_NUM);
			if (gunPos[index] == 0) {
				gunPos[index] = 1;
				gunCount++;
			}
		}
		for (int i = 0; i < gunPos.length; i++) {
			if (gunPos[i] == 1) {
				Gun gNew = new Gun(Island.FIRST_BUILDING_POS[0] + 42 * i, Island.FIRST_BUILDING_POS[1], ImagePaths.GUN1_42x42, 10);
				eb.add(gNew);
			} else {
				int type = (int)(Math.random() * 2);
				if (type == 0) {
					ResourceProducer wNew = new ResourceProducer(Island.FIRST_BUILDING_POS[0] + 42 * i, Island.FIRST_BUILDING_POS[1], ImagePaths.WOOD_PRODUCER, "wood");
					eb.add(wNew);
				} else if (type == 1) {
					ResourceProducer mNew = new ResourceProducer(Island.FIRST_BUILDING_POS[0] + 42 * i, Island.FIRST_BUILDING_POS[1], ImagePaths.METAL_PRODUCER, "metal");
					eb.add(mNew);
				}
			}
		}
		return enemy;
	}
}
