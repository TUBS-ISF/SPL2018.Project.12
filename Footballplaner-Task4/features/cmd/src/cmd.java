import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import interfaces.IGame;
import interfaces.calendars;
import interfaces.export;
import interfaces.importer;
import interfaces.planer;

public class cmd implements interfaces.appearance {
	private planer gp;
	private importer im;
	private List<export> ex;
	private List<IGame> g;
	private List<calendars> c;

	public cmd() {}
	
	/**
	 * Constructor
	 * 
	 * @param result
	 *            Feature Result checked
	 * @param calendar
	 *            Feature Calendar checked
	 * @param gp
	 *            Gameplaner instance
	 */
	public cmd(planer gp) {
		this.gp = gp;
	}

	public void run() {
		if (gp == null) {
			System.err.println("No Planer set!");
			return;
		}
		runCMD();
	}

	public void setPlaner(planer p) {
		this.gp = p;
	}

	public void setImporter(importer im) {
		this.im = im;
	}

	public void setExporter(List<export> ex) {
		this.ex = ex;
	}

	/**
	 * Checks if Application is run from the command prompt. If so it reads the
	 * input If input is exit, the application stops in commandline.
	 */
	private void runCMD() {
		Console c = System.console();
		if (c == null) {
			// try {
			// Runtime rt = Runtime.getRuntime();
			// rt.exec("cmd.exe /c start java -flag -flag -cp footballPlaner2.jar");
			// }catch(Exception e) {
			// System.out.println("Console didn't start");
			// }
			System.err.println("If Feature Console is checked, start your Application from the console...");
			return;
		}

		String command = "";
		while (!command.equals("exit")) {
			command = c.readLine("Enter your commands: \n(For help type 'help')\n");
			try {
				runCommand(command);
			} catch (Exception e) {
				System.out.println("Wrong input");
			}
		}
	}

	/**
	 * Checks the first word as Identifier for the command and extract the second
	 * part of the input for the specific method
	 * 
	 * @param command
	 *            input from Console
	 */
	private void runCommand(String command) {
		String[] commandSplitted = command.split(" ", 2);
		switch (commandSplitted[0]) {
		case "exit":
			System.out.println("Thank you, for using the Football Planer.");
			break;
		case "help":
			System.out.println("Possible Commands are:");
			System.out.println("exit");
			System.out.println("help");
			// @ System.out.println("newGame YYYY.MM.DD hh.mm[>resultP1:resultP2]");
			System.out.println("newGame YYYY.MM.DD hh.mm>resultP1:resultP2");
			System.out.println("changeResult Id resultP1:resultP2");
			System.out.println("calendar MM.YYYY");
			System.out.println("gameById Id");
			System.out.println("export");
			System.out.println("import");
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
	 * 
	 * @param input
	 *            Date, Time (and result)
	 */
	private void commandNewGame(String input) {
		gp.createGame(input);
		System.out.println("Game: " + input + " was created.");
	}

	/**
	 * Changes the Result of a specific game to the given Values
	 * 
	 * @param input
	 *            Id and Result: "ID result1:result2"
	 */
	private void commandChangeResult(String input) {
		// @ System.out.println("Feature is not enabled.");
		String[] inputSplitted = input.split(" ");
		ArrayList<ArrayList<IGame>> games = gp.getGames();
		if (games.size() > 1) {
			IGame result = games.get(1).get(Integer.parseInt(inputSplitted[0]) - 1);
			result = result.setGame(inputSplitted[1]);

			System.out.println("Changed Result of Game " + inputSplitted[0] + " to " + inputSplitted[1]);
		} else {
			System.out.println("Feature is not enabled.");
		}
	}

	/**
	 * Gives back a Calendar for the Year and the Month
	 * 
	 * @param input
	 *            Year and Month
	 */
	private void commandCalendar(String input) {
		// @ System.out.println("Feature is not enabled.");
		
		 String[] inputSplitted= input.split("\\.");
		 for (calendars cal:c) {
			 interfaces.calendars calOut = cal.createCalendar(Integer.parseInt(inputSplitted[1]), Integer.parseInt(inputSplitted[0]), gp);
			 System.out.println("\n Calendar: \n"+calOut);
		 }
//		 interfaces.calendars c= new planer.calendar(Integer.parseInt(inputSplitted[1]),
//		 Integer.parseInt(inputSplitted[0]), gp);
		 
	}

	/**
	 * Checks for the given id, and gives back the Time of the Game and result, if
	 * feature is checked
	 * 
	 * @param input
	 *            Id to serach for
	 */
	private void commandGameById(String input) {
		ArrayList<ArrayList<IGame>> games = gp.getGames();
		System.out.print(games.get(0).get(Integer.parseInt(input) - 1));
		if (games.size() > 1) {
			System.out.print(">" + games.get(1).get(Integer.parseInt(input) - 1));
		}
		System.out.println();
	}

	/**
	 * Exports to enabled Fileformat
	 */
	private void commandExport() {
		if (ex == null || ex.size() == 0) {
			System.out.println("Feature is not enabled! Aborting..");
			return;
		}
		for (export e : ex) {
			e.exportPlaner(gp);
		}
		// @ System.out.println("Feature is not enabled");
	}

	/**
	 * Imports from JSON
	 */
	private void commandImport() {
		if (im == null) {
			System.out.println("Feature is not enabled! Aborting!");
			return;
		}
		gp = im.importfrom(gp,g);
		// gp=imports.importer.importfromJson();
		System.out.println("Loaded:");
		System.out.println(gp);
		// @ System.out.println("Feature is not enabled");
	}

	@Override
	public void setIGame(List<IGame> g) {
		this.g=g;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCalendars(List<calendars> c) {
		this.c=c;
		// TODO Auto-generated method stub
		
	}
}
