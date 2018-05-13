package planer;

import java.util.*;

/**
 * Holds List of Games (and results) Creates new Games
 * 
 * @author David
 *
 */
public class Gameplaner {
	private ArrayList<date> games;
	private ArrayList<result> results;
	boolean result;

	/**
	 * Constructor
	 * 
	 * @param result
	 */
	public Gameplaner(boolean result) {
		games = new ArrayList<date>();
		results = new ArrayList<result>();
		this.result = result;
	}

	/**
	 * Creates a new Game by an well formed input String
	 * 
	 * @param input
	 */
	public void createGame(String input) {
		String[] splitted = input.split(">");
		if (result) {
			result newResult;
			if (splitted.length >= 2) {
				newResult = new result(splitted[1]);
			} else {
				newResult = new result(-1, -1);
			}
			results.add(newResult);
		}
		date newDate = new date(splitted[0]);
		games.add(newDate);
	}

	/**
	 * Gives Back all Games with their IDs
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < games.size(); i++) {
			sb.append((i + 1)).append(": ");
			sb.append(games.get(i));
			if (result) {
				sb.append(">");
				sb.append(results.get(i));
			}
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

	public ArrayList<result> getResults() {
		return results;
	}

	public void setResults(ArrayList<result> results) {
		this.results = results;
	}

}
