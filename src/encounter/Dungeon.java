package encounter;
import java.util.*;

public class Dungeon {
	ArrayList<ArrayList<DungeonTile>> dungeonMap;
	Encounter[] encounters;
	int size;
	public Dungeon(int s){
		dungeonMap = new ArrayList<ArrayList<DungeonTile>>(s);
		for(int r = 0; r < s; r++){
			dungeonMap.add(new ArrayList<DungeonTile>());
			for(int c = 0; c < s; c++){
				dungeonMap.get(r).add(new DungeonTile(this, new Point(r,c)));
			}
		}
	}
	public void updateVisibleTiles(){
		for(ArrayList<DungeonTile> dRow : dungeonMap){
			for (DungeonTile dTile : dRow){
				if (dTile.encounterActor != null){
					dTile.RecursiveVisionMethod(null, 120, (Hero) dTile.encounterActor, this, null, false); 
				}
			}
		}
	}
	public void determineLightLevels() throws InterruptedException{
		for(ArrayList<DungeonTile> dRow : dungeonMap){
			for (DungeonTile dTile : dRow){
				if (dTile.lightSources.size() != 0){
					for(int i = 0; i < dTile.lightSources.size(); i++){
						dTile.RecursiveLightMethod(null, dTile.lightSources.get(i).strength, dTile.lightSources.get(i).strength, dTile.lightSources.get(i), this, null, false); 
						for(ArrayList<DungeonTile> dRow2 : dungeonMap){
							for (DungeonTile dTile2 : dRow){
								dTile2.lightLevelCalculated = false;
							}
						}
					}
				}
			}
		}
	}
	public void print(){
		for(ArrayList<DungeonTile> dRow : dungeonMap){
			for (DungeonTile dTile : dRow){
				System.out.print(dTile.print());
			}
			System.out.println("");
		}
	}
}