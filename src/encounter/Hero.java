package encounter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

import com.google.gson.Gson;

public class Hero extends EncounterActor {
	
	//All-hero variables
	public int iLevel, iXP, iProficiencyBonus, iNextLvlXP, iGold, iHPGainedPerLevel;
	public int[] iArrayCurrentSpellSlots, iArrayMaxSpellSlots;
	public ArrayList<Integer> iArrayMaxHitDice, iArrayCurrentHitDice;
	public boolean bInspiration;
	public String sName, sRace, sClassName;
	public ArrayList<Spell> listSpellsKnown, listSpellsPrepared;
	public ArrayList<String> listProficiencies, listCantripsKnown, listLanguages, listSkillProficiencies;
	public HashMap<String, Integer> dictSkills, dictClassLevels;
	public HashMap<String, Boolean> dictFeats;
	public HashMap<String, String> dictBackgroundTraits;
	ArrayList<Item> listInventory;
	
	//Barbarian variables
	
	
	//Bard variables
	
	
	//Cleric variables
	String sDomain;
	int iChannelDivinity;
	int iMaxChannelDivinity;
	
	//Druid variables
	
	
	//Fighter variables
	public String sMartialArchetype;
	public boolean bSecondWind;
	public int iActionSurge, iMaxActionSurge, iIndomitable, iMaxIndomitable, iSuperiorityDice, iMaxSuperiorityDice, iSpellSlots, iMaxSpellSlots;
	
	//Monk variables
	int iMaxKi, iCurrentKi, iMonkDie, iUnarmoredSpeed;
	String sPath;
	
	//Paladin variables
	
	
	//Ranger variables
	
	
	//Rogue variables
	
	
	//Sorcerer variables
	String sorcerousOrigin;
	int iCurrentSorceryPoints;
	int iMaxSorceryPoints;
	
	//Warlock variables
	
	
	//Wizard variables
	
	
	
		
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
		PrintWriter pw = new PrintWriter(actorSocket.getOutputStream());
		BufferedReader br = new BufferedReader( new InputStreamReader(actorSocket.getInputStream()));
		
		//*********************************************
		//********CLASS-INDEPENDENT INCREASES**********
		//*********************************************
		
		
		
