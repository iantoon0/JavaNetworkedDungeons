package encounter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class Hero extends EncounterActor {
	public int iLevel, iXP, iProficiencyBonus, iNextLvlXP, iGold, iHPGainedPerLevel, iHitDie, iTotalNumHitDice, iCurrentNumHitDice;
	public int[] iArrayCurrentSpellSlots, iArrayMaxSpellSlots;
	public boolean bInspiration;
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
		iArrayCurrentSpellSlots = new int[9];
		iArrayMaxSpellSlots = new int[9];
		listProficiencies = new ArrayList<String>();
		listSkillProficiencies = new ArrayList<String>();
		listCantripsKnown = new ArrayList<String>();
		listLanguages = new ArrayList<String>();
		DiceRoller dr = new DiceRoller();
		iStr = 10; iCon = 10; iDex = 10; iWis = 10; iInt = 10; iCha = 10;
		dictStatusEffects.put("Blinded", false); dictStatusEffects.put("Stunned", false); 
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
		iArrayCurrentSpellSlots = new int[9];
		iArrayMaxSpellSlots = new int[9];
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

	public void takeTurn(){
		bHasTakenAction = false;
		bHasTakenBonusAction = false;
		bHasTakenReaction = false;
	}
	public void levelUp(Socket actorSocket) throws IOException{
		iLevel++;
		iNextLvlXP = Constants.XP_LEVELS[iLevel];
		if ((iLevel - 1) % 4 == 0){
			iProficiencyBonus++;
		}
		if (iLevel % 4 == 0){
			//Prompt: Choose feat or stats increase
			PrintWriter pw = new PrintWriter(actorSocket.getOutputStream());
			BufferedReader br = new BufferedReader( new InputStreamReader(actorSocket.getInputStream()));
			ArrayList<String> tempArrayList = new ArrayList<String>(); tempArrayList.add("New Feature");tempArrayList.add("Stats Increase");
			if(prompt(pw, br, "Choose A Feat Or Stat Increase", tempArrayList, 1).equals("New Feature")){
				tempArrayList = new ArrayList<String>();
				tempArrayList.add("");tempArrayList.add("");tempArrayList.add("");tempArrayList.add("");tempArrayList.add("");//TODO add possible class-independent feats
				dictFeats.put(prompt(pw, br, "Choose A Feat Or Stat Increase", tempArrayList, 1), true);
			}
			else{
				tempArrayList = new ArrayList<String>();
				tempArrayList.add("Strength"); tempArrayList.add("Dexterity"); tempArrayList.add("Constitution");
				tempArrayList.add("Intelligence");tempArrayList.add("Wisdom");tempArrayList.add("Charisma");
				String tempString = prompt(pw, br, "Choose 1 Stat to increase by 2, or 2 stats to increase by 1 each", tempArrayList, 2);
				String[] tempStringArray = new String[2];
				tempStringArray[0] = tempString.substring(0, tempString.indexOf(','));
				tempStringArray[1] = tempString.substring(tempString.indexOf(','));
				for(String tempString2 : tempStringArray){
					switch (tempString2){
					case "Strength": iStr++;
						break;
					case "Dexterity": iDex++;
						break;
					case "Constitution": iCon++;
						break;
					case "Intelligence": iInt++;
						break;
					case "Wisdom": iWis++;
						break;
					case "Charisma": iCha++;
						break;
					}
				}
			}
			
		}
		calculateSkillMod();
	}
	
	public void addXP(int iXP, Socket actorSocket){
		this.iXP += iXP;
		if (this.iXP >= iNextLvlXP){
			try {
				levelUp(actorSocket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void calculateSkillMod(){
		calculateStatMod();
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
			try {
				levelUp(actorSocket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (iHP < iMaxHP){
			//prompt: spend hit dice
			PrintWriter pw = null;
			BufferedReader br = null;
			try {
				pw = new PrintWriter(actorSocket.getOutputStream());
				br = new BufferedReader( new InputStreamReader(actorSocket.getInputStream()));
				ArrayList<String> tempArrayList = new ArrayList<String>(); 
				for (int i = 1; i <= iCurrentNumHitDice; i++){
					tempArrayList.add(i + "d" + iHitDie);
				}
				String diceRolledString = prompt(pw, br, "Spend Hit Dice?", tempArrayList, 1);
				int diceType = Integer.parseInt(diceRolledString.substring(2));
				int diceNumber = Integer.parseInt(diceRolledString.substring(0,1));
				DiceRoller dr = new DiceRoller();
				iCurrentNumHitDice -= diceNumber;
				switch (diceType) {
				case 4: iHP += (diceNumber * iConMod) + dr.d4(diceNumber);
					break;
				case 6: iHP += (diceNumber * iConMod) + dr.d6(diceNumber);
					break;
				case 8: iHP += (diceNumber * iConMod) + dr.d8(diceNumber);
					break;
				case 10: iHP += (diceNumber * iConMod) + dr.d10(diceNumber);
					break;
				case 12: iHP += (diceNumber * iConMod) + dr.d12(diceNumber);
					break;
				default:
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public String prompt(PrintWriter pw, BufferedReader br, String sTitle, ArrayList<String> options, int numSelectable) throws IOException{
		String writeString = new String();
		writeString = "Prompt: '" + sTitle + "' ";
		writeString += "Options: [";
		for(String s : options){
			writeString += ("'" + s + "', ");
		}
		writeString += "], " + numSelectable;
		pw.write(writeString);
		while(!br.ready()){
			try {
				wait(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return br.readLine();
	}
	public String combatPrompt(PrintWriter pw, BufferedReader br, String sTitle, ArrayList<String> options, int numSelectable) throws IOException{
		String writeString = new String();
		writeString = "CombatPrompt: '" + sTitle + "' ";
		writeString += "Options: [";
		for(String s : options){
			writeString += ("'" + s + "', ");
		}
		writeString += "], " + numSelectable;
		pw.write(writeString);
		while(!br.ready()){
			try {
				wait(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return br.readLine();
	}
	public void longRest(Socket actorSocket){
		iHP = iMaxHP;
		iCurrentNumHitDice = iTotalNumHitDice;
	}
	
	public void determineActions(){
		//TODO Add possible actions & conditions to determineActions
		listActions = new ArrayList<String>();
		listActions.add("Attack");
	}
	
}