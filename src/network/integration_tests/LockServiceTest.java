package network.integration_tests;

import java.io.IOException;

import network.client.NetworkClient;
import network.server.Coordinator;

/**
 * This is an integration test that runs multiple clients on the 
 * same node to serialize their requests for lock and unlock, to test
 * if the behavior is the same as defined by the API.
 * 
 * @author CharlesXu
 */
public class LockServiceTest {
	
	public static final int PORT = 9999;
	public static final int DELAY_MILLIS = 1000;
	
	public static final String USER_A = "User A";
	public static final String USER_B = "User B";
	public static final String USER_C = "User C";
	public static final Long ID_1 = 1L;
	
	public static void main(String[] args) {
		try {
			Coordinator cor = new Coordinator(PORT);
			NetworkClient c1 = new NetworkClient(USER_A);
			NetworkClient c2 = new NetworkClient(USER_B);
			String holder = c1.tryLock(ID_1);
			System.out.println("acquire successfully: " + holder);
			Thread.sleep(DELAY_MILLIS);
			holder = c2.tryLock(ID_1);
			System.out.println(USER_A + " still holds the lock: " + holder);
			c1.unlock(ID_1);
			Thread.sleep(DELAY_MILLIS);
			holder = c2.tryLock(ID_1);
			System.out.println(USER_B + " should now be able to hold the lock: " + holder);
			System.out.println("Should get 1000: " + c1.getNextSequenceNumber());
			cor.shutdown();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
