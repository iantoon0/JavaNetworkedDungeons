package encounter;

import java.math.*;

public class DiceRoller {
	public int d20(int numberRolled){
		int result = 0;
		for (int i = 0; i < numberRolled; i++){
			result += (int) Math.ceil(20 * Math.random());
		}
		return result;
	}
	public int d4(int numberRolled){
		int result = 0;
		for (int i = 0; i < numberRolled; i++){
			result += (int) Math.ceil(4 * Math.random());
		}
		return result;
	}
	public int d6(int numberRolled){
		int result = 0;
		for (int i = 0; i < numberRolled; i++){
			result += (int) Math.ceil(6 * Math.random());
		}
		return result;
	}
	public int d8(int numberRolled){
		int result = 0;
		for (int i = 0; i < numberRolled; i++){
			result += (int) Math.ceil(8 * Math.random());
		}
		return result;
	}
	public int d10(int numberRolled){
		int result = 0;
		for (int i = 0; i < numberRolled; i++){
			result += (int) Math.ceil(10 * Math.random());
		}
		return result;
	}
	public int d12(int numberRolled){
		int result = 0;
		for (int i = 0; i < numberRolled; i++){
			result += (int) Math.ceil(12 * Math.random());
		}
		return result;
	}
	public int d100(int numberRolled){
		int result = 0;
		for (int i = 0; i < numberRolled; i++){
			result += (int) Math.ceil(100 * Math.random());
		}
		return result;
	}
}
