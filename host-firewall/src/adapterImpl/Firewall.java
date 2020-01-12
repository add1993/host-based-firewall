/**
 * 
 */
package adapterImpl;

/**
 * @author Ayush Dobhal
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import utility.IPv4AddressUtil;
import utility.Trie;

public class Firewall implements adapter.Firewall {
	private PacketParser packetParser;
	private List<String[]> rules;
	private Trie rulesTrie = new Trie();
	
	public Firewall(String path) throws Exception {
		packetParser = new PacketParser(path);
		this.rules = packetParser.getRules();
		long count = rulesTrie.insertRule(rules);
	}
	
	/**
	 * @param direction, protocol, port, ip_address
	 * @apiNote Checks the packet for validity
	 */
	@Override
	public boolean accept_packet(String direction, String protocol, int port, String ip_address) {
		// TODO Auto-generated method stub
		StringBuilder encoded = new StringBuilder();
		encoded.append(direction);
		encoded.append(protocol);
		encoded.append(port);
		encoded.append(ip_address);
		return rulesTrie.search(encoded.toString());
	}
	
}
