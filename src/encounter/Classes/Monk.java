package encounter.Classes;

import encounter.Hero;

public class Monk extends Hero {
	int maxKi, currentKi;
	public Monk(){
		spellcaster = false;
		maxKi = 0;
		currentKi = 0;
		maxHP = 8 + conBon;
	}
}