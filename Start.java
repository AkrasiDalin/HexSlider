package HexSlider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * The Start class runs as main method
 */
public class Start {
	static HashMap<String, String> map;

	public static void main(String[] args) {
		new Communication();

	}

	/**
	 * Converts a given string to its hex form and returns it.
	 *
	 * @param string
	 * @return hex the hexadecimal value of the given String s.
	 */
	static String toHex(String string) {
		if (map == null)
			readFile();
		String hex = "";

		for (int i = 0; i < string.length(); i++) {
			String substring = string.substring(i, i + 1);
			hex += map.get(substring);
		}

		return hex;
	}

	/**
	 * Reads ASCII table and inputs the corresponding hex values into the
	 * HashMap.
	 */
	static void readFile() {
		map = new HashMap<>();
		String line;
		try (Scanner scanner = new Scanner(new FileInputStream("ascii_table.csv"))) {
			String[] hex;
			while (scanner.hasNext()) {
				line = scanner.nextLine();
				hex = line.split(",");

				map.put(hex[4], hex[2]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
