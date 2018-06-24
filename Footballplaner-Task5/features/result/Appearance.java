public class Appearance{
	private void commandChangeResult(String input) {
		original(input);
		String[] inputSplitted= input.split(" ");
		Result result=gp.getResults().get(Integer.parseInt(inputSplitted[0])-1);
		result.setResult(inputSplitted[1]);
		
		System.out.println("Changed Result of Game " + inputSplitted[0] +" to "+ inputSplitted[1]);
	}
	
	private void commandGameById(String input) {
		original(input);
		System.out.print(">"+gp.getResults().get(Integer.parseInt(input)-1));
	}
}