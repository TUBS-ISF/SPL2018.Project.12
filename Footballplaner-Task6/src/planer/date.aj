package planer;

import java.util.ArrayList;

public aspect date {
	/**
	 * Holds one Date, defined by Date and Time
	 * 
	 * @author David
	 *
	 */
	public static class date_ {
		private int year = 1970;
		private int month = 1;
		private int day = 1;
		private int hours = 0;
		private int minutes = 0;

		/**
		 * Constructor for integer values
		 * 
		 * @param year
		 * @param month
		 * @param day
		 * @param hours
		 * @param minutes
		 */
		public date_(int year, int month, int day, int hours, int minutes) {
			this.year = year;
			this.month = month;
			this.day = day;

			this.hours = hours;
			this.minutes = minutes;

		}

		/**
		 * String Constructor
		 * 
		 * @param gameDate
		 */
		public date_(String gameDate) {
			String[] seperated = gameDate.split(" ");
			if (seperated.length != 0) {
				String[] newDate = seperated[0].split("\\.");
				year = Integer.parseInt(newDate[0]);
				month = Integer.parseInt(newDate[1]);
				day = Integer.parseInt(newDate[2]);
			}
			if (seperated.length > 1) {
				String[] time = seperated[1].split(":");
				hours = Integer.parseInt(time[0]);
				minutes = Integer.parseInt(time[1]);
			}
		}

		/**
		 * ´Gives Back a String of the following format: YYYY.MM.DD
		 * hh:mm[>score1:score2]
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

		// Getters and Setters
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

	private ArrayList<date_> games = new ArrayList<date_>();

	public ArrayList<date_> getGames() {
		return games;
	}

	public void setGames(ArrayList<date_> games) {
		this.games = games;
	}

	void around(String input) : execution(public void Gameplaner.createGame(String)) && args(input) {
		
		proceed(input);
		String[] splitted = input.split(">");
		date_ newDate = new date_(splitted[0]);

		games.add(newDate);
		
	}
	
	String around() : execution(public String Gameplaner.toString()){
		String in = proceed();
		StringBuilder sb = new StringBuilder();
		sb.append(in);
		for (int i = 0; i < games.size(); i++) {
			sb.append((i + 1)).append(": ");
			sb.append(games.get(i));
			sb.append("\n");
		}
		return sb.toString();
	}
	
	void around(String input) : execution(private void appearance.cmd.commandGameById(String)) && args(input){
		//TODO
		System.out.print(games.get(Integer.parseInt(input)-1));
		proceed(input);
	}
	
	ArrayList<date_> around() :execution(public ArrayList<date_> Gameplaner.getGames()){
		proceed();
		return games;
	}
	
	
	void around(ArrayList<date_> d) : execution(public void Gameplaner.setGames(ArrayList<date_>)) &&args(d){
		games=d;
	}
	

	
}
