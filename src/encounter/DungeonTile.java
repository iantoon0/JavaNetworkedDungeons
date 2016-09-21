package encounter;

import java.util.*;

public class DungeonTile {
	public Point loc;
	public ArrayList<Point> surroundingPoints;
	public ArrayList<Object> contents, n_Wallcontents, e_Wallcontents, s_Wallcontents, w_WallContents;
	public EncounterActor encounterActor;
	int lightLevel; //0-3, Magic dark/Total dark/Dim/Bright
	boolean visible;
	boolean wasSeen;
	boolean wall;
	public DungeonTile(Dungeon d){
		wall = false;
		if(wall){
			contents = null;
			n_Wallcontents = null;
			e_Wallcontents = null;
			s_Wallcontents = null;
			w_WallContents = null;
		}
		lightLevel = 3;
		//DETERMINE SURROUNDING POINTS HERE
	}
	public void RecursiveVisionMethod(byte dir, int visionLengthLeft, Hero h, Dungeon d){
		visible = true; wasSeen = true;
		if(wall){
			visionLengthLeft = 0;
		}
		else if(lightLevel == 0){
			if (h.featsMap.containsKey("Truesight30") && visionLengthLeft >= 90){
				visionLengthLeft -= 5;
			}
			else{
				visionLengthLeft = 0;
			}
		}
		else if(lightLevel == 1){
			if ((h.featsMap.containsKey("Truesight30")  && visionLengthLeft >= 90)){
				visionLengthLeft -= 5;
			}
			else if(h.featsMap.containsKey("Darkvision120") || h.featsMap.containsKey("Darkvision60") && visionLengthLeft >= 60){
				visionLengthLeft -= 10;
			}
			else{
				visionLengthLeft = 0;
			}
		}
		else if(lightLevel == 2){
			if ((h.featsMap.containsKey("Truesight30")  && visionLengthLeft >= 90) || h.featsMap.containsKey("Darkvision120") || h.featsMap.containsKey("Darkvision60") && visionLengthLeft >= 60){
				visionLengthLeft -= 5;
			}
			else{
				visionLengthLeft -= 10;
			}
		}
		else if(lightLevel == 3){
			visionLengthLeft -= 5;
		}
		if(visionLengthLeft >= 5){
			//Call RecursiveVisionMethod for surrounding tiles
			switch(dir){
			case 0:  byte i = 0; for(Point p: surroundingPoints){
				i++; d.dungeonMap.get(p.x).get(p.y).RecursiveVisionMethod((byte) i, visionLengthLeft, h, d);;
			}break;
			case 1: break;/*Up and left*/
			case 2: break;/*Up*/
			case 3: break;/*Up and right*/
			case 4: break;/*Right*/
			case 5: break;/*Down and right*/
			case 6: break;/*Down*/
			case 7: break;/*Down and left*/
			case 8: break;/*Left*/
			}
			
		}
	}
	public String print(){
		if(encounterActor != null){
			return "2";
		}
		if(visible){
			return "1";
		}
		else if (wall){
			return " ";
		}
		else{
			return "0";
		}
	}
}
