# Host-Based-Firewall

* Given a set of firewall rules, a network packet will be accepted by the firewall if and only if the direction, protocol, port, and IP address match at least one of the input rules. If a rule contains
a port range, it will match all packets whose port falls within the range. If a rule contains an IP
address range, it will match all packets whose IP address falls within the range.

* The project consists of mainly 4 packages and 6 classes and 2 interface.
*	**1:** adapter: Contains the interfaces that will be implemented.

*	**2:** adapterImpl: Contains the implementation of the interfaces defined in adapter.

*	**3:** Utility: Contains some helper classes like Trie and IPv4AddressUtility to help with implementation of logic

*	**4:** test: Contains junit test cases for testing of the implementation.

## Classes

### Firewall
- This is the central class which follows the interface as provided in programming assignment. This class takes input of a file path and then calls the file parser to get the rules and then stores them in a trie tree.

- **accept_packet(String direction, String protocol, int port, String ip_address):** This method based on input checks if it matches with any of the given rules from input file.

### PacketParser
- This class is used to parse the given packet rules file and extract the rules. These rules are then further used to verification of a packet.

### IPv4AddressUtil
- This class is a utility for IP address validation and well as getting the next address from a given address.

### Trie
- This class stores all the rules in it's nodes and help in saving space and giving a faster search time.
- Time Complexity: Search : O(k)
- Time Complexity: Insert : O(k) 
where k is the length of the input

### Testing the code 
- I have added a JUnit test class to test my code as well as a main class to run some testcases.

### Project Design
- I chose the adapter design pattern to create implement the project as it gives scalability to the project. We can add other Firewall also in the future.
- I chose trie tree to store the rule data as we can have a lot of data and trie's faster search and optimized space complexity will help in scaling the project. 

### Future Optimizations
- We can add POJO objects instead of using arrays for rules and ip addresses. 
- We can try to optimize the trie tree to save further space to help scale better.
- We can make the implemented functions synchronized to support multithreading.

