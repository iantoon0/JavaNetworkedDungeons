package encounter;

import java.net.Socket;
import java.util.*;

public class Hero extends EncounterActor {
	public int iLevel, iXP, iProficiencyBonus, iNextLvlXP, iGold, iHPGainedPerLevel;
	public boolean bInspiration, bSpellcaster;
	public String sName, sRace, sClassName;
	public ArrayList<Spell> listSpellsKnown, listSpellsPrepared;
	public ArrayList<String> listProficiencies, listCantripsKnown, listLanguages, listSkillProficiencies;
	public HashMap<String, Integer> dictSkills;
	public HashMap<String, Boolean> dictFeats;
	public HashMap<String, String> dictBackgroundTraits;
	
	ArrayList<Item> inventory;
		
	public Hero(){
		iNextLvlXP = 300;
		iLevel = 1;
		iXP = 0;
		iProficiencyBonus = 2;
		listProficiencies = new ArrayList<String>();
		listSkillProficiencies = new ArrayList<String>();
		listCantripsKnown = new ArrayList<String>();
		listLanguages = new ArrayList<String>();
		DiceRoller dr = new DiceRoller();
		iStr = 10; iCon = 10; iDex = 10; iWis = 10; iInt = 10; iCha = 10;
		
		dictSkills = new HashMap<String, Integer>();
		dictFeats = new HashMap<String, Boolean>();
		dictBackgroundTraits = new HashMap<String, String>();
		
		sRace = "human";
		calculateStatMod();
		calculateSkillMod();
		
		switch (sRace){
		case "hillDwarf": 
			iCon += 2; iWis++; 
			iHP++; iHPGainedPerLevel++;
			break;
			
		case "mountainDwarf": 
			iCon += 2; iStr += 2; 
			listProficiencies.add("Light Armor"); listProficiencies.add("Medium Armor");
			break;
			
		case "highElf": 
			iDex += 2; iInt++; 
			
			dictFeats.put("Darkvision", true); 
			
			listProficiencies.add("Longsword"); listProficiencies.add("Shortsword"); listProficiencies.add("Longbow"); listProficiencies.add("Shortbow");
			break;
			
		case "darkElf": 
			iDex += 2; iCha++; 
			
			dictFeats.put("Darkvision", true); dictFeats.put("Superior Darkvision", true); 
			dictFeats.put("Sunlight Sensitivity", true); dictFeats.put("Drow Magic", true); listCantripsKnown.add("Dancing Lights");
			
			listProficiencies.add("Rapier"); listProficiencies.add("Shortsword"); listProficiencies.add("Hand Crossbow");
			
			break;
			
		case "woodElf": 
			iDex += 2; iWis++;
			
			dictFeats.put("Darkvision", true);
			
			listProficiencies.add("Longsword"); listProficiencies.add("Shortsword"); listProficiencies.add("Longbow"); listProficiencies.add("Shortbow");
			break;
		
		case "human": 
			iStr++; iCon++; iDex++; iWis++; iInt++; iCha++; 
			
			iMoveSpeed = 30;
			
			break;
		
		case "dragonbornBlack":
			
			
		case "tiefling": 
			iCha += 2; iInt++; 
			
			dictFeats.put("Darkvision", true); dictFeats.put("Hellish Resistance", true); dictFeats.put("Infernal Legacy", true); 
			listCantripsKnown.add("Thaumaturgy");
			
			break;
			
		}
	}
	
