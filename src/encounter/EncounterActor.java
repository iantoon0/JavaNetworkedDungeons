package encounter;

import java.math.*;
public class EncounterActor {
	public int str, con, dex, intel, wis, cha, 
	strMod, conMod, dexMod, intelMod, wisMod, chaMod,
	hp, tempHP, maxHP, initiativeMod, initiative, ac, moveSpeed;
	//ClientThread controller;
	void calculateStatMod(){
		this.strMod = Math.floorDiv(str-10, 2);
		this.conMod = Math.floorDiv(con-10, 2);
		this.dexMod = Math.floorDiv(dex-10, 2);
		this.intelMod = Math.floorDiv(intel-10, 2);
		this.wisMod = Math.floorDiv(wis-10, 2);
		this.chaMod = Math.floorDiv(cha-10, 2);
	}
}