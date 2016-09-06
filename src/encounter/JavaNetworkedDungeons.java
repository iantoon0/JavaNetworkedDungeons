package encounter;

import java.util.*;

import com.google.gson.Gson;

import java.io.*;
import java.net.*;
import encounter.Classes.*;

public class JavaNetworkedDungeons {
	public static void main(String[] args){
		// TODO Auto-generated method stub
		Campaign c = new Campaign();
		System.out.println("Finished");
		JavaNetworkedDungeonsListener jndl = new JavaNetworkedDungeonsListener(c);
		jndl.start();
		System.out.println("Finished2");
		PrintWriter pw = new PrintWriter(System.out);
		JavaNetworkDungeonsProtocol jndp = new JavaNetworkDungeonsProtocol(pw);
		Hero h = new Monk("tiefling");
		c.party.add(h);
		Gson gson = new Gson();
		System.out.println(gson.toJson(c));
	}
}