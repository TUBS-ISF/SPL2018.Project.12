package team;

import java.util.ArrayList;

import Main.Main;

public aspect player {
	public static class Spieler {
		
		private String name;
		
		public Spieler(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
		public String toString() {
			return name;
		}
	}
	
	private ArrayList<Spieler> team.Team.players = new ArrayList<Spieler>();
	
	
	public void team.Team.addPlayer(String input) {
		players.add(new Spieler(input));
	}
	
	public ArrayList<Spieler> team.Team.getPlayers() {
		return players;
	}
	
	String around(team.Team t) : execution(public String team.Team.toString())&& this(t){
		String in = proceed(t);
		StringBuilder sb = new StringBuilder();
		sb.append(in);
		sb.append(" Spieler: ");
		for (Spieler p :  t.players) {
			sb.append(p.toString()).append(", ");
		}
		return sb.toString();
	}
	
	void around(): execution(public static void Main.Main.test()){
		proceed();
		System.out.println("TeamList: "+team.getTeamList().toString());
		team.getTeamList().getTeams().get(0).addPlayer("Tony Kroos");
		team.getTeamList().getTeams().get(0).addPlayer("Manuel Neuer");
		team.getTeamList().getTeams().get(1).addPlayer("Peter Pan");
		System.out.println(team.getTeamList().toString());
	}
	
	
	
	void around(String input) : execution(private void appearance.cmd.commandAddPlayer(String)) &&args(input){
		proceed(input);
		String[] splitted= input.split(" ",2);
		if (splitted.length==2) {
			team.getTeamList().getTeams().get(Integer.parseInt(splitted[0])).addPlayer(splitted[1]);
		}else {
			System.out.println("Wrong input!");
		}
	}
	
}
