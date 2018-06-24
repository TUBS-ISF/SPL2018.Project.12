import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.BaseColor;

public class Exporter {


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
}