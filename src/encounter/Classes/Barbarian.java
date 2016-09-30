package encounter.Classes;

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
	public void iLevelUp(){
		super.iLevelUp();
	}
}
