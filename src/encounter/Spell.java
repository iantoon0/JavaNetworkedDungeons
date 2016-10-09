package encounter;

public class Spell {
	int range;
	Time castTime;
	//classtype Target (Tile, Monster, Hero, etc.)
	public Spell(){
		range = 0;
		castTime = new Time();
	}
}