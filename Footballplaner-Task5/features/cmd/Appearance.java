import java.io.Console;

public class Appearance {

	private Gameplaner gp;

	public Appearance(Gameplaner gp) {
		this.gp = gp;
	}

	public void run() {
		Console c = System.console();
		if (c == null) {
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
			System.out.println("newGame YYYY.MM.DD hh.mm[>resultP1:resultP2]");
			System.out.println("newGame YYYY.MM.DD hh.mm>resultP1:resultP2");
			System.out.println("changeResult Id resultP1:resultP2");
			System.out.println("calendar MM.YYYY");
			System.out.println("gameById Id");
			System.out.println("export");
			System.out.println("import");
			System.out.println("addTeam");
			System.out.println("teams");
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
		case "addTeam":
			commandAddTeam(commandSplitted[1]);
			break;
		case "addTrainer":
			commandAddTrainer(commandSplitted[1]);
			break;
		case "addPlayer":
			commandAddPlayer(commandSplitted[1]);
			break;
		case "teams":
			commandTeams();
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
		System.out.println("Try creating Game...");
	}

	/**
	 * Changes the Result of a specific game to the given Values
	 * 
	 * @param input
	 *            Id and Result: "ID result1:result2"
	 */
	private void commandChangeResult(String input) {
		System.out.println("Try change Result...");
	}

	/**
	 * Gives back a Calendar for the Year and the Month
	 * 
	 * @param input
	 *            Year and Month
	 */
	private void commandCalendar(String input) {
		System.out.println("Try creating Calendar...");
	}

	/**
	 * Checks for the given id, and gives back the Time of the Game and result, if
	 * feature is checked
	 * 
	 * @param input
	 *            Id to serach for
	 */
	private void commandGameById(String input) {
		System.out.println();
	}

	/**
	 * Exports to enabled Fileformat
	 */
	private void commandExport() {
		System.out.println("Try exporting...");
	}

	/**
	 * Imports from JSON
	 */
	private void commandImport() {
		System.out.println("Try importing...");
	}

	private void commandAddTeam(String input) {
		System.out.println("Try adding Team...");
	}

	private void commandTeams() {
		System.out.println("Try getting Teams...");
	}

	private void commandAddTrainer(String input) {
		System.out.println("Try adding Trainer...");
	}

	private void commandAddPlayer(String input) {
		System.out.println("Try adding Player...");
	}
}