package appearance;
//#if cmd
import java.io.Console;
//#endif

/**
 * If Feature cmd is checked and the application is started from the command prompt:
 * The Input from the command prompt is used to run specific features
 * @author David
 *
 */
public class cmd {
	//#if cmd
	private planer.Gameplaner gp;
	
	/**
	 * Constructor
	 * @param result	Feature Result checked
	 * @param calendar	Feature Calendar checked
	 * @param gp		Gameplaner instance
	 */
	public cmd(planer.Gameplaner gp) {
		this.gp=gp;
	}
	
	/**
	 * Checks if Application is run from the command prompt.
	 * If so it reads the input
	 * If input is exit, the application stops in commandline.
	 */
	public void runCMD() {
		Console c = System.console();
		if (c == null) {
//			try {
//			Runtime rt = Runtime.getRuntime();
//			rt.exec("cmd.exe /c start java -flag -flag -cp footballPlaner2.jar");
//			}catch(Exception e) {
//				System.out.println("Console didn't start");
//			}
			System.err.println("If Feature Console is checked, start your Application from the console...");
			return;
		}

		String command = "";
		while (!command.equals("exit")) {
			command = c.readLine("Enter your commands: \n(For help type 'help')\n");
			try {
			runCommand(command);
			}catch(Exception e) {
				System.out.println("Wrong input");
			}
		}
	}
	
	/**
	 * Checks the first word as Identifier for the command and extract the second part of the input for the specific method
	 * @param command input from Console
	 */
	private void runCommand(String command) {
		String[] commandSplitted=command.split(" ",2);
		switch(commandSplitted[0]) {
		case "exit":
			System.out.println("Thank you, for using the Football Planer.");
			break;
		case "help":
			System.out.println("Possible Commands are:");
			System.out.println("exit");
			System.out.println("help");
			//#if !result
//@			System.out.println("newGame YYYY.MM.DD hh.mm[>resultP1:resultP2]");
			//#endif
			//#if result
			System.out.println("newGame YYYY.MM.DD hh.mm>resultP1:resultP2");
			System.out.println("changeResult Id resultP1:resultP2");
			//#endif
			//#if calendar
			System.out.println("calendar MM.YYYY");
			//#endif
			System.out.println("gameById Id");
			//#if export
			System.out.println("export");
			//#endif
			//#if import
			System.out.println("import");
			//#endif
			System.out.println("out\n");
			break;
		case "newGame":
			commandNewGame(commandSplitted[1]);
			break;
		case "changeResult":
			commandChangeResult(commandSplitted[1]);
			break;
		case "calendar":
			commandCalendar(commandSplitted[1]);
			break;
		case "gameById":
			commandGameById(commandSplitted[1]);
			break;
		case "out":
			System.out.println(gp);
			break;
		case "export":
			commandExport();
			break;
		case "import":
			commandImport();
			break;
		default:
			break;
		}
	}
	
	/**
	 * Creates a New Game with the inputdata
	 * @param input Date, Time (and result)
	 */
	private void commandNewGame(String input) {
		gp.createGame(input);
		System.out.println("Game: "+input+" was created.");
	}
	
	/**
	 * Changes the Result of a specific game to the given Values
	 * @param input Id and Result: "ID result1:result2"
	 */
	private void commandChangeResult(String input) {
		//#if !result
//@		System.out.println("Feature is not enabled.");
		//#endif
		
		//#if result
		String[] inputSplitted= input.split(" ");
		planer.result result=gp.getResults().get(Integer.parseInt(inputSplitted[0])-1);
		result.setResult(inputSplitted[1]);
		
		System.out.println("Changed Result of Game " + inputSplitted[0] +" to "+ inputSplitted[1]);
		//#endif
	}
	
	/**
	 * Gives back a Calendar for the Year and the Month
	 * @param input Year and Month
	 */
	private void commandCalendar(String input) {
		//#if !calendar
//@		System.out.println("Feature is not enabled.");
		//#endif
		
		//#if calendar
		String[] inputSplitted= input.split("\\.");
		planer.calendar c= new planer.calendar(Integer.parseInt(inputSplitted[1]), Integer.parseInt(inputSplitted[0]), gp);
		System.out.println("\n Calendar: \n"+c);
		//#endif
	}
	
	/**
	 * Checks for the given id, and gives back the Time of the Game and result, if feature is checked
	 * @param input Id to serach for
	 */
	private void commandGameById(String input) {
		System.out.print(gp.getGames().get(Integer.parseInt(input)-1));
		//#if result
			System.out.print(">"+gp.getResults().get(Integer.parseInt(input)-1));
		//#endif
		System.out.println();
	}
	
	/**
	 * Exports to enabled Fileformat
	 */
	private void commandExport() {
		//#if export
		export.exporter.export(gp);
		//#endif
		//#if !export
//@		System.out.println("Feature is not enabled");
		//#endif
	}
	
	/**
	 * Imports from JSON
	 */
	private void commandImport() {
		//#if import
		gp=imports.importer.importfromJson();
		System.out.println("Loaded:");
		System.out.println(gp);
		//#endif
		//#if !import
//@		System.out.println("Feature is not enabled");
		//#endif
	}
	

	//#endif
}
