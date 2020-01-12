/**
 * 
 */
package adapterImpl;

/**
 * @author Ayush Dobhal
 *
 */
public class MainDriver {

	/**
	 * @param args
	 * @apiNote Main function to test the code
	 */
	public static void main(String[] args) {

		try {
			Firewall fw = new Firewall("src/rules.csv");

			System.out.println("Sample Testing");
			System.out.println(fw.accept_packet("inbound", "tcp", 80, "192.168.1.2"));
			System.out.println(fw.accept_packet("inbound", "udp", 53, "192.168.2.1"));
			System.out.println(fw.accept_packet("outbound", "tcp", 10234, "192.168.10.11"));
			System.out.println(fw.accept_packet("outbound", "udp", 65535, "255.255.255.255"));
			System.out.println(fw.accept_packet("outbound", "udp", 1, "255.255.255.255"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
