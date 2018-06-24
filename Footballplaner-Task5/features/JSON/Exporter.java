import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.google.gson.Gson;

public class Exporter{
	/**
	 * exports a Gameplaner in to a JSON File
	 * JSON file will hold games (and results if feature is checked)
	 * @param gp
	 */
	public static void export(Gameplaner gp) {
		original(gp);
		Gson g = new Gson();
		String json = g.toJson(gp);
		System.out.println(json);

		try {
			PrintWriter out = new PrintWriter("export.json");
			out.print(json);
			out.close();

		} catch (FileNotFoundException fE) {
			System.out.println("File not Found");
		}
	}
}