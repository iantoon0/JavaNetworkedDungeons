package encounter.Classes;

import java.net.Socket;

import encounter.Hero;

public class Cleric extends Hero {
	String sDomain;
	int iChannelDivinity;
	int iMaxChannelDivinity;
	public Cleric(){
		sClassName = "Cleric";
		bSpellcaster = true;
		iMaxHP = 8 + iConMod;
		listProficiencies.add("Simple Weapons");
		listProficiencies.add("Light Armor");
		listProficiencies.add("Medium armor");
		listProficiencies.add("Shields");
		//prompt skill proficiencies 
		//put everthing that a sorcerer gets at level 1 here
	}
	public Cleric(String r){
		super(r);
		sClassName = "Cleric";
		bSpellcaster = true;
		iMaxHP = 8 + iConMod;
		listProficiencies.add("Simple Weapons");
		listProficiencies.add("Light Armor");
		listProficiencies.add("Medium armor");
		listProficiencies.add("Shields");
		//prompt skill proficiencies 
		//put everthing that a sorcerer gets at level 1 here
	}
	public void Levelup(){
		super.levelUp();
		if(iLevel == 2){
			dictFeats.put("Channel Divinity (1/rest)", true);
			switch (sDomain){
			
			case "Knowledge Domain": 
				dictFeats.put("channel Divinity: Knowledge of the Ages", true);
				break;
				
			case "Life Domain": 
				dictFeats.put("channel Divinity: Preserve Life", true);
				break;
				
			case "Light Domain": 
				dictFeats.put("channel Divinity: Radiance of the Dawn", true);
				break;
				
			case "Nature Domain": 
				dictFeats.put("channel Divinity: Charm Animals and Plants", true);
				break;
				
			case "Tempest Domain": 
				dictFeats.put("channel Divinity: Destructive Wrath", true);
				break;
				
			case "Trickery Domain": 
				dictFeats.put("channel Divinity: Invoke Duplicity", true);
				break;
				
			case "War Domain": 
				dictFeats.put("channel Divinity: Guided Strike", true);
				break;
				
			}
		}
		else if(iLevel == 5){
			dictFeats.put("Destroy Undead CR 1/2", true);
		}
		else if(iLevel == 6){
			
			switch (sDomain){
			
			case "Knowledge Domain": 
				dictFeats.put("channel Divinity: Read Thought", true);
				break;
				
			case "Life Domain": 
				dictFeats.put("channel Divinity: Blessed Health", true);
				break;
				
			case "Light Domain": 
				dictFeats.put("Improved Flare", true);
				break;
				
			case "Nature Domain": 
				dictFeats.put("channel Divinity: Dampen Elements", true);
				break;
				
			case "Tempest Domain": 
				dictFeats.put("channel Divinity: Thunderbolt Strike", true);
				break;
				
			case "Trickery Domain": 
				dictFeats.put("channel Divinity: Cloak of Shadows", true);
				break;
				
			case "War Domain": 
				dictFeats.put("channel Divinity: War God's Blessing", true);
				break;
			}
		}
		
			else if(iLevel == 8){
				
				
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
			}
			
		else if(iLevel == 10){
					dictFeats.put("Divine Intervention", true);
				}
		else if(iLevel == 17){
			switch (sDomain)
			{
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
		}
		}
	public  void shortRest(Socket actorSocket){
		super.shortRest(actorSocket);
		iChannelDivinity = iMaxChannelDivinity;
	}
	public void longRest(Socket actorSocket){
		this.shortRest(actorSocket);
		super.longRest(actorSocket);
		iArrayCurrentSpellSlots = iArrayMaxSpellSlots;
	}
}













