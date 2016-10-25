package encounter;

import java.math.*;

public class DiceRoller {
	public int d20(int numberRolled, String playerName, boolean isHidden){
		int result = 0;
		for (int i = 0; i < numberRolled; i++){
			result += (int) Math.ceil(20 * Math.random());
		}
		if(numberRolled == 1){
			TextMessage t = new TextMessage("Server", "Common", playerName + " Rolled a " + result + " on a d20!");
		}
		else{
			TextMessage t = new TextMessage("Server", "Common", playerName + " Rolled a total of " + result + " out of " + numberRolled + " d20!");
		}
		return result;
	}
	public int d4(int numberRolled, String playerName, boolean isHidden){
		int result = 0;
		for (int i = 0; i < numberRolled; i++){
			result += (int) Math.ceil(4 * Math.random());
		}
		if(numberRolled == 1){
			TextMessage t = new TextMessage("Server", "Common", playerName + " Rolled a " + result + " on a d4!");
		}
		else{
			TextMessage t = new TextMessage("Server", "Common", playerName + " Rolled a total of " + result + " out of " + numberRolled + " d4!");
		}
		return result;
	}
	public int d6(int numberRolled, String playerName, boolean isHidden){
		int result = 0;
		for (int i = 0; i < numberRolled; i++){
			result += (int) Math.ceil(6 * Math.random());
		}
		if(numberRolled == 1){
			TextMessage t = new TextMessage("Server", "Common", playerName + " Rolled a " + result + " on a d6!");
		}
		else{
			TextMessage t = new TextMessage("Server", "Common", playerName + " Rolled a total of " + result + " out of " + numberRolled + " d6!");
		}
		return result;
	}
	public int d8(int numberRolled, String playerName, boolean isHidden){
		int result = 0;
		for (int i = 0; i < numberRolled; i++){
			result += (int) Math.ceil(8 * Math.random());
		}
		if(numberRolled == 1){
			TextMessage t = new TextMessage("Server", "Common", playerName + " Rolled a " + result + " on a d8!");
		}
		else{
			TextMessage t = new TextMessage("Server", "Common", playerName + " Rolled a total of " + result + " out of " + numberRolled + " d8!");
		}
		return result;
	}
	public int d10(int numberRolled, String playerName, boolean isHidden){
		int result = 0;
		for (int i = 0; i < numberRolled; i++){
			result += (int) Math.ceil(10 * Math.random());
		}
		if(numberRolled == 1){
			TextMessage t = new TextMessage("Server", "Common", playerName + " Rolled a " + result + " on a d10!");
		}
		else{
			TextMessage t = new TextMessage("Server", "Common", playerName + " Rolled a total of " + result + " out of " + numberRolled + " d10!");
		}
		return result;
	}
	public int d12(int numberRolled, String playerName, boolean isHidden){
		int result = 0;
		for (int i = 0; i < numberRolled; i++){
			result += (int) Math.ceil(12 * Math.random());
		}
		if(numberRolled == 1){
			TextMessage t = new TextMessage("Server", "Common", playerName + " Rolled a " + result + " on a d12!");
		}
		else{
			TextMessage t = new TextMessage("Server", "Common", playerName + " Rolled a total of " + result + " out of " + numberRolled + " d12!");
		}
		return result;
	}
	public int d100(int numberRolled, String playerName, boolean isHidden){
		int result = 0;
		for (int i = 0; i < numberRolled; i++){
			result += (int) Math.ceil(100 * Math.random());
		}
		if(numberRolled == 1){
			TextMessage t = new TextMessage("Server", "Common", playerName + " Rolled a " + result + " on a d100!");
		}
		else{
			TextMessage t = new TextMessage("Server", "Common", playerName + " Rolled a total of " + result + " out of " + numberRolled + " d100!");
		}
		
		return result;
	}
}
