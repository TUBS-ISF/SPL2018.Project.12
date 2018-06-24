public class Main{
	static TeamList teamList;
	
	public static void main (String[] args) {
		teamList=new TeamList();
	}
	
	public static void test() {
		teamList.addTeam(new Team("Lehndorf"));
		teamList.addTeam(new Team("Ölper"));
		teamList.addTeam(new Team("Eintracht"));
		teamList.addTeam(new Team("Hondelage"));
		teamList.addTeam(new Team("Viktoria"));
		System.out.println(teamList.toString());
	}
	
	
	public static TeamList getTeamList() {
		return teamList;
	}
}