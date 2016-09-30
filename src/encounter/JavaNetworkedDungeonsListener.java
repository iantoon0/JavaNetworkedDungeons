package encounter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;

import com.google.gson.Gson;

public class JavaNetworkedDungeonsListener extends Thread {

	Campaign c;
	HashMap<EncounterActor, Socket> actorSocketMap = new HashMap<EncounterActor, Socket>();

	
	public JavaNetworkedDungeonsListener(HashMap<EncounterActor, Socket> actorSocketMap, Campaign c){
		this.c = c;
		this.actorSocketMap = actorSocketMap;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		ServerSocket serversocket;
		Gson gson = new Gson();
		while(true){
			try {
				int portNumber = 555;
				serversocket = new ServerSocket(portNumber);
				System.out.println(serversocket.getLocalPort());
				Socket clientSocket = serversocket.accept();
				ClientThread clientThread = new ClientThread(clientSocket, c);
				EncounterActor actorFromThread = null;
				boolean b = true;
				JavaNetworkDungeonsProtocol jndp = new JavaNetworkDungeonsProtocol(null,null);
				while (b){
					actorFromThread = jndp.CreateActorFromJson(new BufferedReader( new InputStreamReader(clientSocket.getInputStream())).readLine());
					if(actorFromThread.getClass().equals(Hero.class))
					{
						c.listParty.add((Hero) actorFromThread);
						b = false;
					}
				}
				actorSocketMap.put(actorFromThread, clientSocket);
				clientThread.run();
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
