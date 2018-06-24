public class Main{
	
	
	public static void test() {
		original();
		teamList.getTeams().get(0).setTrainer("Karl Marx");
		teamList.getTeams().get(1).setTrainer("Joachim Löw");
		System.out.println(teamList.toString());
	}
}