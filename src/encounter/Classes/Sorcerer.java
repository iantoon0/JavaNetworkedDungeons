package encounter.Classes;

import java.net.Socket;

import encounter.Hero;

public class Sorcerer extends Hero {
	public Sorcerer(){
		sClassName = "Sorcerer";
		bSpellcaster = true;
		//put everthing that a sorcerer gets at level 1 here
		}
	public Sorcerer(String r){
		super(r);
		sClassName = "Sorcerer";
		bSpellcaster = true;
		//put everthing that a sorcerer gets at level 1 here
	}
	public void levelUp(){
		super.levelUp();
		
	}
	public void shortRest(Socket actorSocket){
		super.shortRest(actorSocket);
		// do all stuff that a sorcerer can do in a short rest
	}
	public void longRest(Socket actorSocket){
		this.shortRest(actorSocket);
		super.longRest(actorSocket);
		// do all stuff that a sorcerer can do in a long rest
	}
	
}
