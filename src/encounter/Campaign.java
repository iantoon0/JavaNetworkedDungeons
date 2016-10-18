package encounter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class Campaign {
	public ArrayList<Hero> listParty;
	public ArrayList<Spell> listActiveSpells; 
	public Time currentTime;
	boolean bInEncounter;
	public Campaign(){
		listParty = new ArrayList<Hero>();
	}
	public void shortRest(HashMap<EncounterActor, Socket> actorSocketMap){
		currentTime.iHour++;
		currentTime.fixTime();
		for(Hero h : listParty){
			h.shortRest(actorSocketMap.get(h));
		}
		//calculate exhaustion levels & such
	}
	public void longRest(HashMap<EncounterActor, Socket> actorSocketMap){
		currentTime.iHour += 8;
		currentTime.fixTime();
		for(Hero h : listParty){
			h.longRest(actorSocketMap.get(h));
		}
		//calculate exhaustion levels & such
	}
}