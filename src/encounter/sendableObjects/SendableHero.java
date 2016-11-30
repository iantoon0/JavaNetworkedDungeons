package encounter.sendableObjects;

import java.util.ArrayList;

import encounter.Hero;
import encounter.Spell;

public class SendableHero {
	public int iLevel, iXP, iProficiencyBonus, iNextLvlXP, iGold,iHPGainedPerLevel, iSize;
	public int[] iArrayCurrentSpellSlots, iArrayMaxSpellSlots;
	public ArrayList<Integer> iArrayMaxHitDice, iArrayCurrentHitDice;
	public boolean bInspiration;
	public String sName, sRace, sClassName;
	public ArrayList<String> listProficiencies, listCantripsKnown, listLanguages, listSkillProficiencies, listSpellsKnown, listSpellsPrepared, listFeats, listBackgroundTraits, listInventory;
	
	public SendableHero(Hero h){
		this.iLevel = h.iLevel;
		this.iXP = h.iXP;
		this.iProficiencyBonus = h.iProficiencyBonus;
		this.iNextLvlXP = h.iNextLvlXP;
		this.iGold = h.iGold;
		this.iHPGainedPerLevel = h.iHPGainedPerLevel;
		this.iSize = h.iSize;
		this.iArrayCurrentHitDice = h.iArrayCurrentHitDice;
		this.iArrayMaxHitDice = h.iArrayMaxHitDice;
		this.bInspiration = h.bInspiration;
		this.sName = h.sName;
		this.sRace = h.sRace;
		this.sClassName = h.sClassName;
		this.listProficiencies = h.listProficiencies;
		this.listCantripsKnown = h.listCantripsKnown;
		this.listLanguages = h.listLanguages;
		this.listSkillProficiencies = h.listSkillProficiencies;
		for(Spell s : h.listSpellsKnown){
		//	this.listSpellsKnown.add();
		}
	}
}
