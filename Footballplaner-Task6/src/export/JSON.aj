package export;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import Main.Main;
import planer.Gameplaner;
import planer.date.date_;

public aspect JSON {
	
	void around() : execution(private void appearance.cmd.commandExport()){
		proceed();
		Gameplaner gp= Main.getGp();
		export(gp);
	}
	
	after(): execution(public void Main.Main.test()){
		Gameplaner gp= Main.getGp();
		export(gp);
	}

	
	public static void export(Gameplaner gp) {
		Gson g = new Gson();
		//String json = g.toJson(gp);
		String json = createJson(gp);
		System.out.println(json);

		try {
			PrintWriter out = new PrintWriter("export.json");
			out.print(json);
			out.close();

		} catch (FileNotFoundException fE) {
			System.out.println("File not Found");
		}
	}
	
	private static String createJson(Gameplaner gp) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append(addGames(gp));
		sb.append("}");
		return sb.toString();
	}
	
	private static String addGames(Gameplaner gp) {
		StringBuilder sb = new StringBuilder();
		sb.append('"'+"games"+'"'+":[");
		for(int i =0;i< gp.getGames().size();i++) {
			sb.append(addGame(gp.getGames().get(i)));
			if (i!=(gp.getGames().size()-1)){
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	private static String addGame(date_ game) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append('"'+"year"+'"'+":");
		sb.append(game.getYear());
		sb.append(","+'"'+"month"+'"'+":");
		sb.append(game.getMonth());
		sb.append(","+'"'+"day"+'"'+":");
		sb.append(game.getDay());
		sb.append(","+'"'+"hours"+'"'+":");
		sb.append(game.getHours());
		sb.append(","+'"'+"minutes"+'"'+":");
		sb.append(game.getMinutes());
		sb.append("}");
		return sb.toString();
	}
}
