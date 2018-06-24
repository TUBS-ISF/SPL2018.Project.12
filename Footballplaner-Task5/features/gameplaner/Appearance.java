public class Appearance{
	private void commandNewGame(String input) {
		original(input);
		gp.createGame(input);
		System.out.println("Game: "+input+" was created.");
	}
}