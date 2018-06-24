public   class  Main {
	
	
	public static void run(){
		Appearance ap = new Appearance(gp);
		ap.run();
	}

	
	static TeamList teamList;

	
	
	 private static void  main__wrappee__team  (String[] args) {
		teamList=new TeamList();
	}

	
	
	 private static void  main__wrappee__gameplaner  (String[] args) {
		main__wrappee__team(args);
		test();
	}

	
	
	
	public static void main(String[] args) {
		System.out.println("Task5");
		gp=new Gameplaner();

		main__wrappee__gameplaner(args);
	}

	
	
	 private static void  test__wrappee__team  () {
		teamList.addTeam(new Team("Lehndorf"));
		teamList.addTeam(new Team("Ölper"));
		teamList.addTeam(new Team("Eintracht"));
		teamList.addTeam(new Team("Hondelage"));
		teamList.addTeam(new Team("Viktoria"));
		System.out.println(teamList.toString());
	}

	
	
	
	 private static void  test__wrappee__gameplaner  () {
		test__wrappee__team();
		System.out.println("Test 5");
		
		run();
	}

	
	
	
	 private static void  test__wrappee__date  () {
		test__wrappee__gameplaner();
		String s1 = "2011.11.11 13:30";
		String s2 = "2009.1.3 12:00>2:1";
		String s3 = "2009.3.3 13:00>2:5";
		String s4 = "2009.3.3 16:00>2:2";
		String s5 = "2009.3.10 13:00>2:1";

		gp.createGame(s1);
		gp.createGame(s2);
		gp.createGame(s3);
		gp.createGame(s4);
		gp.createGame(s5);

		System.out.println(gp.toString());
	}

	
	 private static void  test__wrappee__calendar  () {
		test__wrappee__date();
		Calendar march2009 = new Calendar(2009, 3, gp);
		System.out.println("\n Calendar: \n" + march2009);
	}

	
	 private static void  test__wrappee__export  () {
		test__wrappee__calendar();
		Exporter.export(gp);
	}

	
	 private static void  test__wrappee__import  () {
		test__wrappee__export();
		gp=Importer.importfromJson();
	}

	
	
	
	 private static void  test__wrappee__trainer  () {
		test__wrappee__import();
		teamList.getTeams().get(0).setTrainer("Karl Marx");
		teamList.getTeams().get(1).setTrainer("Joachim Löw");
		System.out.println(teamList.toString());
	}

	
	public static void test() {
		test__wrappee__trainer();
		teamList.getTeams().get(0).addPlayer("Tony Kroos");
		teamList.getTeams().get(0).addPlayer("Manuel Neuer");
		System.out.println(teamList.toString());
	}

	
	
	
	public static TeamList getTeamList() {
		return teamList;
	}

	
	
	static Gameplaner gp;


}
