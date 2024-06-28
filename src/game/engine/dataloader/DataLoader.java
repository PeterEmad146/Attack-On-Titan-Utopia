package game.engine.dataloader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import game.engine.exceptions.InvalidCSVFormat;
import game.engine.titans.TitanRegistry;
import game.engine.weapons.WeaponRegistry;

/**
 * 4.15 Milestone 1
 * A class that is used to import data from a given csv file.
 * The imported data is then used to create and initialize registries for Titans and Weapons
 * and fill them with the entries from the csv file. 
 * The read file should be placed in the same directory as the .src folder and not inside it.
 * @author Peter Emad, Mark Fahim, Ahmed Sheta
 *
 */
public class DataLoader {

	// class attributes
	private final static String TITANS_FILE_NAME = "titans.csv";
	private final static String WEAPONS_FILE_NAME = "weapons.csv";
	
	// methods
	public static String getTitansFileName() {
		return TITANS_FILE_NAME;
	}
	public static String getWeaponsFileName() {
		return WEAPONS_FILE_NAME;
	}		
	
	/**
	 * Read the TITANS_FILE_NAME CSV file using BufferedReader
	 * and creates a TitanRegistry with the values, then loads it into the hashmap alongside it's code.
	 * 
	 * The line represents the titans' data as follows, code, baseHealth, baseDamage, heightInMeters, speed, resourcesValue, dangerLevel.
	 * 
	 * @return Hashmap consisting of 4 titan registries read from the csv file.
	 * @throws IOException throws exception if the file name was not found.
	 */
	public static HashMap<Integer, TitanRegistry> readTitanRegistry() throws IOException{
		HashMap<Integer, TitanRegistry> titanRegistryMap = new HashMap<>();
		BufferedReader br = new BufferedReader(new FileReader(TITANS_FILE_NAME));
		while (br.ready()) {
			String nextLine = br.readLine();
			String[] data = nextLine.split(",");
			if (data.length != 7) {
				throw new InvalidCSVFormat(nextLine);
			}
			TitanRegistry reg = new TitanRegistry(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]), Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]));
			titanRegistryMap.put(reg.getCode(), reg);
		}
		br.close();
		return titanRegistryMap;
	}

	/**
	 * Reads the WEAPONS_FILE_NAME CSV file using BufferedReader
	 * and creates a WeaponRegistry with the values, then loads it into the hasmap alongside it's code.
	 * 
	 * The line represents the weapons' data as follows: code, price, damage, name -or- code, price, damage, name, minRange, maxRange
	 * based on the type of weapon (minRange and maxRange are added only if the weapon type is VolleySpreadCannon).
	 * 
	 * @return Hashmap consisting of 4 weapon registries read from the csv file.
	 * @throws IOException throws exception if the file name was not found.
	 */
	public static HashMap<Integer, WeaponRegistry> readWeaponRegistry() throws IOException {
		HashMap<Integer, WeaponRegistry> weaponRegistryMap = new HashMap<>();
		BufferedReader br = new BufferedReader(new FileReader(WEAPONS_FILE_NAME));
		while (br.ready()) {
			String nextLine = br.readLine();
			String[] data = nextLine.split(",");
			WeaponRegistry reg;
			if (data.length != 6 && data.length != 4) {
				throw new InvalidCSVFormat(nextLine);
			}
			if (data.length == 6) {
				reg = new WeaponRegistry(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]), data[3], Integer.parseInt(data[4]), Integer.parseInt(data[5]));
			} 
			else {
				reg = new WeaponRegistry(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]), data[3]);
			}
			weaponRegistryMap.put(reg.getCode(), reg);
		}
		br.close();
		return weaponRegistryMap;
	}
	
}
