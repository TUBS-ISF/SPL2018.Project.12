import java.util.ArrayList;
import java.util.List;

import interfaces.IGame;

public class gameplaner implements interfaces.planer {

	private ArrayList<ArrayList<IGame>> games;
	private List<IGame> g;

//	public planer createPlaner(int size, List<IGame> g) {
//		return new gameplaner(size,g);
//	}

	public gameplaner() {}
	
//	/**
//	 * Constructor
//	 * 
//	 * @param result
//	 */
//	public gameplaner(int size, List<IGame> g) {
//		games = new ArrayList<ArrayList<IGame>>();
//		for (int i = 0; i < size; i++) {
//			games.add(new ArrayList<IGame>());
//		}
//		this.g = g;
//	}
	
	
	public void setIGame(List<IGame> g) {
		games = new ArrayList<ArrayList<IGame>>();
		for (int i = 0; i < g.size(); i++) {
			games.add(new ArrayList<IGame>());
		}
		this.g = g;
	}

	/**
	 * Creates a new Game by an well formed input String
	 * 
	 * @param input
	 */
	public void createGame(String input) {
		String[] splitted = input.split(">");
		if (games.size() > 1) {
		IGame newResult=null;
		if (splitted.length >= 2) {
			// TODO new Result not instantiated
			newResult = g.get(1).setGame(splitted[1]);
		} else {
			newResult = g.get(1).setGame("-1:-1");
		}
			games.get(1).add(newResult);
		}
		//IGame newDate = new;
		games.get(0).add(g.get(0).setGame(splitted[0]));
	}

	/**
	 * Gives Back all Games with their IDs
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < games.get(0).size(); i++) {
			sb.append((i + 1)).append(": ");
			sb.append(games.get(0).get(i));
			if (games.size() > 1) {
				sb.append(">");
				sb.append(games.get(1).get(i));
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public ArrayList<ArrayList<IGame>> getGames() {
		return games;
	}

	public void setGames(ArrayList<ArrayList<IGame>> games) {
		this.games = games;
	}

	@Override
	public List<IGame> getIGame() {
		// TODO Auto-generated method stub
		return g;
	}

	
	// Getters and Setters
	/*
	 * public ArrayList<date> getGames() { return games; }
	 * 
	 * public void setGames(ArrayList<date> games) { this.games = games; }
	 * 
	 * public ArrayList<result> getResults() { return results; }
	 * 
	 * public void setResults(ArrayList<result> results) { this.results = results; }
	 */
}
