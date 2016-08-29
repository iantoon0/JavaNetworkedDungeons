package encounter;

import java.util.*;

public class Hero extends EncounterActor {
	public int level, xp, proficiencyBonus, nextLvlXP, gold, hpGainedPerLevel;
	public boolean inspiration, spellcaster;
	public String name, race, className;
	ArrayList<Spell> spellsKnown, spellsPrepared;
	ArrayList<String> proficiencies, cantripsKnown, languages, skillProficiencies;
	HashMap<String, Integer> skillMap;
	HashMap<String,Boolean> featsMap;
	ArrayList<Item> inventory;
		
	public Hero(){
		nextLvlXP = 300;
		level = 1;
		xp = 0;
		proficiencyBonus = 2;
		skillProficiencies = new ArrayList<String>();
		str = 10; con = 10; dex = 10; wis = 10; intel = 10; cha = 10;
		skillMap = new HashMap<String, Integer>();
		race = "human";
		calculateStatBon();
		calculateSkillBon();
		switch (race){
		case "hillDwarf": 
			con += 2; wis++; 
			hp++; hpGainedPerLevel++;
			break;
			
		case "mountainDwarf": 
			con += 2; str += 2; 
			proficiencies.add("Light Armor"); proficiencies.add("Medium Armor");
			break;
			
		case "highElf": 
			dex += 2; intel++; 
			featsMap.put("Darkvision", true); 
			proficiencies.add("Longsword"); proficiencies.add("Shortsword"); proficiencies.add("Longbow"); proficiencies.add("Shortbow");
			break;
			
		case "darkElf": 
			dex += 2; cha++; 
			featsMap.put("Darkvision", true); featsMap.put("Superior Darkvision", true); 
			featsMap.put("Sunlight Sensitivity", true); featsMap.put("Drow Magic", true); cantripsKnown.add("Dancing Lights");
			
			proficiencies.add("Rapier"); proficiencies.add("Shortsword"); proficiencies.add("Hand Crossbow");
			
			break;
			
		case "woodElf": 
			dex += 2; wis++; 
			featsMap.put("Darkvision", true); 
			proficiencies.add("Longsword"); proficiencies.add("Shortsword"); proficiencies.add("Longbow"); proficiencies.add("Shortbow");
			break;
		
		case "human": 
			str++; con++; dex++; wis++; intel++; cha++; 
			moveSpeed = 30;
			break;
			
		case "tiefling": cha += 2; intel++; featsMap.put("Darkvision", true); featsMap.put("Hellish Resistance", true); 
			featsMap.put("Infernal Legacy", true); cantripsKnown.add("Thaumaturgy");
			break;
			
		}
	}
	
	public void levelUp(){
		level++;
		nextLvlXP = Constants.XP_LEVELS[level];
		if ((level - 1) % 4 == 0){
			proficiencyBonus++;
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