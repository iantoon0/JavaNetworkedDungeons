package encounter;

import java.util.*;

public class Hero extends EncounterActor {
	public int level, xp, proficiencyBonus, nextLvlXP, gold, hpGainedPerLevel;
	public boolean inspiration, spellcaster;
	public String name, race, className;
	ArrayList<Spell> spellsKnown, spellsPrepared;
	ArrayList<String> proficiencies, cantripsKnown, languages, skillProficiencies;
	
	HashMap<String, Integer> skillMap;
	HashMap<String, Boolean> featsMap;
	
	ArrayList<Item> inventory;
		
	public Hero(){
		nextLvlXP = 300;
		level = 1;
		xp = 0;
		proficiencyBonus = 2;
		proficiencies = new ArrayList<String>();
		skillProficiencies = new ArrayList<String>();
		cantripsKnown = new ArrayList<String>();
		languages = new ArrayList<String>();
		
		str = 10; con = 10; dex = 10; wis = 10; intel = 10; cha = 10;
		
		skillMap = new HashMap<String, Integer>();
		featsMap = new HashMap<String, Boolean>();
		
		race = "human";
		calculateStatMod();
		calculateSkillMod();
		
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
			
		case "tiefling": 
			cha += 2; intel++; 
			
			featsMap.put("Darkvision", true); featsMap.put("Hellish Resistance", true); featsMap.put("Infernal Legacy", true); 
			cantripsKnown.add("Thaumaturgy");
			
			break;
			
		}
	}
	
	public Hero(String r){
		nextLvlXP = 300;
		level = 1;
		xp = 0;
		proficiencyBonus = 2;
		race = r;
		proficiencies = new ArrayList<String>();
		skillProficiencies = new ArrayList<String>();
		cantripsKnown = new ArrayList<String>();
		languages = new ArrayList<String>();
		
		str = 10; con = 10; dex = 10; wis = 10; intel = 10; cha = 10;
		
		skillMap = new HashMap<String, Integer>();
		featsMap = new HashMap<String, Boolean>();
		
		calculateStatMod();
		calculateSkillMod();
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
			
		case "tiefling": 
			cha += 2; intel++; 
			
			featsMap.put("Darkvision", true); featsMap.put("Hellish Resistance", true); featsMap.put("Infernal Legacy", true); 
			cantripsKnown.add("Thaumaturgy");
			
			break;
			
		}
	}

	
	public void levelUp(){
		level++;
		nextLvlXP = Constants.XP_LEVELS[level];
		if ((level - 1) % 4 == 0){
			proficiencyBonus++;
		}
		calculateSkillMod();
	}
	
	public void addXP(int xp){
		this.xp += xp;
		if (this.xp >= nextLvlXP){
			levelUp();
		}
	}
	
	public void calculateSkillMod(){
		skillMap.put("acrobatics", dexMod);
		skillMap.put("animalHandling", wisMod);
		skillMap.put("arcana", intelMod);
		skillMap.put("athletics", strMod);
		skillMap.put("deception", chaMod);
		skillMap.put("history", intelMod);
		skillMap.put("insight", wisMod);
		skillMap.put("intimidation", chaMod);
		skillMap.put("investigation", intelMod);
		skillMap.put("medicine", wisMod);
		skillMap.put("nature", intelMod);
		skillMap.put("perception", wisMod);
		skillMap.put("performance", chaMod);
		skillMap.put("persuasion", chaMod);
		skillMap.put("religion", intelMod);
		skillMap.put("sleightOfHand", dexMod);
		skillMap.put("stealth", dexMod);
		skillMap.put("survival", dexMod);
		for (String s : skillProficiencies){
			skillMap.put(s, skillMap.get(s) + proficiencyBonus);
		}
	}
}