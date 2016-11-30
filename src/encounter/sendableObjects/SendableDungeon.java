package encounter.sendableObjects;

import java.util.ArrayList;

import encounter.Dungeon;
import encounter.DungeonTile;
import encounter.Hero;

public class SendableDungeon {
	public ArrayList<SendableDungeonTile> dungeonMap;
	public int size;
	
	public SendableDungeon (Dungeon d, Hero h){
		this.size = d.size;
		System.out.println("Creating new SendableDungeon");
		this.dungeonMap = new ArrayList<SendableDungeonTile>();
		for(int r = 0; r < d.size; r++){
			for(int c = 0; c < d.size; c++){
				this.dungeonMap.add(new SendableDungeonTile(d.dungeonMap.get(r).get(c), h));
			}
		}
	}
}
