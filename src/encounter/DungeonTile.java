package encounter;

import java.util.*;

public class DungeonTile {
	public Point loc;
	public ArrayList<Point> surroundingPoints;
	public ArrayList<Object> contents;
	int lightLevel; //0-4
	boolean visible;
	boolean wasSeen;
	
}