	public Hero(String r){
		iNextLvlXP = 300;
		iLevel = 1;
		iXP = 0;
		iProficiencyBonus = 2;
		sRace = r;
		listProficiencies = new ArrayList<String>();
		listSkillProficiencies = new ArrayList<String>();
		listCantripsKnown = new ArrayList<String>();
		listLanguages = new ArrayList<String>();
		
		iStr = 10; iCon = 10; iDex = 10; iWis = 10; iInt = 10; iCha = 10;
		
		dictSkills = new HashMap<String, Integer>();
		dictFeats = new HashMap<String, Boolean>();
		
		calculateStatMod();
		calculateSkillMod();
		switch (sRace){
		case "hillDwarf": 
			iCon += 2; iWis++; 
			iHP++; iHPGainedPerLevel++;
			break;
			
		case "mountainDwarf": 
			iCon += 2; iStr += 2; 
			listProficiencies.add("Light Armor"); listProficiencies.add("Medium Armor");
			break;
			
		case "highElf": 
			iDex += 2; iInt++; 
			
			dictFeats.put("Darkvision", true); 
			
			listProficiencies.add("Longsword"); listProficiencies.add("Shortsword"); listProficiencies.add("Longbow"); listProficiencies.add("Shortbow");
			break;
			
		case "darkElf": 
			iDex += 2; iCha++; 
			
			dictFeats.put("Darkvision", true); dictFeats.put("Superior Darkvision", true); 
			dictFeats.put("Sunlight Sensitivity", true); dictFeats.put("Drow Magic", true); listCantripsKnown.add("Dancing Lights");
			
			listProficiencies.add("Rapier"); listProficiencies.add("Shortsword"); listProficiencies.add("Hand Crossbow");
			
			break;
			
		case "woodElf": 
			iDex += 2; iWis++; 
			
			dictFeats.put("Darkvision", true); 
			
			listProficiencies.add("Longsword"); listProficiencies.add("Shortsword"); listProficiencies.add("Longbow"); listProficiencies.add("Shortbow");
			break;
		
		case "human": 
			iStr++; iCon++; iDex++; iWis++; iInt++; iCha++; 
			
			iMoveSpeed = 30;
			
			break;
			
		case "tiefling": 
			iCha += 2; iInt++; 
			
			dictFeats.put("Darkvision", true); dictFeats.put("Hellish Resistance", true); dictFeats.put("Infernal Legacy", true); 
			listCantripsKnown.add("Thaumaturgy");
			
			break;
			
		}
	}

	
	public void levelUp(){
		iLevel++;
		iNextLvlXP = Constants.XP_LEVELS[iLevel];
		if ((iLevel - 1) % 4 == 0){
			iProficiencyBonus++;
		}
		if (iLevel % 4 == 0){
			//Prompt: Choose feat or stats increase
		}
		calculateSkillMod();
	}
	
	public void addXP(int iXP){
		this.iXP += iXP;
		if (this.iXP >= iNextLvlXP){
			levelUp();
		}
	}
	
	public void calculateSkillMod(){
		dictSkills.put("acrobatics", iDexMod);
		dictSkills.put("animalHandling", iWisMod);
		dictSkills.put("arcana", iIntMod);
		dictSkills.put("athletics", iStrMod);
		dictSkills.put("deception", iChaMod);
		dictSkills.put("history", iIntMod);
		dictSkills.put("insight", iWisMod);
		dictSkills.put("intimidation", iChaMod);
		dictSkills.put("investigation", iIntMod);
		dictSkills.put("medicine", iWisMod);
		dictSkills.put("nature", iIntMod);
		dictSkills.put("perception", iWisMod);
		dictSkills.put("performance", iChaMod);
		dictSkills.put("persuasion", iChaMod);
		dictSkills.put("religion", iIntMod);
		dictSkills.put("sleightOfHand", iDexMod);
		dictSkills.put("stealth", iDexMod);
		dictSkills.put("survival", iDexMod);
		for (String s : listSkillProficiencies){
			dictSkills.put(s, dictSkills.get(s) + iProficiencyBonus);
		}
		iPassivePerception = dictSkills.get("perception") + 10;
	}
	public void shortRest(Socket actorSocket){
		if (iXP >= iNextLvlXP){
			levelUp();
		}
		if (iHP < iMaxHP){
			//prompt: spend hit dice
			
		}
	}
	public void longRest(Socket actorSocket){
		iHP = iMaxHP;
	}
}