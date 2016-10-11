package encounter.Classes;

import java.net.Socket;
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
	public void levelUp(){
		super.levelUp();
		iMaxKi = iLevel;
		switch (iLevel){
		case 2: dictFeats.put("Unarmored Movement", true); iUnarmoredSpeed = 10;
			break;
		case 3: /*Prompt: Choose path*/ dictFeats.put("Deflect Missiles", true); 
			break;
		case 4: dictFeats.put("Slow Fall", true);
			break;
		case 5: dictFeats.put("Extra Attack", true); dictFeats.put("Stunning Strike", true); iMonkDie = 6; 
			break;
		case 6: dictFeats.put("Ki-Empowered Strikes", true); iUnarmoredSpeed = 15;
			break;
		case 7: dictFeats.put("Evasion", true); dictFeats.put("Stillness Of Mind", true);
			break;
		case 8: break;
		case 9: break;
		case 10: dictFeats.put("Purity Of Body", true); iUnarmoredSpeed = 20; 
			break;
		case 11: iMonkDie = 8; 
			break;
		case 12: break;
		case 13: dictFeats.put("Tongue Of The Sun And Moon", true);
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
		case "hand":
			switch (iLevel){
			case 3: dictFeats.put("Open Hand Technique", true);
				break;
			case 6: dictFeats.put("Wholeness Of Body", true);
				break;
			case 11: dictFeats.put("Tranquility", true);
				break;
			case 17: dictFeats.put("Quivering Palm", true);
				break;
			}
			break;
		case "shadow": 
			switch (iLevel){
			case 3: dictFeats.put("Shadow Arts", true);
				break;
			case 6: dictFeats.put("Shadow Step", true);
				break;
			case 11: dictFeats.put("Cloak Of Shadows", true);
				break;
			case 17: dictFeats.put("Opportunist", true);
				break;
			}
			break;
		case "elemental":
			switch (iLevel){
			case 3: //Prompt: Choose elemental discipline
				break;
			case 6: 
				break;
			case 11: 
				break;
			case 17: 
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