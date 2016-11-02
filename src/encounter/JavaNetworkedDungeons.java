package encounter;

import java.util.*;

import com.google.gson.Gson;

import java.io.*;
import java.net.*;
import encounter.Classes.*;

public class JavaNetworkedDungeons {
	public static void main(String[] args) throws UnknownHostException{
		// TODO Auto-generated method stub
		Campaign c = new Campaign();
		Hero h = new Monk("tiefling");
		c.listParty.add(h);
		Gson gson = new Gson();
		System.out.println(gson.toJson(c));
		Dungeon d = new Dungeon(40);
		c.currentDungeon = d;
		d.dungeonMap.get(8).get(8).encounterActor = h; //Adds a player who has darkvision to the map
		d.dungeonMap.get(8).get(14).lightSources.add(new LightSource(60,0));//Adds a new light source with strength 60
		d.dungeonMap.get(10).get(18).bWall = true;//*************************
		d.dungeonMap.get(11).get(18).bWall = true;//**Creates a 3 by 1 wall**
		d.dungeonMap.get(12).get(18).bWall = true;//*************************
		try {
			d.determineLightLevels();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("");
		System.out.println(InetAddress.getLocalHost().getHostAddress());
		HashMap<EncounterActor, Socket> actorSocketMap = new HashMap<EncounterActor, Socket>();
		JavaNetworkedDungeonsListener jndl = new JavaNetworkedDungeonsListener(actorSocketMap, c);
		jndl.start();
		PrintWriter pw = new PrintWriter(System.out);
		JavaNetworkDungeonsProtocol jndp = new JavaNetworkDungeonsProtocol(pw,c);
	}
}