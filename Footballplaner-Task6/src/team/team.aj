package team;

import java.util.ArrayList;
import Main.Main;

public aspect team {
	

	public static class TeamList{
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
	
	public class Team{
		private String name;
		
		public Team(String input) {
			name = input;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String toString() {
			return name;
		}
	}
	
	TeamList teamList =new TeamList();
	static TeamList Main.teamList2 = new TeamList();
	
	void around(): execution(public static void Main.Main.test()){
		Main.teamList2.addTeam(new Team("Lehndorf"));
		Main.teamList2.addTeam(new Team("Ölper"));
		Main.teamList2.addTeam(new Team("Eintracht"));
		Main.teamList2.addTeam(new Team("Hondelage"));
		Main.teamList2.addTeam(new Team("Viktoria"));
		teamList.addTeam(new Team("Lehndorf"));
		teamList.addTeam(new Team("Ölper"));
		teamList.addTeam(new Team("Eintracht"));
		teamList.addTeam(new Team("Hondelage"));
		teamList.addTeam(new Team("Viktoria"));
		System.out.println(Main.teamList2.toString());

		proceed();
	}
	
	public static TeamList getTeamList() {
		return Main.teamList2;
	}
	
	static public TeamList Main.getTeamList(){
		return teamList2;
	}
	
	void around(String input) : execution(private void appearance.cmd.commandAddTeam(String)) &&args(input){
		proceed(input);
		teamList.addTeam(new Team(input));
		System.out.println("Try adding Team...");
	}
	
	void around() : execution(private void appearance.cmd.commandTeams()){
		proceed();
		System.out.println(teamList.toString());
	}
	

}
