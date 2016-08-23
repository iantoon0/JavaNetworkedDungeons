package encounter;

import java.util.*;

public class Hero extends EncounterActor {
	public int level, xp, proficiencyBonus, nextLvlXP, gold, hpGainedPerLevel;
	public boolean inspiration, spellcaster;
	String name, race;
	ArrayList<Spell> spellsKnown;
	ArrayList<Spell> spellsPrepared;
	ArrayList<String> proficiencies, languages, feats, skillProficiencies;
	HashMap<String, Integer> skillMap;
	ArrayList<Item> inventory;
		
	public Hero(){
		nextLvlXP = 300;
		level = 1;
		xp = 0;
		proficiencyBonus = 2;
		calculateSkillBon();
		switch (race){
		case "hillDwarf": con += 2; wis++; hp++; hpGainedPerLevel++;
			break;
		case "mtnDwarf": con += 2; str += 2; proficiencies.add("light armor"); proficiencies.add("medium armor");
			break;
		case "highElf": dex += 2; intel++; 
			break;
		case "darkElf": dex += 2; cha++;
			break;
		case "woodElf": dex += 2; wis++;
			break;
		}
	}
	
	public void levelUp(){
		level++;
		nextLvlXP = Constants.XP_LEVELS[level];
		if ((level - 1) % 4 == 0){
			proficiencyBonus++;
		}
		if(level % 4 == 0){
			
		}
		calculateSkillBon();
	}
	
	public void addXP(int xp){
		this.xp += xp;
		if (this.xp >= nextLvlXP){
			levelUp();
		}
	}
	
	public void calculateSkillBon(){
		skillMap.put("acrobatics", dexBon);
		skillMap.put("animalHandling", wisBon);
		skillMap.put("arcana", intelBon);
		skillMap.put("athletics", strBon);
		skillMap.put("deception", chaBon);
		skillMap.put("history", intelBon);
		skillMap.put("insight", wisBon);
		skillMap.put("intimidation", chaBon);
		skillMap.put("investigation", intelBon);
		skillMap.put("medicine", wisBon);
		skillMap.put("nature", intelBon);
		skillMap.put("perception", wisBon);
		skillMap.put("performance", chaBon);
		skillMap.put("persuasion", chaBon);
		skillMap.put("religion", intelBon);
		skillMap.put("sleightOfHand", dexBon);
		skillMap.put("stealth", dexBon);
		skillMap.put("survival", dexBon);
		for (String s : skillProficiencies){
			skillMap.put(s, skillMap.get(s) + proficiencyBonus);
		}
		
	}
}