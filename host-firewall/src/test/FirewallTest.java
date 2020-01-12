package test;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import adapterImpl.Firewall;

import static org.junit.Assert.assertEquals;

/**
 * @author Ayush Dobhal
 *
 */

public class FirewallTest {
    Firewall firewall;

    @Before
    public void setUp() throws IOException {
        try {
			firewall = new Firewall("src/rules.csv");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test
    public void test1() {
        assertEquals(true, firewall.accept_packet("inbound", "udp", 53, "192.168.2.1"));
    }

    @Test
    public void test2() {
        assertEquals(true, firewall.accept_packet("outbound", "tcp", 10234, "192.168.10.11"));
    }

    @Test
    public void test3() {
        assertEquals(false, firewall.accept_packet("inbound", "tcp", 81, "192.168.1.2"));
    }

    @Test
    public void test4() {
        assertEquals(false, firewall.accept_packet("inbound", "udp", 24, "52.12.48.92"));
    }
}

