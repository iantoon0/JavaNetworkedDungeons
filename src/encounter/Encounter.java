package encounter;

import java.util.ArrayList;

public class Encounter {
	
	ArrayList<EncounterActor> EncounterActors;
	ArrayList<ArrayList<DungeonTile>> EncounterMap;
	
	public Encounter(ArrayList actors){
		EncounterActors = actors;
		for (EncounterActor currentTurnActor : EncounterActors){
			
		}
	}
}
