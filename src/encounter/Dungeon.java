package encounter;
import java.util.*;

public class Dungeon {
	public ArrayList<ArrayList<DungeonTile>> dungeonMap;
	public Encounter[] encounters;
	public int size;
	public Dungeon(int s){
		this.size = s;
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
						dTile.RecursiveLightMethod(null, dTile.lightSources.get(i).iStrength +10 , dTile.lightSources.get(i).iStrength, dTile.lightSources.get(i), this, null, false); 
						for(ArrayList<DungeonTile> dRow2 : dungeonMap){
							for (DungeonTile dTile2 : dRow){
								dTile2.bLightLevelCalculated = false;
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
		System.out.println("");
		System.out.println("");
		for(ArrayList<DungeonTile> dRow : dungeonMap){
			for (DungeonTile dTile : dRow){
				System.out.print(dTile.printLight());
			}
			System.out.println("");
		}
	}
	
}