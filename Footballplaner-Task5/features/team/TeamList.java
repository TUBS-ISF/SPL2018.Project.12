import java.util.ArrayList;

public class TeamList{
	private ArrayList <Team> teams;
	
	public TeamList() {
		teams = new ArrayList<Team>();
	}
	
	public ArrayList <Team> getTeams() {
		return teams;
	}
	
	public void addTeam(Team t) {
		teams.add(t);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Team t : teams) {
			sb.append(t.toString()).append("\n");
		}
		return sb.toString();
	}
}