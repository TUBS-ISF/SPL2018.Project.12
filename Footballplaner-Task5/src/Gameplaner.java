import java.util.*; 


/**
 * Holds List of Games (and results) Creates new Games
 * 
 * @author David
 *
 */
public   class  Gameplaner  extends Object {
	
	
	/**
	 * Constructor
	 * 
	 * @param result
	 */
	public Gameplaner  () {
	
		games = new ArrayList<date>();
		
	
		results = new ArrayList<Result>();
	}

	

	 private String  toString__wrappee__gameplaner  () {
		return "Gameplaner\n";
	}

	
	
	 private String  toString__wrappee__date  () {
		String in = toString__wrappee__gameplaner();
		StringBuilder sb = new StringBuilder();
		sb.append(in);
		for (int i = 0; i < games.size(); i++) {
			sb.append((i + 1)).append(": ");
			sb.append(games.get(i));
			sb.append("\n");
		}
		return sb.toString();
	}

	
	
	public String toString() {
		String in = toString__wrappee__date();
		//System.out.println(in);
		StringBuilder sb = new StringBuilder();
		String[] splitted = in.split("\n");
		sb.append(splitted[0]).append("\n");
		for (int i = 0; i < results.size(); i++) {
			sb.append(splitted[i+1]);
			sb.append(">");
			sb.append(results.get(i));
			sb.append("\n");
		}
		
		return sb.toString();
	}

	
	

	private ArrayList<date> games;

	
	
	 private void  createGame__wrappee__date  (String input) {
		String[] splitted = input.split(">");
		
		date newDate = new date(splitted[0]);
		games.add(newDate);

	}

	
	
	public void createGame(String input) {
		createGame__wrappee__date(input);
		String[] splitted = input.split(">");
		Result newResult;
		if (splitted.length >= 2) {
			newResult = new Result(splitted[1]);
		} else {
			newResult = new Result(-1, -1);
		}
		results.add(newResult);
	}

	
	

	// Getters and Setters
		public ArrayList<date> getGames() {
			return games;
		}

	

		public void setGames(ArrayList<date> games) {
			this.games = games;
		}

	
	
	private ArrayList<Result> results;

	
	
	//Getters and Setters
	public ArrayList<Result> getResults() {
		return results;
	}

	

	public void setResults(ArrayList<Result> results) {
		this.results = results;
	}


}
