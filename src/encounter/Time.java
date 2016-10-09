package encounter;

public class Time {
	int iMinute, iHour, iDay, iMonth, iYear;
	public Time(){
		
	}
	public void fixTime(){
		if(iMinute >= 60){
			iHour++;
			iMinute = 0;
		}
		if(iHour >= 24){
			iDay++;
			iHour = 0;
		}
		if(iDay >= 30){
			iMonth++;
			iDay = 0;
		}
		if(iMonth >= 12){
			iYear++;
			iMonth = 0;
		}
	}
	public void add(Time t){
		this.iMinute += t.iMinute;
		this.iHour += t.iHour;
		this.iDay += t.iDay;
		this.iMonth += t.iMonth;
		this.iYear += t.iYear;
		this.fixTime();
	}
}
