package team;

public aspect carer {

public static class Carer{
		
		private String name;
		
		public Carer(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
		public String toString() {
			return name;
		}
	}
	
	private Carer team.Team.carer;

	
	public void team.Team.setCarer(String carer) {
		this.carer=new Carer(carer);
	}
	
	public Carer team.Team.getCarer() {
		return carer;
	}
	
	String around(team.Team t) : execution(public String team.Team.toString())&& this(t){
		String in = proceed(t);
		return in+ " , Carer: "+t.carer;
	}
	
	void around(): execution(public static void Main.Main.test()){
		proceed();
		team.getTeamList().getTeams().get(0).setCarer("Oliver Bierhoff");
		team.getTeamList().getTeams().get(1).setCarer("Paul Breitner");
		System.out.println(team.getTeamList().toString());
	}
	
	void around(String input) : execution(private void appearance.cmd.commandAddCarer(String)) &&args(input){
		proceed(input);
		String[] splitted= input.split(" ",2);
		if (splitted.length==2) {
			team.getTeamList().getTeams().get(Integer.parseInt(splitted[0])).setCarer(splitted[1]);
		}else {
			System.out.println("Wrong input!");
		}
	}
}
