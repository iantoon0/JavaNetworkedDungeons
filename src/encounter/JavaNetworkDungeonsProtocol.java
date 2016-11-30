package encounter;

import com.google.gson.*;

import encounter.sendableObjects.SendableCampaign;

import java.io.*;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPOutputStream;

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
		if(input.contains("chat")){//received as "chat:speaker,language,contents"
			String sMessage = input.substring(5);
			String speaker = sMessage.substring(0, sMessage.indexOf(","));
			String notSpeaker = sMessage.substring(sMessage.indexOf(","));
			String language = notSpeaker.substring(0, sMessage.indexOf(","));
			String contents = notSpeaker.substring(sMessage.indexOf(","));
			TextMessage message = new TextMessage(speaker, language, contents);
			c.updateChatLog(message);
		}
		else if(input.contains("party")){
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
			rtn = gson.fromJson(s, Hero.class);
		}
		return rtn;
	}
	public void outputCampaign(Campaign c, boolean bPlayerIsDM){
		
		try {
			output.println(/*compress*/(gson.toJson(new SendableCampaign(c, bPlayerIsDM, null)) + "<EOF>"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void outputDungeon(Dungeon d){
		output.println("Sending Dungeon of size: " + d.size);
		
	}
	public void outputEncounter(Encounter e){
		output.println(gson.toJson(e));
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
}