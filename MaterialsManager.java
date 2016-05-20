import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class MaterialsManager {
	private static String PLAYER_MATERIALS_FILE = "playerMaterialsData.txt";
	private static String FOLDER = "Player";
	private static HashMap<String, Integer> materials = new HashMap<String, Integer>();

	public int getResource(String res) {
		res = res.toLowerCase();

		if (!materials.containsKey(res)) {
			System.out.println("MaterialsManager: Resource \"" + res
					+ "\" does not exist.");
			return -1;
		}
		return materials.get(res);
	}

	public void setResource(String res, Integer val) {
		res = res.toLowerCase();
		materials.put(res, val);
	}

	public void saveMaterials() {
		File folder = new File(FOLDER);

		if (!folder.exists()) {
			folder.mkdir();
		}

		File f = new File(folder + "/" + PLAYER_MATERIALS_FILE);

		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to create new materials save file.");
		}

		try {
			FileOutputStream fout = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(materials);
			oos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Failed to write materials to file.");
		}
	}

	@SuppressWarnings("unchecked")
	public void loadMaterials() {
		try {
			FileInputStream fin = new FileInputStream(FOLDER + "/"
					+ PLAYER_MATERIALS_FILE);
			ObjectInputStream ois = new ObjectInputStream(fin);
			materials = (HashMap<String, Integer>) ois.readObject();
			ois.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("MaterialsManager: Something done messed up.");
		}
	}

	@Override
	public String toString() {
		String ret = "";
		for (String s : materials.keySet()) {
			ret += s + " " + materials.get(s) + "\n";
		}
		return ret;
	}

}
