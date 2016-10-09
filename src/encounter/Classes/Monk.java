package encounter.Classes;

import java.net.Socket;
import java.util.HashMap;

import encounter.EncounterActor;
import encounter.Hero;

public class Monk extends Hero {
	int iMaxKi, iCurrentKi;
	int iPath;//0 - Way of 
	public Monk(){
		sClassName = "Monk";
		bSpellcaster = false;
		iMaxKi = 0;
		iCurrentKi = 0;
		iMaxHP = 8 + iConMod;
	}
	public Monk(String r){
		super(r);
		sClassName = "Monk";
		bSpellcaster = false;
		iMaxKi = 0;
		iCurrentKi = 0;
		iMaxHP = 8 + iConMod;
	}
	public void iLevelUp(){
		super.iLevelUp();
		iMaxKi = iLevel;
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