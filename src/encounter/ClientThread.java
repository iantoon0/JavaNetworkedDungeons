package encounter;

import java.net.*;
import java.io.*;

public class ClientThread extends Thread {
	
	Socket clientSocket;
	Campaign c;
	
	public ClientThread(Socket clientSocket, Campaign c){
		this.clientSocket = clientSocket;
		this.c = c;
	}
	public void run(){
		try {
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			JavaNetworkDungeonsProtocol jndp = new JavaNetworkDungeonsProtocol(out);
			String inputLine;
			while (true){
				while ((inputLine = in.readLine()) != null) {
			        jndp.processInput(inputLine);
				}
				jndp.outputCampaign(c);
				sleep(1);
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
