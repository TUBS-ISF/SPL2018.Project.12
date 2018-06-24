public class Appearance{
	
	private void commandGameById(String input) {
		System.out.print(gp.getGames().get(Integer.parseInt(input)-1));
		original(input);
	}
}