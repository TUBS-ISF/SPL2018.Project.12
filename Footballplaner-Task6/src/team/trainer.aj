package team;

public aspect trainer {
	
	public static class Trainer{
		
		private String name;
		
		public Trainer(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
		public String toString() {
			return name;
		}
	}
	
	private Trainer team.Team.trainer;

	
	public void team.Team.setTrainer(String trainer) {
		this.trainer=new Trainer(trainer);
	}
	
	public Trainer team.Team.getTrainer() {
		return trainer;
	}
	
	String around(team.Team t) : execution(public String team.Team.toString())&& this(t){
		String in = proceed(t);
		return in+ " , Trainer: "+t.trainer;
	}
	
	void around(): execution(public static void Main.Main.test()){
		proceed();
		team.getTeamList().getTeams().get(0).setTrainer("Karl Marx");
		team.getTeamList().getTeams().get(1).setTrainer("Joachim Löw");
		System.out.println(team.getTeamList().toString());
	}
	
	void around(String input) : execution(private void appearance.cmd.commandAddTrainer(String)) &&args(input){
		proceed(input);
		String[] splitted= input.split(" ",2);
		if (splitted.length==2) {
			team.getTeamList().getTeams().get(Integer.parseInt(splitted[0])).setTrainer(splitted[1]);
		}else {
			System.out.println("Wrong input!");
		}
	}
	
}
