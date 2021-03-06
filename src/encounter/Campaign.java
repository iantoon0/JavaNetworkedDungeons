package encounter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class Campaign {
	public ArrayList<Hero> listParty;
	public ArrayList<Spell> listActiveSpells; 
	public HashMap<String, ArrayList<TextMessage>> dictChatLog;
	public Time currentTime;
	public boolean bInEncounter;
	public ArrayList<Dungeon> listDungeons;
	public Dungeon currentDungeon;
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
	public void updateChatLog(TextMessage message){
		if(dictChatLog.containsKey(message.sLanguage)){
			dictChatLog.get(message.sLanguage).add(message);
		}
		else{
			ArrayList<TextMessage> messageArray = new ArrayList<TextMessage>();
			messageArray.add(message);
			dictChatLog.put(message.sLanguage, messageArray);
		}
	}
}