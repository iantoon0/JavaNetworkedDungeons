package encounter;

import java.util.ArrayList;

public class Prompt {
	String sPromptTitle;
	ArrayList<String> sPromptOptions;
	int iNumSelectable;
	public Prompt(String sPromptTitle, ArrayList<String> sPromptOptions, int iNumSelectable){
		this.sPromptTitle = sPromptTitle;
		this.sPromptOptions = sPromptOptions;
		this.iNumSelectable = iNumSelectable;
	}
}
