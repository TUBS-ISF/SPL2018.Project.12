import com.itextpdf.text.Phrase; 
import com.itextpdf.text.pdf.PdfPCell; 
import com.itextpdf.text.pdf.PdfPTable; 
import com.itextpdf.text.BaseColor; import java.io.FileNotFoundException; 
import java.io.PrintWriter; 

import com.google.gson.Gson; 

public   class  Exporter {
	


	private static void addHeader(PdfPTable table) {
		String [] score = {"Score1", "Score2"};
		for (String title : score) {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.YELLOW);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(title));
			table.addCell(header);
		}
	}

	
	
	private static void addRow(PdfPTable table, Gameplaner gp, int i) {
		Result result = gp.getResults().get(i);
		table.addCell("" + result.getScore1());
		table.addCell("" + result.getScore2());
	}

	
	
	
	 private static void  export__wrappee__export  (Gameplaner gp) {
		System.out.println("Export");
	}

	
	/**
	 * exports a Gameplaner in to a JSON File
	 * JSON file will hold games (and results if feature is checked)
	 * @param gp
	 */
	public static void export(Gameplaner gp) {
		export__wrappee__export(gp);
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
