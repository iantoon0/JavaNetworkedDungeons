package encounter;

import java.util.ArrayList;

public class Hero extends EncounterActor {
	public int level, xp, proficiencyBonus, acrobatics, animalHandling, 
	arcana, athletics, deception, history, insight, intimidation, 
	investigation, medicine, nature, perception, performance, 
	persuasion, religion, sleightOfHand, stealth, survival, nextLvlXP, gold;
	public boolean inspiration, spellcaster;
	String name, race;
	ArrayList<Spell> spellsKnown;
	ArrayList<Spell> spellsPrepared;
	ArrayList<String> proficiencies, languages, feats;
	ArrayList<Integer> trainedSkills;
	ArrayList<Item> inventory;
	final int[] XP_LEVELS = {0, 300, 900, 2700, 6500, 14000, 23000, 34000, 48000, 64000, 85000, 100000, 120000, 140000, 165000, 195000, 225000, 265000, 305000, 355000};
	
	public Hero(){
		nextLvlXP = 300;
		level = 1;
		xp = 0;
		proficiencyBonus = 2;
		
	}
	
	public void levelUp(){
		level++;
		nextLvlXP = XP_LEVELS[level];
		if ((level - 1) % 4 == 0){
			proficiencyBonus++;
		}
		calculateSkillBon();
	}
	
	public void calculateSkillBon(){
		acrobatics = dexBon;
		animalHandling = wisBon;
		arcana = intelBon;
		athletics = strBon;
		deception = chaBon;
		history = intelBon;
		insight = wisBon;
		intimidation = chaBon;
		investigation = intelBon;
		medicine = wisBon;
		nature = intelBon;
		perception = wisBon;
		performance = chaBon;
		persuasion = chaBon;
		religion = intelBon;
		sleightOfHand = dexBon;
		stealth = dexBon;
		survival = dexBon;
		for(int i = 0; i < trainedSkills.size(); i++){
			switch (trainedSkills.get(i)){
				case 1:  acrobatics += proficiencyBonus;
            		break;
				case 2:  animalHandling += proficiencyBonus;
					break;
				case 3:  arcana += proficiencyBonus;
					break;
				case 4:  athletics += proficiencyBonus;
					break;
				case 5:  deception += proficiencyBonus;
					break;
				case 6:  history += proficiencyBonus;
            		break;
				case 7:  insight += proficiencyBonus;
            		break;
				case 8:  intimidation += proficiencyBonus;
            		break;
				case 9:  medicine += proficiencyBonus;
            		break;
				case 10: nature += proficiencyBonus;
            		break;
				case 11: perception += proficiencyBonus;
					break;
				case 12: performance += proficiencyBonus;
					break;
				case 13: persuasion += proficiencyBonus;
					break;
				case 14: religion += proficiencyBonus;
					break;
				case 15: sleightOfHand += proficiencyBonus;
					break;
				case 16: stealth += proficiencyBonus;
					break;
				case 17: survival += proficiencyBonus;
					break;
				default: break;
			}
		}
	}
}