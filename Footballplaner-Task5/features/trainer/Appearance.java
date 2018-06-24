
public class Appearance{
	private void commandAddTrainer(String input) {
		original(input);
		String[] splitted= input.split(" ",2);
		if (splitted.length==2) {
			Main.getTeamList().getTeams().get(Integer.parseInt(splitted[0])).setTrainer(splitted[1]);
		}else {
			System.out.println("Wrong input!");
		}
	}
}