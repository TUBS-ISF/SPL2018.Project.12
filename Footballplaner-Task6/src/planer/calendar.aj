package planer;

import java.util.ArrayList;

import planer.date.date_;


public aspect calendar {
	public class Calendar{
		private int[] daysPerMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
			
			day[] calenderMonth;
			int days;
			
			/**
			 * Holds all Games of one Day
			 * @author David
			 *
			 */
			private class day {
				ArrayList<Integer> id;
				ArrayList<Integer> hours;
				ArrayList<Integer> minutes;
				
				/**
				 * Cosntructor
				 */
				public day() {
					id = new ArrayList<Integer>();
					hours = new ArrayList<Integer>();
					minutes = new ArrayList<Integer>();
				}
				
				/**
				 * Adds a Game to the Day
				 * @param id
				 * @param hours
				 * @param minutes
				 */
				public void addDate(int id, int hours, int minutes) {
					this.id.add(id);
					this.hours.add(hours);
					this.minutes.add(minutes);
				}
				
				/**
				 * Gives back the Game ID and the Time of the Game
				 */
				public String toString() {
					StringBuilder sb = new StringBuilder();
					for (int i=0; i<id.size();i++) {
						sb.append(id.get(i)+1).append(": ");
						sb.append(hours.get(i)).append(":");
						sb.append(minutes.get(i)).append("\n");
					}
					return sb.toString();
				}
			}
			
			/**
			 * Creates a Calendar with all games for every day of the given Month
			 * @param year
			 * @param month
			 * @param gp Gameplaner
			 */
			public Calendar(int year, int month, Gameplaner gp) {
				days =daysPerMonth[month];
				if (month == 2 && (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))) {
					days=29;
				}
				calenderMonth= new day[days];
				for (int i=0;i<days;i++) {
					calenderMonth[i]=new day();
				}
				ArrayList<date_> dates=gp.getGames();
				for (int i = 0; i<dates.size(); i++) {
					if(dates.get(i).getYear()==year&&dates.get(i).getMonth()==month) {
						calenderMonth[dates.get(i).getDay()].addDate(i, dates.get(i).getHours(), dates.get(i).getMinutes());
					}
				}
			}

			/**
			 * Prints every day, followed by its games
			 */
			public String toString() {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i<days;i++) {
					sb.append("Day: ").append(i+1).append("\n");
					sb.append(calenderMonth[i]).append("\n");
				}
				return sb.toString();
			}
		}
	
	void around(): execution(public static void Main.Main.test()){
		proceed();
		Calendar march2009 = new Calendar(2009, 3, Main.Main.getGp());
		System.out.println("\n Calendar: \n" + march2009);
	}
	
	void around(String input):execution(private void appearance.cmd.commandCalendar(String)) && args(input){
		proceed(input);
		String[] inputSplitted= input.split("\\.");
		Calendar c= new Calendar(Integer.parseInt(inputSplitted[1]), Integer.parseInt(inputSplitted[0]), Main.Main.getGp());
		System.out.println("\n Calendar: \n"+c);
	}
	
	
}
