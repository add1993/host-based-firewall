/**
 * 
 */
package adapter;

/**
 * @author Ayush Dobhal
 * @apiNote Firewall Interface
 */
public interface Firewall {
	public boolean accept_packet(String direction, String protocol, int port, String ip_address)  throws Exception;
}
