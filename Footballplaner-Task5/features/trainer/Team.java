public class Team{
	
	private Trainer trainer = new Trainer("none");
	
	public void setTrainer(String trainer) {
		this.trainer=new Trainer(trainer);
	}
	
	public Trainer getTrainer() {
		return trainer;
	}
	
	public String toString() {
		String in= original();
		return in+ " , Trainer: "+trainer;
	}
}