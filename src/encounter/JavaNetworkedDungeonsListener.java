package encounter;

import java.io.IOException;
import java.net.ServerSocket;

public class JavaNetworkedDungeonsListener extends Thread {

	Campaign c;
	
	public JavaNetworkedDungeonsListener(Campaign c){
		this.c = c;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		ServerSocket serversocket;
		while(true){
			try {
				int portNumber = 556;
				serversocket = new ServerSocket(portNumber);
				System.out.println(serversocket.getLocalPort());
				ClientThread clientThread = new ClientThread(serversocket.accept(), c);
				serversocket.close();
				System.out.print("Sleeping");
				sleep(1000);
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
