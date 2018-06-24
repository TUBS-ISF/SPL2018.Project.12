public class Main{
	public static void test() {
		original();
		teamList.getTeams().get(0).addPlayer("Tony Kroos");
		teamList.getTeams().get(0).addPlayer("Manuel Neuer");
		System.out.println(teamList.toString());
	}
}