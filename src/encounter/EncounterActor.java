package encounter;

import java.math.*;
public class EncounterActor {
	public int iStr, iCon, iDex, iInt, iWis, iCha, iStrMod, iConMod, iDexMod, iIntMod, iWisMod, iChaMod,
	iHP, iTempHP, iMaxHP, iInitiative, iInitiativeMod, iArmorClass, iMoveSpeed, iPassivePerception;
	//ClientThread controller;
	void calculateStatMod(){
		this.iStrMod = Math.floorDiv(iStr-10, 2);
		this.iConMod = Math.floorDiv(iCon-10, 2);
		this.iDexMod = Math.floorDiv(iDex-10, 2);
		this.iIntMod = Math.floorDiv(iInt-10, 2);
		this.iWisMod = Math.floorDiv(iWis-10, 2);
		this.iChaMod = Math.floorDiv(iCha-10, 2);
	}
}