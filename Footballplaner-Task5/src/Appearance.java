import java.io.Console; public   class  Appearance {
	

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
	 private void  commandNewGame__wrappee__cmd  (String input) {
		System.out.println("Try creating Game...");
	}

	
	private void commandNewGame(String input) {
		commandNewGame__wrappee__cmd(input);
		gp.createGame(input);
		System.out.println("Game: "+input+" was created.");
	}

	

	/**
	 * Changes the Result of a specific game to the given Values
	 * 
	 * @param input
	 *            Id and Result: "ID result1:result2"
	 */
	 private void  commandChangeResult__wrappee__cmd  (String input) {
		System.out.println("Try change Result...");
	}

	
	private void commandChangeResult(String input) {
		commandChangeResult__wrappee__cmd(input);
		String[] inputSplitted= input.split(" ");
		Result result=gp.getResults().get(Integer.parseInt(inputSplitted[0])-1);
		result.setResult(inputSplitted[1]);
		
		System.out.println("Changed Result of Game " + inputSplitted[0] +" to "+ inputSplitted[1]);
	}

	

	/**
	 * Gives back a Calendar for the Year and the Month
	 * 
	 * @param input
	 *            Year and Month
	 */
	 private void  commandCalendar__wrappee__cmd  (String input) {
		System.out.println("Try creating Calendar...");
	}

	
	private void commandCalendar(String input) {
		commandCalendar__wrappee__cmd(input);
		String[] inputSplitted= input.split("\\.");
		Calendar c= new Calendar(Integer.parseInt(inputSplitted[1]), Integer.parseInt(inputSplitted[0]), gp);
		System.out.println("\n Calendar: \n"+c);
	}

	

	/**
	 * Checks for the given id, and gives back the Time of the Game and result, if
	 * feature is checked
	 * 
	 * @param input
	 *            Id to serach for
	 */
	 private void  commandGameById__wrappee__cmd  (String input) {
		System.out.println();
	}

	
	
	 private void  commandGameById__wrappee__date  (String input) {
		System.out.print(gp.getGames().get(Integer.parseInt(input)-1));
		commandGameById__wrappee__cmd(input);
	}

	
	
	private void commandGameById(String input) {
		commandGameById__wrappee__date(input);
		System.out.print(">"+gp.getResults().get(Integer.parseInt(input)-1));
	}

	

	/**
	 * Exports to enabled Fileformat
	 */
	 private void  commandExport__wrappee__cmd  () {
		System.out.println("Try exporting...");
	}

	

	private void commandExport() {
		commandExport__wrappee__cmd();
		Exporter.export(gp);
	}

	

	/**
	 * Imports from JSON
	 */
	 private void  commandImport__wrappee__cmd  () {
		System.out.println("Try importing...");
	}

	
	
	private void commandImport() {
		commandImport__wrappee__cmd();
		gp=Importer.importfromJson();
		System.out.println("Loaded:");
		System.out.println(gp);
	}

	

	 private void  commandAddTeam__wrappee__cmd  (String input) {
		System.out.println("Try adding Team...");
	}

	
	
	private void commandAddTeam(String input) {
		commandAddTeam__wrappee__cmd(input);
		Main.getTeamList().addTeam(new Team(input));
		System.out.println("Try adding Team...");
	}

	

	 private void  commandTeams__wrappee__cmd  () {
		System.out.println("Try getting Teams...");
	}

	
	
	private void commandTeams() {
		commandTeams__wrappee__cmd();
		System.out.println(Main.getTeamList().toString());
	}

	

	 private void  commandAddTrainer__wrappee__cmd  (String input) {
		System.out.println("Try adding Trainer...");
	}

	
	private void commandAddTrainer(String input) {
		commandAddTrainer__wrappee__cmd(input);
		String[] splitted= input.split(" ",2);
		if (splitted.length==2) {
			Main.getTeamList().getTeams().get(Integer.parseInt(splitted[0])).setTrainer(splitted[1]);
		}else {
			System.out.println("Wrong input!");
		}
	}

	

	 private void  commandAddPlayer__wrappee__cmd  (String input) {
		System.out.println("Try adding Player...");
	}

	
	private void commandAddPlayer(String input) {
		commandAddPlayer__wrappee__cmd(input);
		String[] splitted= input.split(" ",2);
		if (splitted.length==2) {
			Main.getTeamList().getTeams().get(Integer.parseInt(splitted[0])).addPlayer(splitted[1]);
		}else {
			System.out.println("Wrong input!");
		}
	}

	
	
	static TeamList teamList;


}
