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
	public DungeonTile(Dungeon d, Point p){
		wall = false;
		if(wall){
			contents = null;
			n_Wallcontents = null;
			e_Wallcontents = null;
			s_Wallcontents = null;
			w_WallContents = null;
		}
		lightLevel = 3;
		this.loc = p;
		//DETERMINE SURROUNDING POINTS HERE
	}
	public void RecursiveVisionMethod(LinkedList<Point> nextPoints, int visionLengthLeft, Hero h, Dungeon d){
		visible = true; wasSeen = true;
		if(nextPoints == null){
			for(int i = 0; i < d.dungeonMap.size(); i++){
				for(DungeonTile dTile: d.dungeonMap.get(i)){
					System.out.println("Generating line from " + loc + " to "+ dTile.loc);
					System.out.println(getPointQueue(loc,dTile.loc));
					dTile.RecursiveVisionMethod(getPointQueue(loc,dTile.loc), visionLengthLeft, h, d);
				}
			}
			System.out.println("Returning...");
		}
		else{
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
				System.out.println(loc);
				if(nextPoints.size() != 0){
					Point p = nextPoints.remove();
					System.out.println(nextPoints);
					d.dungeonMap.get(p.x).get(p.y).RecursiveVisionMethod(nextPoints, visionLengthLeft, h, d);
				}
			}
		}
	}
	private LinkedList<Point> getPointQueue(Point start, Point end){
		LinkedList<Point> pointQueue = new LinkedList<Point>();
		int w = end.x - start.x ;
	    int h = end.y - start.y ;
	    int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0 ;
	    if (w<0) dx1 = -1 ; else if (w>0) dx1 = 1 ;
	    if (h<0) dy1 = -1 ; else if (h>0) dy1 = 1 ;
	    if (w<0) dx2 = -1 ; else if (w>0) dx2 = 1 ;
	    int longest = Math.abs(w) ;
	    int shortest = Math.abs(h) ;
	    if (!(longest>shortest)) {
	        longest = Math.abs(h) ;
	        shortest = Math.abs(w) ;
	        if (h<0) dy2 = -1 ; else if (h>0) dy2 = 1 ;
	        dx2 = 0 ;            
	    }
	    Point p = new Point();
	    p.x = start.x;p.y = start.y;
	    int numerator = longest >> 1 ;
	    for (int i=0;i<=longest;i++) {
	    	pointQueue.add(new Point(p.x,p.y));
	    	System.out.println("Added Point: " +p);
	    	numerator += shortest ;
	        if (!(numerator<longest)) {
	            numerator -= longest ;
	            p.x += dx1 ;
	            p.y += dy1 ;
	        } else {
	            p.x += dx2 ;
	            p.y += dy2 ;
	        }
	    }
	    System.out.println("PointQueue:" + pointQueue);
		return pointQueue;
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
