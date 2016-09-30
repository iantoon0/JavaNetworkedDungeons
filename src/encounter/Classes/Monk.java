package encounter.Classes;

import encounter.Hero;

public class Monk extends Hero {
	int maxKi, currentKi;
	public Monk(){
		sClassName = "Monk";
		bSpellcaster = false;
		maxKi = 0;
		currentKi = 0;
		iMaxHP = 8 + iConMod;
	}
	public Monk(String r){
		super(r);
		sClassName = "Monk";
		bSpellcaster = false;
		maxKi = 0;
		currentKi = 0;
		iMaxHP = 8 + iConMod;
	}
	public void iLevelUp(){
		super.iLevelUp();
	}
}