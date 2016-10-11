package encounter.Classes;

import java.net.Socket;

import encounter.Hero;

public class Barbarian extends Hero {
	public Barbarian(){
		sClassName = "Barbarian";
		bSpellcaster = false;
	}
	public Barbarian(String r){
		super(r);
		sClassName = "Barbarian";
		bSpellcaster = false;
	}
	public void levelUp(Socket actorSocket){
		super.levelUp(actorSocket);
	}
}
