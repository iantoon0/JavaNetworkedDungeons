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
		HashMap<EncounterActor, Socket> actorSocketMap = new HashMap<EncounterActor, Socket>();
		JavaNetworkedDungeonsListener jndl = new JavaNetworkedDungeonsListener(actorSocketMap, c);
		jndl.start();
		PrintWriter pw = new PrintWriter(System.out);
		JavaNetworkDungeonsProtocol jndp = new JavaNetworkDungeonsProtocol(pw,c);
		Hero h = new Monk("tiefling");
		h.featsMap.put("truesight30",true);
		c.party.add(h);
		Gson gson = new Gson();
		System.out.println(gson.toJson(c));
		jndp.outputCampaign(c);
		Dungeon d = new Dungeon(40);
		d.dungeonMap.get(8).get(8).encounterActor = h;
		d.print();
		d.updateVisibleTiles();
		d.print();
	}
}