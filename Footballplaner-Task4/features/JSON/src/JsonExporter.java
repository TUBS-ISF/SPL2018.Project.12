import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.google.gson.Gson;
import interfaces.planer;

public class JsonExporter implements interfaces.export{
	public void exportPlaner(planer gp) {

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
