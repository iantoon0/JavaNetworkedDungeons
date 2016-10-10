package encounter;

public class Spell {
	int irange;
	char[] componentCharArray;
	Time castTime, durationTime;
	Object activeTarget;
	//classtype Target (Tile, Monster, Hero, etc.)
	public Spell(){
		irange = 0;
		castTime = new Time();
		durationTime = new Time();
	}
}