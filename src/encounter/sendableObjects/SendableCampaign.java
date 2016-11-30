package encounter.sendableObjects;

import java.util.ArrayList;

import encounter.Campaign;
import encounter.Dungeon;
import encounter.Hero;
import encounter.Time;

public class SendableCampaign {
	public ArrayList<SendableHero> listParty;
	public Time currentTime;
	public boolean bInEncounter;
	public ArrayList<SendableDungeon> listDungeons;
	public SendableDungeon currentTempDungeon;
	
	public SendableCampaign(Campaign c, boolean bPlayerIsDM, Hero h){
		if (c.listParty != null){
			for(Hero h2 : c.listParty){
				listParty.add(new SendableHero(h2));
			}
		}
		if (c.listDungeons != null){
			for(Dungeon d : c.listDungeons){
				listDungeons.add(new SendableDungeon(d, h));
			}
		}
		if (c.currentDungeon != null){
			currentTempDungeon = new SendableDungeon(c.currentDungeon, h);
		}
		if (c.currentTime != null){
			this.currentTime = c.currentTime;
		}
		this.bInEncounter = c.bInEncounter;
	}
}
