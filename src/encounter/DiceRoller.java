package encounter;

import java.math.*;

public class DiceRoller 
{
 	public int dice(int dNumber, int numberRolled, String playerName, boolean isHidden)
	{
		int result = 0;
		for (int i = 0; i < numberRolled; i++)
		{
			result += (int) Math.ceil(dNumber * Math.random());
		}
		
		if(numberRolled == 1){
			TextMessage t = new TextMessage("Server", "Common", playerName + " Rolled a " + result + " on a d" + dNumber + "!");
		}
		else{
			TextMessage t = new TextMessage("Server", "Common", playerName + " Rolled a total of " + result + " out of " + numberRolled + " d" + dNumber + "!");
		}
		
		return result;
	}
}
