package encounter;

import java.net.*;
import java.util.ArrayList;
import java.util.zip.GZIPOutputStream;

import com.google.gson.Gson;

import java.io.*;

public class ClientThread extends Thread {
	
	Socket clientSocket;
	Campaign c;
	Gson gson = new Gson();
	boolean bPlayerIsDM;
	
	public ClientThread(Socket clientSocket, Campaign c){
		this.clientSocket = clientSocket;
		this.c = c;
		System.out.println("Created new ClientThread!");
	}
	public static String compress(String str) throws Exception {
		if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream obj=new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(obj);
        gzip.write(str.getBytes());
        gzip.close();
        String outStr = obj.toString();
        System.out.println("Output String length : " + outStr.length());
        return outStr;
     }
	public void run(){
		try {
			PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String writeString = new String();
			
			ArrayList<String> temp = new ArrayList<String>();
			//temporary array list to hold Player or String
			temp.add("Player"); 
			temp.add("DM");
			Prompt p = new Prompt("Are you a player or a DM?", temp, 1);
			writeString = gson.toJson(p) + "<EOF>";
			//writeString = compress(writeString);
			pw.println(writeString);
			System.out.println("Wrote " + writeString + " to client");
			while(! br.ready()){
				sleep(500);
				System.out.println("Sleeping...");
			}
			JavaNetworkDungeonsProtocol jndp = new JavaNetworkDungeonsProtocol(pw,c);
			System.out.println("Reading response...");
			String inputLine = br.readLine();
			System.out.println("Response:" + inputLine);
			bPlayerIsDM = (inputLine == "DM");
			jndp.outputCampaign(c);
			while (true){
				while (br.ready()) {
					inputLine = br.readLine();
			        jndp.processInput(inputLine, br);
					System.out.println("Processed input!");
				}
				
				//System.out.println("Wrote Campaign to player");
				sleep(3000);
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
