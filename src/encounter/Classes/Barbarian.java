package encounter.Classes;

import encounter.Hero;

public class Barbarian extends Hero {
	public Barbarian(){
		className = "Barbarian";
		spellcaster = false;
	}
	public Barbarian(String r){
		super(r);
		className = "Barbarian";
		spellcaster = false;
	}
	public void levelUp(){
		super.levelUp();
	}
}
