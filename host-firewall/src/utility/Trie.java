/**
 * 
 */
package utility;

/**
 * @author Ayush Dobhal
 *
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TrieNode {
	char c;
	boolean isLeaf;
	HashMap<Character, TrieNode> children;

	public TrieNode() {
		children  = new HashMap<Character, TrieNode>();
	}

	public TrieNode(char c) {
		this.c = c;
		children  = new HashMap<Character, TrieNode>();
	}
}

public class Trie {
	private TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	/**
	 * @param word
	 * @apiNote Inserts data into Trie Tree
	 */
	public void insert(String word) {
		HashMap<Character, TrieNode> children = root.children;

		TrieNode node = new TrieNode();
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			
			if (children.containsKey(ch)) {
				node = children.get(ch);
			} else {
				node = new TrieNode(ch);
				children.put(ch, node);
			}
			children = node.children;
		}
		node.isLeaf = true;
	}

	/**
	 * @param word
	 * @apiNote Searches for the word in the trie tree and 
	 * 			return true/false
	 */
	public boolean search(String word) {
		TrieNode node = searchNode(word);

		if (node != null && node.isLeaf) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param str
	 * @apiNote Traverses the trie tree to search for str.
	 * 			Returns TrieNode corresponding to str
	 */
	public TrieNode searchNode(String str) {
		Map<Character, TrieNode> children = root.children;
		TrieNode node = null;
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (children.containsKey(ch)) {
				node = children.get(ch);
				children = node.children;
			} else {
				return null;
			}
		}
		return node;
	}
	
	/**
	 * @param List of rules
	 * @apiNote Inserts the rules in the trie tree
	 */
	public long insertRule(List<String[]> rules) throws Exception {
		String direction;
		String protocol;
		String portRange;
		String ipRange;
		String IPStart;
		String IPEnd;
		String currentIP;
		String[] portArr;
		String[] IPArr;
		
		int portStart;
		int portEnd;
		long count = 0;
		for (String[] rule : rules) {
			int N = rule.length;
			if (N == 4) {
				direction = rule[0];
				protocol = rule[1];
				portRange = rule[2];
				ipRange = rule[3];
				portArr = portRange.split("-");
				IPArr = ipRange.split("-");
				portStart = Integer.parseInt(portArr[0]);
				portEnd = portStart;
				
				if (portArr.length > 1) {
					portEnd = Integer.parseInt(portArr[1]);
				}
				
				IPStart = IPArr[0];
				IPEnd = IPStart;
				if (IPArr.length > 1) {
					IPEnd = IPArr[1];
				}
				
				currentIP = IPStart;
				for (int ip = portStart; ip <= portEnd; ip++) {
					while (IPv4AddressUtil.isValidRange(IPStart, IPEnd, currentIP)) {
						StringBuilder encoded = new StringBuilder();
						encoded.append(direction.trim());
						encoded.append(protocol.trim());
						encoded.append(ip);
						encoded.append(currentIP.trim());
						this.insert(encoded.toString());
						currentIP = IPv4AddressUtil.getNextIPAddress(currentIP);
						count = count + 1;
					}
					currentIP = IPStart;
				}
			} else {
				System.out.println("Given rule is invalid.");
				continue;
			}
		}
		return count;
	}
}
