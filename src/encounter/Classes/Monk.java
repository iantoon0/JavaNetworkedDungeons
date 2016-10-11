package encounter.Classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import encounter.EncounterActor;
import encounter.Hero;

public class Monk extends Hero {
	int iMaxKi, iCurrentKi, iMonkDie, iUnarmoredSpeed;
	String sPath;//0 - Way of 
	public Monk(){
		sClassName = "Monk";
		bSpellcaster = false;
		iMaxKi = 0;
		iCurrentKi = 0;
		iMonkDie = 4;
		iMaxHP = 8 + iConMod;
		dictFeats.put("Unarmored Defense", true);
		dictFeats.put("Martial Arts", true);
	}
	public Monk(String r){
		super(r);
		sClassName = "Monk";
		bSpellcaster = false;
		iMaxKi = 0;
		iCurrentKi = 0;
		iMonkDie = 4;
		iMaxHP = 8 + iConMod;
		dictFeats.put("Unarmored Defense", true);
		dictFeats.put("Martial Arts", true);
	}
	public void levelUp(Socket actorSocket) throws IOException{
		super.levelUp(actorSocket);
		PrintWriter pw = null;
		BufferedReader br = null;
		try {
			pw = new PrintWriter(actorSocket.getOutputStream());
			br = new BufferedReader( new InputStreamReader(actorSocket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		iMaxKi = iLevel;
		switch (iLevel){
		case 2: dictFeats.put("Unarmored Movement", true); iUnarmoredSpeed = 10;
			break;
		case 3: /*Prompt: Choose path*/
			ArrayList<String> tempArrayList = new ArrayList<String>(); tempArrayList.add("Way of the Open Hand"); tempArrayList.add("Way of Shadows"); tempArrayList.add("Way of the Four Elements");
			prompt(pw, br, "Choose A Path", tempArrayList, 1);
			dictFeats.put("Deflect Missiles", true); 
			break;
		case 4: dictFeats.put("Slow Fall", true);
			break;
		case 5: dictFeats.put("Extra Attack", true); dictFeats.put("Stunning Strike", true); iMonkDie = 6; 
			break;
		case 6: dictFeats.put("Ki-Empowered Strikes", true); iUnarmoredSpeed = 15;
			break;
		case 7: dictFeats.put("Evasion", true); dictFeats.put("Stillness of Mind", true);
			break;
		case 8: break;
		case 9: break;
		case 10: dictFeats.put("Purity of Body", true); iUnarmoredSpeed = 20; 
			break;
		case 11: iMonkDie = 8; 
			break;
		case 12: break;
		case 13: dictFeats.put("Tongue of the Sun And Moon", true);
			break;
		case 14: iUnarmoredSpeed = 25;
			break;
		case 15: dictFeats.put("Timeless Body", true);
			break;
		case 16: break;
		case 17: iMonkDie = 10; 
			break;
		case 18: iUnarmoredSpeed = 30; dictFeats.put("Empty Body", true);
			break;
		case 19: break;
		case 20: dictFeats.put("Perfect Self", true);
			break;
		}
		switch (sPath){
		case "Way of the Open Hand":
			switch (iLevel){
			case 3: dictFeats.put("Open Hand Technique", true);
				break;
			case 6: dictFeats.put("Wholeness of Body", true);
				break;
			case 11: dictFeats.put("Tranquility", true);
				break;
			case 17: dictFeats.put("Quivering Palm", true);
				break;
			}
			break;
		case "Way of Shadow": 
			switch (iLevel){
			case 3: dictFeats.put("Shadow Arts", true);
				break;
			case 6: dictFeats.put("Shadow Step", true); 
				break;
			case 11: dictFeats.put("Cloak of Shadows", true);
				break;
			case 17: dictFeats.put("Opportunist", true);
				break;
			}
			break;
		case "Way of the Four Elements": 
			switch (iLevel){
			case 3: dictFeats.put("Elemental Attunement", true);
				ArrayList<String> tempArrayList = new ArrayList<String>(); 
				tempArrayList.add("Fangs of the Fire Snake"); tempArrayList.add("Fist of Four Thunders"); 
				tempArrayList.add("Fist of Unbroken Air"); tempArrayList.add("Rush of the Gale Spirits");
				tempArrayList.add("Shape the Flowing River"); tempArrayList.add("Sweeping Cinder Strike");
				tempArrayList.add("Water Whip");
				prompt(pw, br, "Choose an Elemental Discipline", tempArrayList, 1);
				break;
			case 6: tempArrayList = new ArrayList<String>();
			tempArrayList.add("Clench of the North Wind"); tempArrayList.add("Gong of the Summit"); 
			tempArrayList.add("Fangs of the Fire Snake"); tempArrayList.add("Fist of Four Thunders"); 
			tempArrayList.add("Fist of Unbroken Air"); tempArrayList.add("Rush of the Gale Spirits");
			tempArrayList.add("Shape the Flowing River"); tempArrayList.add("Sweeping Cinder Strike");
			tempArrayList.add("Water Whip");
			prompt(pw, br, "Choose an Elemental Discipline", tempArrayList, 1);
				break;
			case 11: tempArrayList = new ArrayList<String>();
			tempArrayList.add("Eternal Mountain Defense"); tempArrayList.add("Flames of the Phoenix");
			tempArrayList.add("Mist Stance"); tempArrayList.add("Ride the Wind");
			tempArrayList.add("Clench of the North Wind"); tempArrayList.add("Gong of the Summit"); 
			tempArrayList.add("Fangs of the Fire Snake"); tempArrayList.add("Fist of Four Thunders"); 
			tempArrayList.add("Fist of Unbroken Air"); tempArrayList.add("Rush of the Gale Spirits");
			tempArrayList.add("Shape the Flowing River"); tempArrayList.add("Sweeping Cinder Strike");
			tempArrayList.add("Water Whip");
			prompt(pw, br, "Choose an Elemental Discipline", tempArrayList, 1);
				break;
			case 17: tempArrayList = new ArrayList<String>();
			tempArrayList.add("Breath of Winter"); tempArrayList.add("River of Hungry Flame");
			tempArrayList.add("Wave of Rolling Earth");
			tempArrayList.add("Eternal Mountain Defense"); tempArrayList.add("Flames of the Phoenix");
			tempArrayList.add("Mist Stance"); tempArrayList.add("Ride the Wind");
			tempArrayList.add("Clench of the North Wind"); tempArrayList.add("Gong of the Summit"); 
			tempArrayList.add("Fangs of the Fire Snake"); tempArrayList.add("Fist of Four Thunders"); 
			tempArrayList.add("Fist of Unbroken Air"); tempArrayList.add("Rush of the Gale Spirits");
			tempArrayList.add("Shape the Flowing River"); tempArrayList.add("Sweeping Cinder Strike");
			tempArrayList.add("Water Whip");
			prompt(pw, br, "Choose an Elemental Discipline", tempArrayList, 1);
				break;
			}
			break;
		}
	}
	public void shortRest(Socket actorSocket){
		super.shortRest(actorSocket);
		iCurrentKi = iMaxKi;
	}
	public void longRest(Socket actorSocket){
		shortRest(actorSocket);
		super.longRest(actorSocket);
	}
}