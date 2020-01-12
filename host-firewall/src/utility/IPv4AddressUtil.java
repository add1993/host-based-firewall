package utility;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Ayush Dobhal
 *
 */

public class IPv4AddressUtil {

	/**
	 * @param ip
	 * @apiNote Convert the given ip address to long format
	 */
	private static long convertIPToLong(InetAddress ip) {
		byte[] octets = ip.getAddress();
		long result = 0;
		for (byte oct : octets) {
			result <<= 8;
			result |= oct & 0xff;
		}
		return result;
	}

	/**
	 * @param ipStart, ipEnd, currentIP
	 * @apiNote Checks if the given IP range is correct or not
	 */
	public static boolean isValidRange(String ipStart, String ipEnd, String currentIP) {
		try {
			long low = convertIPToLong(InetAddress.getByName(ipStart));
			long high = convertIPToLong(InetAddress.getByName(ipEnd));
			long givenIP = convertIPToLong(InetAddress.getByName(currentIP));
			return (givenIP >= low && givenIP <= high);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @param ipAddress
	 * @apiNote Given an IP address return the next ip address
	 */
	public static String getNextIPAddress(String ipAddress) throws Exception {
		String nextIPAddress = "";
		String[] elements = ipAddress.split("\\.");
		if (elements != null && elements.length == 4) {
			Integer ipPart1 = Integer.parseInt(elements[0]);
			Integer ipPart2 = Integer.parseInt(elements[1]);
			Integer ipPart3 = Integer.parseInt(elements[2]);
			Integer ipPart4 = Integer.parseInt(elements[3]);

			if (ipPart4 < 255) {
				nextIPAddress = ipPart1 + "." + ipPart2 + "." + ipPart3 + "." + (++ipPart4);
				return nextIPAddress;
			} else if (ipPart4 == 255) {
				if (ipPart3 < 255) {
					nextIPAddress = ipPart1 + "." + ipPart2 + "." + (++ipPart3) + "." + (0);
					return nextIPAddress;
				} else if (ipPart3 == 255) {
					if (ipPart2 < 255) {
						nextIPAddress = ipPart1 + "." + (++ipPart2) + "." + (0) + "." + (0);
						return nextIPAddress;
					} else if (ipPart2 == 255) {
						if (ipPart1 < 255) {
							nextIPAddress = (++ipPart1) + "." + (0) + "." + (0) + "." + (0);
							return nextIPAddress;
						} else if (ipPart1 == 255) {
							throw new Exception("IP Range Exceeded " + ipAddress);
						}
					}
				}
			}
		}

		return null;
	}
}
