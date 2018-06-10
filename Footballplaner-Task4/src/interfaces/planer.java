package interfaces;

import java.util.ArrayList;
import java.util.List;

public interface planer {

	//public ArrayList<ArrayList<IGame>> games = new ArrayList<ArrayList<IGame>>();

	//public planer createPlaner(int size, List<IGame> g);
	public void setIGame(List<IGame> g);
	public List<IGame> getIGame();
	public void createGame(String input);
	public ArrayList<ArrayList<IGame>> getGames();
	public void setGames(ArrayList<ArrayList<IGame>> games);

	public String toString();
}
