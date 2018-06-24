import java.util.ArrayList; 

public   class  Team {
	
	private String name;

	
	
	public Team  (String input) {
		name = input;
	
		players=new ArrayList<Spieler>();
	}

	
	
	public String getName() {
		return name;
	}

	
	
	public void setName(String name) {
		this.name = name;
	}

	
	
	 private String  toString__wrappee__team  () {
		return name;
	}

	
	
	 private String  toString__wrappee__trainer  () {
		String in= toString__wrappee__team();
		return in+ " , Trainer: "+trainer;
	}

	
	
	public String toString() {
		String in= toString__wrappee__trainer();
		StringBuilder sb = new StringBuilder();
		sb.append(in);
		sb.append(" Spieler: ");
		for (Spieler p : players) {
			sb.append(p.toString()).append(", ");
		}
		return sb.toString();
	}

	
	
	private Trainer trainer = new Trainer("none");

	
	
	public void setTrainer(String trainer) {
		this.trainer=new Trainer(trainer);
	}

	
	
	public Trainer getTrainer() {
		return trainer;
	}

	
	
	private ArrayList<Spieler> players;

	
	
	
	public void addPlayer(String input) {
		players.add(new Spieler(input));
	}

	
	
	public ArrayList<Spieler> getPlayers() {
		return players;
	}


}
