/**
 * 
 */
package adapterImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import adapter.FileParser;

/**
 * @author Ayush Dobhal
 *
 */
public class PacketParser implements FileParser {
	private File file;
	private List<String[]> rules = new ArrayList<String[]>();
	
	public PacketParser(String path) {
		this.file = new File(path);
		this.parseFile();
	}
	
	/**
	 * @param None
	 * @apiNote Parse the given input file and populate the rules in rules list
	 */
	public void parseFile() {
		try {
			Scanner inputStream = new Scanner(file);

			while (inputStream.hasNext()) {
				String data = inputStream.next();
				String[] values = data.split(",");
				rules.add(values);
			}
			inputStream.close();
			System.out.println("Total Rules in Files: " + rules.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param None
	 * @apiNote Return the rules list parsed from the file
	 */
	public List<String[]> getRules() {
		return rules;
	}
}
