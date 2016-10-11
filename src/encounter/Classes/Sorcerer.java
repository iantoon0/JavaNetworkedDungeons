package encounter.Classes;

import java.net.Socket;

import encounter.Hero;

public class Sorcerer extends Hero {
	String sorcerousOrigin;
	int iCurrentSorceryPoints;
	int iMaxSorceryPoints;
	
	public Sorcerer(){
		sClassName = "Sorcerer";
		bSpellcaster = true;
		iMaxHP = 6 + iConMod;
		listProficiencies.add("Daggers");
		listProficiencies.add("Darts");
		listProficiencies.add("Slings");
		listProficiencies.add("Quarterstaffs");
		listProficiencies.add("Light Crossbows");
		//prompt skill proficiencies 
		//put everthing that a sorcerer gets at level 1 here
		}
	public Sorcerer(String r){
		super(r);
		sClassName = "Sorcerer";
		bSpellcaster = true;
		iMaxHP = 6 + iConMod;
		listProficiencies.add("Daggers");
		listProficiencies.add("Darts");
		listProficiencies.add("Slings");
		listProficiencies.add("Quarterstaffs");
		listProficiencies.add("Light Crossbows");
		//prompt skill proficiencies 
		//put everthing that a sorcerer gets at level 1 here
	}
	public void levelUp(Socket actorSocket){
		super.levelUp(actorSocket);
		iMaxSorceryPoints = iLevel;
		if(iLevel == 2){
			dictFeats.put("Font Of Magic", true);
		}
		else if(iLevel == 3){
			dictFeats.put("metamagic", true);
		}
		else if(iLevel == 6){
			if(sorcerousOrigin.equals("draconic")){
				dictFeats.put("Elemental Affinity", true);
			}
			else if(sorcerousOrigin.equals("wild magic")){
				
				dictFeats.put("Bend Luck", true);
			}
		}
		else if(iLevel == 10){
			dictFeats.put("+1 to metamagic", true);
		}
		else if(iLevel == 14){
			if(sorcerousOrigin.equals("draconic")){
				dictFeats.put("Dragon Wings", true);
			}
			else if(sorcerousOrigin.equals("wild magic")){
				dictFeats.put("Controlled Chaos", true);
			}
		}
		else if(iLevel == 17){
			dictFeats.put("+1 to metamagic", true);
		}
		else if(iLevel == 18){
			if(sorcerousOrigin.equals("draconic")){
				dictFeats.put("Draconic Presence", true);
			}
			else if(sorcerousOrigin.equals("wild magic")){
				dictFeats.put("Spell Bombardment", true);
			}
		}
		else if(iLevel == 20){
			dictFeats.put("Sorcerous Restoration", true);
		}
	}
	public void shortRest(Socket actorSocket){
		super.shortRest(actorSocket);
		if(iLevel == 20){
			if(iCurrentSorceryPoints + 4 >= iMaxSorceryPoints){
				iCurrentSorceryPoints = iMaxSorceryPoints;
			}
			else{
				iCurrentSorceryPoints += 4;
			}
		}
		
		// do all stuff that a sorcerer can do in a short rest
	}
	public void longRest(Socket actorSocket){
		this.shortRest(actorSocket);
		super.longRest(actorSocket);
		if(iLevel != 20){
			if(iCurrentSorceryPoints + 2 >= iMaxSorceryPoints){
				iCurrentSorceryPoints = iMaxSorceryPoints;
			}
			else{
				iCurrentSorceryPoints += 2;
			}
		iArrayCurrentSpellSlots = iArrayMaxSpellSlots;
		}
		// do all stuff that a sorcerer can do in a long rest
	}
	
}
