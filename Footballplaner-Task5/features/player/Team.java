import java.util.ArrayList;

public class Team{
	
	private ArrayList<Spieler> players;
	
	public Team(String input) {
		players=new ArrayList<Spieler>();
	}
	
	
	public void addPlayer(String input) {
		players.add(new Spieler(input));
	}
	
	public ArrayList<Spieler> getPlayers() {
		return players;
	}
	
	public String toString() {
		String in= original();
		StringBuilder sb = new StringBuilder();
		sb.append(in);
		sb.append(" Spieler: ");
		for (Spieler p : players) {
			sb.append(p.toString()).append(", ");
		}
		return sb.toString();
	}
	
}