package encounter;

import com.google.gson.*;

import encounter.Classes.*;

import java.io.*;

public class JavaNetworkDungeonsProtocol {
	
	Gson gson = new Gson();
	PrintWriter output;
	Campaign c;
	
	public JavaNetworkDungeonsProtocol(PrintWriter out, Campaign C){
		this.output = out;
		this.c = c;
	}
	
	public void processInput(String input, BufferedReader in) throws IOException{
		/*
		if (input.contains("className")){
			processHero(input);
		}
		*/
		if(input.contains("chat")){//recieved as "chat:speaker,language,contents"
			String sMessage = input.substring(5);
			String speaker = sMessage.substring(0, sMessage.indexOf(","));
			String notSpeaker = sMessage.substring(sMessage.indexOf(","));
			String language = notSpeaker.substring(0, sMessage.indexOf(","));
			String contents = notSpeaker.substring(sMessage.indexOf(","));
			TextMessage message = new TextMessage(speaker, language, contents);
			c.updateChatLog(message);
		}
		if(input.contains("party")){
			c = gson.fromJson(input, Campaign.class);
		}
		else if(input.contains("encounterActors")){
			
		}
		else if(input.equals("Cmd:CreateHero")){
			String HeroString = in.readLine();
			
		}
		else if(input.equals("Cmd:")){
			
		}
	}
	public EncounterActor CreateActorFromJson(String s){
		EncounterActor rtn = null;
		if (s.contains("className")){
			if (s.contains("\"className\":\"Barbarian\"")){
				rtn = gson.fromJson(s, Barbarian.class);
			}
			else if (s.contains("\"className\":\"Bard\"")){
				rtn = gson.fromJson(s, Bard.class);
			}
			else if (s.contains("\"className\":\"Cleric\"")){
				rtn = gson.fromJson(s, Cleric.class);
			}
			else if (s.contains("\"className\":\"Druid\"")){
				rtn = gson.fromJson(s, Druid.class);
			}
			else if (s.contains("\"className\":\"Fighter\"")){
				rtn = gson.fromJson(s, Fighter.class);
			}
			else if (s.contains("\"className\":\"Monk\"")){
				rtn = gson.fromJson(s, Monk.class);
			}
			else if (s.contains("\"className\":\"Paladin\"")){
				rtn = gson.fromJson(s, Paladin.class);
			}
			else if (s.contains("\"className\":\"Ranger\"")){
				rtn = gson.fromJson(s, Ranger.class);
			}
			else if (s.contains("\"className\":\"Rogue\"")){
				rtn = gson.fromJson(s, Rogue.class);
			}
			else if (s.contains("\"className\":\"Sorcerer\"")){
				rtn = gson.fromJson(s, Sorcerer.class);
			}
			else if (s.contains("\"className\":\"Warlock\"")){
				rtn = gson.fromJson(s, Warlock.class);
			}
			else if (s.contains("\"className\":\"Wizard\"")){
				rtn = gson.fromJson(s, Wizard.class);
			}
		}
		return rtn;
	}
	public void outputCampaign(Campaign c){
		output.println(gson.toJson(c));
	}
	public void outputEncounter(Encounter e){
		output.println(gson.toJson(e));
	}
}