package encounter;

import com.google.gson.*;

import java.io.*;

public class JavaNetworkDungeonsProtocol {
	
	Gson gson = new Gson();
	PrintWriter output;
	
	public JavaNetworkDungeonsProtocol(PrintWriter out){
		this.output = out;
	}
	
	public void processInput(String input){
		/*
		if (input.contains("className")){
			processHero(input);
		}
		*/
		if(input.contains("party")){
			gson.fromJson(input, Campaign.class);
		}
		else if(input.contains("encounterActors")){
			
		}
	}
	public void outputCampaign(Campaign c){
		output.write(gson.toJson(c));
	}
	public void outputEncounter(Encounter e){
		output.write(gson.toJson(e));
	}
}