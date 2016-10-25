package encounter;

public class TextMessage {
	public String sLanguage, sContents, sNameSpeaker;
	public TextMessage(String sNameSpeaker, String lang, String contents){
		this.sNameSpeaker = sNameSpeaker;
		this.sContents = contents;
		this.sLanguage = lang;
	}
}
