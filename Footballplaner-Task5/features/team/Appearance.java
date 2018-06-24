public class Appearance{
	
	static TeamList teamList;
	
	private void commandAddTeam(String input) {
		original(input);
		Main.getTeamList().addTeam(new Team(input));
		System.out.println("Try adding Team...");
	}
	
	private void commandTeams() {
		original();
		System.out.println(Main.getTeamList().toString());
	}
	
}