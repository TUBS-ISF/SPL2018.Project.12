
/**
 * Holds one Date, defined by Date and Time
 * @author David
 *
 */
public  class  date {
	
	private int year=1970;

	
	private int month=1;

	
	private int day=1;

	
	private int hours=0;

	
	private int minutes=0;

	
	
	/**
	 * Constructor for integer values
	 * @param year
	 * @param month
	 * @param day
	 * @param hours
	 * @param minutes
	 */
	public date(int year, int month, int day, int hours, int minutes) {
		this.year= year;
		this.month=month;
		this.day=day;
		
		this.hours=hours;
		this.minutes=minutes;
		
	}

	
	
	/**
	 * String Constructor
	 * @param gameDate
	 */
	public date(String gameDate) {
		String[] seperated = gameDate.split(" ");
		if(seperated.length!=0) {
			String[] newDate = seperated[0].split("\\.");
			year=Integer.parseInt(newDate[0]);
			month=Integer.parseInt(newDate[1]);
			day = Integer.parseInt(newDate[2]);
		}
		if(seperated.length>1) {
			String[] time = seperated[1].split(":");
			hours =Integer.parseInt(time[0]);
			minutes =Integer.parseInt(time[1]);
		}
	}

	
	
	/**
	 * ´Gives Back a String of the following format:
	 * YYYY.MM.DD hh:mm[>score1:score2]
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(year).append(".");
		sb.append(month).append(".");
		sb.append(day).append(" ");
		
		sb.append(hours).append(":");
		sb.append(minutes);
		
		return sb.toString();
	}

	


	//Getters and Setters
	public int getYear() {
		return year;
	}

	

	public void setYear(int year) {
		this.year = year;
	}

	

	public int getMonth() {
		return month;
	}

	

	public void setMonth(int month) {
		this.month = month;
	}

	

	public int getDay() {
		return day;
	}

	

	public void setDay(int day) {
		this.day = day;
	}

	

	public int getHours() {
		return hours;
	}

	

	public void setHours(int hours) {
		this.hours = hours;
	}

	

	public int getMinutes() {
		return minutes;
	}

	

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}


}
