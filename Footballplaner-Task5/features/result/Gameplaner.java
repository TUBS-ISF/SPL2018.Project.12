import java.util.*;


/**
 * Holds List of Games (and results) Creates new Games
 * 
 * @author David
 *
 */
public class Gameplaner extends Object{
	
	private ArrayList<Result> results;
	
	/**
	 * Constructor
	 * 
	 * @param result
	 */
	public Gameplaner() {
		results = new ArrayList<Result>();
	}
	
	public void createGame(String input) {
		original(input);
		String[] splitted = input.split(">");
		Result newResult;
		if (splitted.length >= 2) {
			newResult = new Result(splitted[1]);
		} else {
			newResult = new Result(-1, -1);
		}
		results.add(newResult);
	}
	
	public String toString() {
		String in = original();
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
	
	//Getters and Setters
	public ArrayList<Result> getResults() {
		return results;
	}

	public void setResults(ArrayList<Result> results) {
		this.results = results;
	}
}