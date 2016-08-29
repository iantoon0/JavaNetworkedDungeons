package encounter;

import com.google.gson.*;

import encounter.Classes.*;

import java.io.*;

public class JavaNetworkDungeonsProtocol {
	
	Gson gson = new Gson();
	PrintWriter output;
	
	public JavaNetworkDungeonsProtocol(PrintWriter out){
		this.output = out;
	}
	
	public void processInput(String input){
		if (input.contains("className")){
			
		}
	}
	public void outputCampaign(Campaign c){
		for (Hero h : c.party){
			output.write(gson.toJson(h));
		}
	}
	public void processHero(String input){
		if (input.contains("Barbarian")){
			gson.fromJson(input, Barbarian.class);
		}
		if (input.contains("Bard")){
			gson.fromJson(input, Bard.class);
		}
		if (input.contains("Cleric")){
			gson.fromJson(input, Cleric.class);
		}
		if (input.contains("Druid")){
			gson.fromJson(input, Druid.class);
		}
		if (input.contains("Fighter")){
			gson.fromJson(input, Fighter.class);
		}
		if (input.contains("Monk")){
			gson.fromJson(input, Monk.class);
		}
		if (input.contains("Paladin")){
			gson.fromJson(input, Paladin.class);
		}
		if (input.contains("Ranger")){
			gson.fromJson(input, Ranger.class);
		}
		if (input.contains("Rogue")){
			gson.fromJson(input, Rogue.class);
		}
		if (input.contains("Sorcerer")){
			gson.fromJson(input, Sorcerer.class);
		}
		if (input.contains("Warlock")){
			gson.fromJson(input, Warlock.class);
		}
		if (input.contains("Wizard")){
			gson.fromJson(input, Wizard.class);
		}	
	}
}
