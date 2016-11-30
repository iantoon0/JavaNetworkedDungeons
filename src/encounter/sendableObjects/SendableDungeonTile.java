package encounter.sendableObjects;

import java.util.ArrayList;

import encounter.DungeonTile;
import encounter.Hero;
import encounter.LightSource;
import encounter.Monster;
import encounter.Point;

public class SendableDungeonTile {
	public Point loc;
	public ArrayList<Point> surroundingPoints;
	public ArrayList<LightSource> lightSources;
	public SendableHero h;
	public Monster m;
	public int iLightLevel;
	public boolean bVisible, bWasSeen, bWall, bIsDifficultTerrain;
	
	public SendableDungeonTile(DungeonTile d, Hero h){
		this.loc = d.loc;
		this.surroundingPoints = d.surroundingPoints;
		this.lightSources = d.lightSources;
		if(d.encounterActor != null && d.encounterActor.getClass() == Hero.class){
			this.h = new SendableHero((Hero) d.encounterActor);
		}
		else if(d.encounterActor != null && d.encounterActor.getClass() == Monster.class){
			m = (Monster) d.encounterActor;
		}
		this.iLightLevel = d.lightLevel;
		if(d.dictHeroVisibility.containsKey(h)){
			this.bVisible = d.dictHeroVisibility.get(h);
		}
		else{
			bVisible = false;
		}
		this.bWasSeen = d.bWasSeen;
		this.bWall = d.bWall;
		this.bIsDifficultTerrain = d.bIsDifficultTerrain;
	}
}
