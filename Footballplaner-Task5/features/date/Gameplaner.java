import java.util.*;

/**
 * Holds List of Games (and results) Creates new Games
 * 
 * @author David
 *
 */
public class Gameplaner extends Object{
	

	private ArrayList<date> games;
	
	/**
	 * Constructor
	 * 
	 * @param result
	 */
	public Gameplaner() {
		games = new ArrayList<date>();
		
	}
	
	public void createGame(String input) {
		String[] splitted = input.split(">");
		
		date newDate = new date(splitted[0]);
		games.add(newDate);

	}
	
	public String toString() {
		String in = original();
		StringBuilder sb = new StringBuilder();
		sb.append(in);
		for (int i = 0; i < games.size(); i++) {
			sb.append((i + 1)).append(": ");
			sb.append(games.get(i));
			sb.append("\n");
		}
		return sb.toString();
	}
	

	// Getters and Setters
		public ArrayList<date> getGames() {
			return games;
		}

		public void setGames(ArrayList<date> games) {
			this.games = games;
		}

	
}