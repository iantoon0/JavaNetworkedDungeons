package encounter;

import java.util.*;

public class DungeonTile {
	public Point loc;
	public ArrayList<Point> surroundingPoints;
	public ArrayList<LightSource> lightSources;
	public ArrayList<Object> contents, n_Wallcontents, e_Wallcontents, s_Wallcontents, w_WallContents;
	public HashMap<Hero, Boolean> dictHeroVisibility;
	public EncounterActor encounterActor;
	int lightLevel; //0-3, Magic dark/Total dark/Dim/Bright
	boolean wasSeen, wall, lightLevelCalculated;
	public DungeonTile(Dungeon d, Point p){
		dictHeroVisibility = new HashMap<Hero, Boolean>();
		wall = false;
		lightSources = new ArrayList<LightSource>();
		if(wall){
			contents = null;
			n_Wallcontents = null;
			e_Wallcontents = null;
			s_Wallcontents = null;
			w_WallContents = null;
		}
		lightLevel = 1;
		this.loc = p;
		//DETERMINE SURROUNDING POINTS HERE
	}
	public void RecursiveVisionMethod(LinkedList<Point> nextPoints, int visionLengthLeft, Hero h, Dungeon d, Point prevPoint, boolean diagAdd){
		dictHeroVisibility.put(h, true); wasSeen = true;
		int dx = 0;
		int dy = 1;
		if(prevPoint != null){
			dx = Math.abs(prevPoint.x - loc.x);
			dy = Math.abs(prevPoint.y - loc.y);
		}
		if(nextPoints == null){
			for(int i = 0; i < d.dungeonMap.size(); i++){
				for(DungeonTile dTile: d.dungeonMap.get(i)){
					this.RecursiveVisionMethod(getPointQueue(loc,dTile.loc), visionLengthLeft, h, d, null, false);
				}
			}
		}
		else{
			if(wall){
				visionLengthLeft = 0;
			}
			else if(lightLevel == 0){
				if (h.dictFeats.containsKey("Truesight") && visionLengthLeft >= 90){
					visionLengthLeft -= 5;
					if(dx == dy){
						if(diagAdd){
							visionLengthLeft -= 5;
							diagAdd = false;
						}
						else{
							diagAdd = true;
						}
					}
				}
				else{
					visionLengthLeft = 0;
				}
			}
			else if(lightLevel == 1){
				if ((h.dictFeats.containsKey("Truesight")  && visionLengthLeft >= 90)){
					visionLengthLeft -= 5;
					if(dx == dy){
						if(diagAdd){
							visionLengthLeft -= 5;
							diagAdd = false;
						}
						else{
							diagAdd = true;
						}
					}
				}
				else if(h.dictFeats.containsKey("Superior Darkvision") || h.dictFeats.containsKey("Darkvision") && visionLengthLeft >= 60){
					visionLengthLeft -= 10;
					if(dx == dy){
						if(diagAdd){
							visionLengthLeft -= 10;
							diagAdd = false;
						}
						else{
							diagAdd = true;
						}
					}
				}
				else{
					visionLengthLeft = 0;
				}
			}
			else if(lightLevel == 2){
				if ((h.dictFeats.containsKey("Truesight")  && visionLengthLeft >= 90) || h.dictFeats.containsKey("Superior Darkvision") || h.dictFeats.containsKey("Darkvision") && visionLengthLeft >= 60){
					visionLengthLeft -= 5;
					if(dx == dy){
						if(diagAdd){
							visionLengthLeft -= 5;
							diagAdd = false;
						}
						else{
							diagAdd = true;
						}
					}
				}
				else{
					visionLengthLeft -= 10;
					if(dx == dy){
						if(diagAdd){
							visionLengthLeft -= 10;
							diagAdd = false;
						}
						else{
							diagAdd = true;
						}
					}
				}
			}
			else if(lightLevel == 3){
				visionLengthLeft -= 5;
				if(dx == dy){
					if(diagAdd){
						visionLengthLeft -= 5;
						diagAdd = false;
					}
					else{
						diagAdd = true;
					}
				}
			}
			if(visionLengthLeft >= 5){
				//Call RecursiveVisionMethod for surrounding tiles
				if(nextPoints.size() != 0){
					Point p = nextPoints.remove();
					d.dungeonMap.get(p.x).get(p.y).RecursiveVisionMethod(nextPoints, visionLengthLeft, h, d, loc, diagAdd);
				}
			}
		}
	}
	public void RecursiveLightMethod(LinkedList<Point> nextPoints, int strengthLeft, int startStrengthTotal, LightSource l, Dungeon d, Point prevPoint, boolean diagAdd){
		int dx = 0;
		int dy = 1;
		if(prevPoint != null){
			dx = Math.abs(prevPoint.x - loc.x);
			dy = Math.abs(prevPoint.y - loc.y);
		}
		if(nextPoints == null){
			for(int i = 0; i < d.dungeonMap.size(); i++){
				for(DungeonTile dTile: d.dungeonMap.get(i)){
					this.RecursiveLightMethod(getPointQueue(loc,dTile.loc), strengthLeft, startStrengthTotal, l, d, null, false);
				}
			}
		}
		else{
			if(!lightLevelCalculated){

				if (lightLevel + Math.ceil(((double)strengthLeft/(double)startStrengthTotal) * 2) < 3){
					lightLevel += (int) Math.ceil(((double)strengthLeft/(double)startStrengthTotal) * 2);
				}
				else{
					lightLevel = 3;
				}
			}
			else if(1 + Math.ceil(((double)strengthLeft/(double)startStrengthTotal) * 2) > lightLevel){
				lightLevel = 1 + (int) Math.ceil(((double)strengthLeft/(double)startStrengthTotal) * 2);
			}
			if(wall || lightLevel == 0){
				strengthLeft = 0;
			}
			else{
				strengthLeft -= 5;
				if(dx == dy){
					if(diagAdd){
						strengthLeft -= 5;
						diagAdd = false;
					}
					else{
						diagAdd = true;
					}
				}
			}
			if(strengthLeft >= 5){
				//Call RecursiveVisionMethod for surrounding tiles
				if(nextPoints.size() != 0){
					Point p = nextPoints.remove();
					
					d.dungeonMap.get(p.x).get(p.y).RecursiveLightMethod(nextPoints, strengthLeft, startStrengthTotal, l, d, loc, diagAdd);
				}
			}
			lightLevelCalculated = true;
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
		return pointQueue;
	}
	public String print(){
		if(encounterActor != null){
			return "X";
		}
		else if(lightSources.size() != 0){
			return "*";
		}
		return "";
	}
	public String printLight(){
		if(lightSources.size() != 0){
			return "*";
		}
		return "" + lightLevel;
	}
	
}
