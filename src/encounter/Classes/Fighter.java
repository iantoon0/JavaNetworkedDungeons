package encounter.Classes;

import java.io.IOException;
import java.net.Socket;

import encounter.Hero;

public class Fighter extends Hero {
	
	public Fighter(){
		sClassName = "Fighter";
		bSpellcaster = false;
		iMaxHP = 10 + iConMod;
		listProficiencies.add("All Armor");
		listProficiencies.add("Shields");
		listProficiencies.add("Simple Weapons");
		listProficiencies.add("Matial Weapons");
		dictFeats.put("Second Wind", true);
		//prompt fighting style
		//prompt skill proficiencies 
		//put everthing that a sorcerer gets at level 1 here
	}
	public Fighter(String r){
		super(r);
		sClassName = "Fighter";
		bSpellcaster = false;
		iMaxHP = 10 + iConMod;
		listProficiencies.add("All Armor");
		listProficiencies.add("Shields");
		listProficiencies.add("Simple Weapons");
		listProficiencies.add("Matial Weapons");
		dictFeats.put("Second Wind", true);
		//prompt fighting style
		//prompt skill proficiencies 
		//put everthing that a sorcerer gets at level 1 here
	}
	public void levelup(Socket actorSocket) throws IOException{
		super.levelUp(actorSocket);
		if(iLevel == 2){
			dictFeats.put("Action Surge(one use)", true);
		}
		else if(iLevel == 3){
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
		}
		else if(iLevel == 5){
			dictFeats.put("Extra Attack", true);
		}
		else if(iLevel == 7){
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
		}
		else if(iLevel == 9){
			dictFeats.put("Indomitable (one use)", true);
		}
		else if(iLevel == 10){
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
		}
		else if(iLevel == 15){
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
		}
		else if(iLevel == 18){
			switch(sMartialArchetype){
			
			case "Champion": 
				dictFeats.put("Survivor", true);
				break;
				
			case "Eldritch Knight": 
				dictFeats.put("Improved War Magic", true);
				break;
			}
		}
	}
	public  void shortRest(Socket actorSocket){
		super.shortRest(actorSocket);
		bSecondWind = true;
		iActionSurge = iMaxActionSurge;
		iSuperiorityDice = iMaxSuperiorityDice;
		
	}
	public void longRest(Socket actorSocket){
		this.shortRest(actorSocket);
		super.longRest(actorSocket);
		iIndomitable = iMaxIndomitable;
		iSpellSlots = iMaxSpellSlots;
		
	}

}
