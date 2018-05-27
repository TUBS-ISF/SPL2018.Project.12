/**
 * 
 */
package main;

/**
 * Mainclass Checks Propertys Initializes player and starts the choosen
 * appearance
 * 
 * @author David
 *
 */
public class FootballPlaner {
	static planer.Gameplaner gp;

	/**
	 * 
	 * Checks the Propertys Creates a Gameplaner with the information, if Result is
	 * checked Runs cmd if the feature is checked
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Task3");
		gp = new planer.Gameplaner();
		testGames();
		/*
		 * if(calendar) { testCalendar(); }
		 */

		//export.exporter.export(gp);
		//#if cmd
		appearance.cmd console = new appearance.cmd(gp);
		console.runCMD();
		//#endif
	}

	
	/**
	 * Checks if in args are parameter
	 * @param args
	 */
	@Deprecated
	public static void checkArgs(String[] args) {
		/*for(String arg: args) {
			String[] argSplitted = arg.split("=");
			if(argSplitted.length>=2) {
				switch(argSplitted[0]) {
				case "cmd":
					cmd= Boolean.parseBoolean(argSplitted[1]);
					break;
				case "result":
					result= Boolean.parseBoolean(argSplitted[1]);
					break;
				case "calendar":
					calendar= Boolean.parseBoolean(argSplitted[1]);
					break;
				default:
					break;
				}
			}
		}*/
	}
	
	/**
	 *Creates test data
	 */
	public static void testGames() {
		String s1 = "2011.11.11 13:30";
		String s2 = "2009.1.3 12:00>2:1";
		String s3 = "2009.3.3 13:00>2:5";
		String s4 = "2009.3.3 16:00>2:2";
		String s5 = "2009.3.10 13:00>2:1";

		gp.createGame(s1);
		gp.createGame(s2);
		gp.createGame(s3);
		gp.createGame(s4);
		gp.createGame(s5);

		System.out.println(gp.toString());
	}

	/**
	 * creates a calendar for march 2009
	 */
	public static void testCalendar() {
		planer.calendar march2009 = new planer.calendar(2009, 3, gp);
		System.out.println("\n Calendar: \n" + march2009);
	}

}