		if ((iLevel - 1) % 4 == 0){
			iProficiencyBonus++;
		}
		if (iLevel % 4 == 0){
			//Prompt: Choose feat or stats increase
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
		
		//*********************************************
		//*********CLASS-DEPENDENT INCREASES***********
		//*********************************************
		ArrayList<String> tempArray = new ArrayList<String>();
		tempArray.add("Barbarian");tempArray.add("Bard");tempArray.add("Cleric");tempArray.add("Druid");tempArray.add("Fighter");tempArray.add("Monk");tempArray.add("Paladin");tempArray.add("Ranger");tempArray.add("Rogue");tempArray.add("Sorcerer");tempArray.add("Warlock");tempArray.add("Wizard");
		
		String classToLevelUp = prompt(pw, br, "Choose a class to level up", tempArray, 1);
		switch(sClassName){
		case "Barbarian": 
			dictClassLevels.put("Barbarian", dictClassLevels.get("Barbarian") + 1);
			break;
			
		case "Bard": 
			dictClassLevels.put("Bard", dictClassLevels.get("Bard") + 1);
			break;
		
		case "Cleric": 
			dictClassLevels.put("Cleric", dictClassLevels.get("Cleric") + 1);
			switch(dictClassLevels.get("Cleric")){
			
			case 2: 
				dictFeats.put("Channel Divinity (1/rest)", true);
				switch (sDomain){
			
				case "Knowledge Domain": 
					dictFeats.put("Channel Divinity: Knowledge of the Ages", true);
					break;
				
				case "Life Domain": 
					dictFeats.put("Channel Divinity: Preserve Life", true);
					break;
				
				case "Light Domain": 
					dictFeats.put("Channel Divinity: Radiance of the Dawn", true);
					break;
					
				case "Nature Domain": 
					dictFeats.put("Channel Divinity: Charm Animals and Plants", true);
					break;
					
				case "Tempest Domain": 
					dictFeats.put("Channel Divinity: Destructive Wrath", true);
					break;
					
				case "Trickery Domain": 
					dictFeats.put("Channel Divinity: Invoke Duplicity", true);
					break;
				
				case "War Domain": 
					dictFeats.put("Channel Divinity: Guided Strike", true);
					break;
				
				}
				break;
			case 5: dictFeats.put("Destroy Undead CR 1/2", true);
				break;
			case 6: 
				switch (sDomain){
				
				case "Knowledge Domain": 
					dictFeats.put("Channel Divinity: Read Thought", true);
					break;
					
				case "Life Domain": 
					dictFeats.put("Channel Divinity: Blessed Health", true);
					break;
					
				case "Light Domain": 
					dictFeats.put("Improved Flare", true);
					break;
					
				case "Nature Domain": 
					dictFeats.put("Channel Divinity: Dampen Elements", true);
					break;
					
				case "Tempest Domain": 
					dictFeats.put("Channel Divinity: Thunderbolt Strike", true);
					break;
					
				case "Trickery Domain": 
					dictFeats.put("Channel Divinity: Cloak of Shadows", true);
					break;
					
				case "War Domain": 
					dictFeats.put("Channel Divinity: War God's Blessing", true);
					break;
				}
				break;
			case 8: 
				switch (sDomain){
			
				case "Knowledge Domain": 
					dictFeats.put("Potent Spellcasting", true);
					break;
				
				case "Life Domain": 
					dictFeats.put("Divine Strike", true);
					break;
				
				case "Light Domain": 
					dictFeats.put("Potent Spellcasting", true);
					break;
				
				case "Nature Domain": 
					dictFeats.put("Divine Strike", true);
					break;
				
				case "Tempest Domain": 
					dictFeats.put("Divine Strike", true);
					break;
				
				case "Trickery Domain": 
					dictFeats.put("Divine Strike", true);
					break;
				
				case "War Domain": 
					dictFeats.put("Divine Strike", true);
					break;
				}
				break;
			case 10: dictFeats.put("Divine Intervention", true);
				break;
			case 17: 
				switch (sDomain){
				case "Knowledge Domain": 
					dictFeats.put("Visions of the Past", true);
					break;
					
				case "Life Domain": 
					dictFeats.put("Supreme Healing", true);
					break;
					
				case "Light Domain": 
					dictFeats.put("Corona of Light", true);
					break;
					
				case "Nature Domain": 
					dictFeats.put("Master of Nature", true);
					break;
					
				case "Tempest Domain": 
					dictFeats.put("Stormborn", true);
					break;
				
				case "Trickery Domain": 
					dictFeats.put("Improved Duplicity", true);
					break;
				
				case "War Domain": 
					dictFeats.put("Avatar of Battle", true);
					break;	
				}
				break;
			}
			break;
		
		case "Druid": 
			dictClassLevels.put("Druid", dictClassLevels.get("Druid") + 1);
			break;
		
		case "Fighter": 
			dictClassLevels.put("Fighter", dictClassLevels.get("Fighter") + 1);
			switch(dictClassLevels.get("Fighter")){
				case 2:
					dictFeats.put("Action Surge(one use)", true);
					break;
					
				case 3:
					switch(sMartialArchetype){
					
					case "Champion": 
						dictFeats.put("Improved Critical", true);
						break;
						
					case "Battle Master": 
						dictFeats.put("Combat Superiority", true);
						dictFeats.put("Student of War", true);
						break;
						
					case "Eldritch Knight": 
						dictFeats.put("Weapon Bond", true);
						bSpellcaster = true;
						break;
					}
					break;
				
				case 5:
					dictFeats.put("Extra Attack", true);
					break;
				
				case 7:
					switch(sMartialArchetype){
					
					case "Champion": 
						dictFeats.put("Remarkable Athlete", true);
						break;
						
					case "Battle Master": 
						dictFeats.put("Know Your Enemy", true);
						break;
						
					case "Eldritch Knight": 
						dictFeats.put("War Magic", true);
						break;
					}
					break;
				
				case 9:
					dictFeats.put("Indomitable (one use)", true);
					break;
				
				case 10:
					switch(sMartialArchetype){
					
					case "Champion": 
						dictFeats.put("Additional Fighting Style", true);
						break;
						
					case "Battle Master": 
						dictFeats.put("Improved Combat Superiority", true);
						break;
						
					case "Eldritch Knight": 
						dictFeats.put("Eldritch Strike", true);
						break;
					}
					break;

				case 15:
					switch(sMartialArchetype){
					
					case "Champion": 
						dictFeats.put("Superior Critical", true);
						break;
						
					case "Battle Master": 
						dictFeats.put("Relentless", true);
						break;
						
					case "Eldritch Knight": 
						dictFeats.put("Arcane Charge", true);
						break;
					}
					break;
				case 18:
					switch(sMartialArchetype){
					
					case "Champion": 
						dictFeats.put("Survivor", true);
						break;
						
					case "Eldritch Knight": 
						dictFeats.put("Improved War Magic", true);
						break;
					}
					break;
			}
			break;
		
		case "Monk": 
			dictClassLevels.put("Monk", dictClassLevels.get("Monk") + 1);
			iMaxKi = dictClassLevels.get("Monk");
			switch (dictClassLevels.get("Monk")){
			case 2: dictFeats.put("Unarmored Movement", true); iUnarmoredSpeed = 10;
				break;
			case 3: /*Prompt: Choose path*/
				ArrayList<String> tempArrayList = new ArrayList<String>(); tempArrayList.add("Way of the Open Hand"); tempArrayList.add("Way of Shadows"); tempArrayList.add("Way of the Four Elements");
				prompt(pw, br, "Choose A Path", tempArrayList, 1);
				dictFeats.put("Deflect Missiles", true); 
				break;
			case 4: dictFeats.put("Slow Fall", true);
				break;
			case 5: dictFeats.put("Extra Attack", true); dictFeats.put("Stunning Strike", true); iMonkDie = 6; 
				break;
			case 6: dictFeats.put("Ki-Empowered Strikes", true); iUnarmoredSpeed = 15;
				break;
			case 7: dictFeats.put("Evasion", true); dictFeats.put("Stillness of Mind", true);
				break;
			case 8: break;
			case 9: break;
			case 10: dictFeats.put("Purity of Body", true); iUnarmoredSpeed = 20; 
				break;
			case 11: iMonkDie = 8; 
				break;
			case 12: break;
			case 13: dictFeats.put("Tongue of the Sun And Moon", true);
				break;
			case 14: iUnarmoredSpeed = 25;
				break;
			case 15: dictFeats.put("Timeless Body", true);
				break;
			case 16: break;
			case 17: iMonkDie = 10; 
				break;
			case 18: iUnarmoredSpeed = 30; dictFeats.put("Empty Body", true);
				break;
			case 19: break;
			case 20: dictFeats.put("Perfect Self", true);
				break;
			}
			switch (sPath){
			case "Way of the Open Hand":
				switch (dictClassLevels.get("Monk")){
				case 3: dictFeats.put("Open Hand Technique", true);
					break;
				case 6: dictFeats.put("Wholeness of Body", true);
					break;
				case 11: dictFeats.put("Tranquility", true);
					break;
				case 17: dictFeats.put("Quivering Palm", true);
					break;
				}
				break;
			case "Way of Shadow": 
				switch (dictClassLevels.get("Monk")){
				case 3: dictFeats.put("Shadow Arts", true);
					break;
				case 6: dictFeats.put("Shadow Step", true); 
					break;
				case 11: dictFeats.put("Cloak of Shadows", true);
					break;
				case 17: dictFeats.put("Opportunist", true);
					break;
				}
				break;
			case "Way of the Four Elements": 
				switch (dictClassLevels.get("Monk")){
				case 3: dictFeats.put("Elemental Attunement", true);
					ArrayList<String> tempArrayList = new ArrayList<String>(); 
					tempArrayList.add("Fangs of the Fire Snake"); tempArrayList.add("Fist of Four Thunders"); 
					tempArrayList.add("Fist of Unbroken Air"); tempArrayList.add("Rush of the Gale Spirits");
					tempArrayList.add("Shape the Flowing River"); tempArrayList.add("Sweeping Cinder Strike");
					tempArrayList.add("Water Whip");
					prompt(pw, br, "Choose an Elemental Discipline", tempArrayList, 1);
					break;
				case 6: tempArrayList = new ArrayList<String>();
				tempArrayList.add("Clench of the North Wind"); tempArrayList.add("Gong of the Summit"); 
				tempArrayList.add("Fangs of the Fire Snake"); tempArrayList.add("Fist of Four Thunders"); 
				tempArrayList.add("Fist of Unbroken Air"); tempArrayList.add("Rush of the Gale Spirits");
				tempArrayList.add("Shape the Flowing River"); tempArrayList.add("Sweeping Cinder Strike");
				tempArrayList.add("Water Whip");
				prompt(pw, br, "Choose an Elemental Discipline", tempArrayList, 1);
					break;
				case 11: tempArrayList = new ArrayList<String>();
				tempArrayList.add("Eternal Mountain Defense"); tempArrayList.add("Flames of the Phoenix");
				tempArrayList.add("Mist Stance"); tempArrayList.add("Ride the Wind");
				tempArrayList.add("Clench of the North Wind"); tempArrayList.add("Gong of the Summit"); 
				tempArrayList.add("Fangs of the Fire Snake"); tempArrayList.add("Fist of Four Thunders"); 
				tempArrayList.add("Fist of Unbroken Air"); tempArrayList.add("Rush of the Gale Spirits");
				tempArrayList.add("Shape the Flowing River"); tempArrayList.add("Sweeping Cinder Strike");
				tempArrayList.add("Water Whip");
				prompt(pw, br, "Choose an Elemental Discipline", tempArrayList, 1);
					break;
				case 17: tempArrayList = new ArrayList<String>();
				tempArrayList.add("Breath of Winter"); tempArrayList.add("River of Hungry Flame");
				tempArrayList.add("Wave of Rolling Earth");
				tempArrayList.add("Eternal Mountain Defense"); tempArrayList.add("Flames of the Phoenix");
				tempArrayList.add("Mist Stance"); tempArrayList.add("Ride the Wind");
				tempArrayList.add("Clench of the North Wind"); tempArrayList.add("Gong of the Summit"); 
				tempArrayList.add("Fangs of the Fire Snake"); tempArrayList.add("Fist of Four Thunders"); 
				tempArrayList.add("Fist of Unbroken Air"); tempArrayList.add("Rush of the Gale Spirits");
				tempArrayList.add("Shape the Flowing River"); tempArrayList.add("Sweeping Cinder Strike");
				tempArrayList.add("Water Whip");
				prompt(pw, br, "Choose an Elemental Discipline", tempArrayList, 1);
					break;
				}
				break;
			}
			break;
		
		case "Paladin": 
			dictClassLevels.put("Paladin", dictClassLevels.get("Paladin") + 1);
			break;
		
		case "Ranger": 
			dictClassLevels.put("Ranger", dictClassLevels.get("Ranger") + 1);
			break;
		
		case "Rogue": 
			dictClassLevels.put("Rogue", dictClassLevels.get("Rogue") + 1);
			break;
		
		case "Sorcerer": 
			dictClassLevels.put("Sorcerer", dictClassLevels.get("Sorcerer") + 1);

			iMaxSorceryPoints = iLevel;
			switch(dictClassLevels.get("Sorcerer")){
			case 2:
				dictFeats.put("Font Of Magic", true);
				break;
			case 3:
				dictFeats.put("metamagic", true);
				break;
			case 6:
				if(sorcerousOrigin.equals("draconic")){
					dictFeats.put("Elemental Affinity", true);
				}
				else if(sorcerousOrigin.equals("Wild Magic")){
					
					dictFeats.put("Bend Luck", true);
				}
				break;
			case 10:
				dictFeats.put("+1 to metamagic", true);
				break;
				
			case 14:
				if(sorcerousOrigin.equals("draconic")){
					dictFeats.put("Dragon Wings", true);
				}
				else if(sorcerousOrigin.equals("wild magic")){
					dictFeats.put("Controlled Chaos", true);
				}
				break;
			case 17:
				dictFeats.put("+1 to metamagic", true);
				break;
				
			case 18:
				if(sorcerousOrigin.equals("draconic")){
					dictFeats.put("Draconic Presence", true);
				}
				else if(sorcerousOrigin.equals("wild magic")){
					dictFeats.put("Spell Bombardment", true);
				}
				break;
			case 20:
				dictFeats.put("Sorcerous Restoration", true);
				break;
		}
		break;
		
		case "Warlock": 
			dictClassLevels.put("Warlock", dictClassLevels.get("Warlock") + 1);
			break;
		
		case "Wizard": 
			dictClassLevels.put("Wizard", dictClassLevels.get("Wizard") + 1);
			break;
		
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
	public String prompt(PrintWriter pw, BufferedReader br, String sTitle, ArrayList<String> options, int numSelectable) throws IOException{
		Prompt prompt = new Prompt(sTitle, options, numSelectable);
		Gson gson = new Gson();
		String writeString = new String();
		writeString = gson.toJson(prompt);
		pw.print(writeString + "<EOF>");
		while(!br.ready()){
			try {
				synchronized(this) {
			        this.wait(10);
				}
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
		pw.print(writeString + "<EOF>");
		while(!br.ready()){
			try {
				wait(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return br.readLine();
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
			//TODO: redo prompting for hit dice with new system
			PrintWriter pw = null;
			BufferedReader br = null;
			try {
				pw = new PrintWriter(actorSocket.getOutputStream());
				br = new BufferedReader( new InputStreamReader(actorSocket.getInputStream()));
				ArrayList<String> tempArrayList = new ArrayList<String>(); 
				for (int i = 0; i < iArrayCurrentHitDice.size(); i++){
					tempArrayList.add(i + "d" + iArrayCurrentHitDice.get(i));
				}
				String diceRolledString = prompt(pw, br, "Spend Hit Dice?", tempArrayList, 1);
				int diceType = Integer.parseInt(diceRolledString.substring(2));
				int diceNumber = Integer.parseInt(diceRolledString.substring(0,1));
				DiceRoller dr = new DiceRoller();
				//iCurrentNumHitDice -= diceNumber;
				switch (diceType) {
				case 4: iHP += (diceNumber * iConMod) + dr.d4(diceNumber, sName, false);
					break;
				case 6: iHP += (diceNumber * iConMod) + dr.d6(diceNumber, sName, false);
					break;
				case 8: iHP += (diceNumber * iConMod) + dr.d8(diceNumber, sName, false);
					break;
				case 10: iHP += (diceNumber * iConMod) + dr.d10(diceNumber, sName, false);
					break;
				case 12: iHP += (diceNumber * iConMod) + dr.d12(diceNumber, sName, false);
					break;
				default:
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(dictClassLevels.get("Sorcerer") == 20){
			if(iCurrentSorceryPoints + 4 >= iMaxSorceryPoints){
				iCurrentSorceryPoints = iMaxSorceryPoints;
			}
			else{
				iCurrentSorceryPoints += 4;
			}
		}
		
		iChannelDivinity = iMaxChannelDivinity;
		
		bSecondWind = true;
		iActionSurge = iMaxActionSurge;
		iSuperiorityDice = iMaxSuperiorityDice;

		iCurrentKi = iMaxKi;
	}
	public void longRest(Socket actorSocket){
		shortRest(actorSocket);
		iHP = iMaxHP;
		iArrayCurrentHitDice = iArrayMaxHitDice;
		iArrayCurrentSpellSlots = iArrayMaxSpellSlots;
		if(dictClassLevels.get("Sorcerer") != 20){
			if(iCurrentSorceryPoints + 2 >= iMaxSorceryPoints){
				iCurrentSorceryPoints = iMaxSorceryPoints;
			}
			else{
				iCurrentSorceryPoints += 2;
			}
		}
		iArrayCurrentSpellSlots = iArrayMaxSpellSlots;
		iIndomitable = iMaxIndomitable;
		iSpellSlots = iMaxSpellSlots;
		
	}
	
	public void determineActions(){
		//TODO Add possible actions & conditions to determineActions
		listActions = new ArrayList<String>();
		listActions.add("Attack");
	}
	
}