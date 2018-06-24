public class Appearance{
	private void commandCalendar(String input) {
		original(input);
		String[] inputSplitted= input.split("\\.");
		Calendar c= new Calendar(Integer.parseInt(inputSplitted[1]), Integer.parseInt(inputSplitted[0]), gp);
		System.out.println("\n Calendar: \n"+c);
	}
}