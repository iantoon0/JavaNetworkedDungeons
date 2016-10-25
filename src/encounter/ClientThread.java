package encounter;

import java.net.*;
import java.util.ArrayList;

import com.google.gson.Gson;

import java.io.*;

public class ClientThread extends Thread {
	
	Socket clientSocket;
	Campaign c;
	Gson gson = new Gson();
	
	public ClientThread(Socket clientSocket, Campaign c){
		this.clientSocket = clientSocket;
		this.c = c;
		System.out.println("Created new ClientThread!");
	}
	public void run(){
		try {
			PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String writeString = new String();
			ArrayList<String> temp = new ArrayList<String>();
			temp.add("Player"); temp.add("DM");
			Prompt p = new Prompt("Are you a player or a DM?", temp, 1);
			writeString = gson.toJson(p);
			pw.write(writeString + "<EOF>");
			while(!br.ready()){
				try {
					synchronized(this) {
				        this.wait(10);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			JavaNetworkDungeonsProtocol jndp = new JavaNetworkDungeonsProtocol(pw,c);
			String inputLine;
			while (true){
				while ((inputLine = br.readLine()) != null) {
			        jndp.processInput(inputLine, br);
				}
				jndp.outputCampaign(c);
				sleep(250);
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
