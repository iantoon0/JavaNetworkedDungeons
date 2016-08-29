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
		if (input.contains("className")){
			
		}
	}
	public void outputCampaign(Campaign c){
		for (Hero h : c.party){
			output.write(gson.toJson(h));
		}
	}
	public void processHero(String input){
		gson.fromJson(input, Hero.Class);
	}
}
