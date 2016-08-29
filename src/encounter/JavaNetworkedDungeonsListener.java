package encounter;

import java.io.IOException;
import java.net.ServerSocket;

public class JavaNetworkedDungeonsListener implements Runnable {

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
				int portNumber = 555;
				serversocket = new ServerSocket(portNumber);
				System.out.println(serversocket.getLocalPort());
				ClientThread clientThread = new ClientThread(serversocket.accept(), c);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
