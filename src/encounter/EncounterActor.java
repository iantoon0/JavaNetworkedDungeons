package encounter;

import java.math.*;
public class EncounterActor {
	public int str, con, dex, intel, wis, cha, 
	strBon, conBon, dexBon, intelBon, wisBon, chaBon,
	hp, tempHP, maxHP, initiativeBon, initiative, ac, moveSpeed;
	ClientThread controller;
	void calculateStatBon(){
		this.strBon = Math.floorDiv(str-10, 2);
		this.conBon = Math.floorDiv(con-10, 2);
		this.dexBon = Math.floorDiv(dex-10, 2);
		this.intelBon = Math.floorDiv(intel-10, 2);
		this.wisBon = Math.floorDiv(wis-10, 2);
		this.chaBon = Math.floorDiv(cha-10, 2);
	}
}