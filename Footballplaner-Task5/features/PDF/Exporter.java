import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.stream.Stream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Exporter {

	/**
	 * Creates a PDF for a given Gameplaner
	 * 
	 * @param gp
	 */
	public static void export(Gameplaner gp) {
		original(gp);
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream("Footballplaner.pdf"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		document.open();
		PdfPTable table = createTable(gp);
		try {
			document.add(table);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		document.close();

	}

	/**
	 * Creates a Table of the Arraylist games (and result) The Table is exportable
	 * to PDF
	 * 
	 * @param gp
	 * @return
	 */
	private static PdfPTable createTable(Gameplaner gp) {
		// int width =5;
		int width = 7;
		PdfPTable table = null;
		if (gp != null) {
			table = new PdfPTable(width);
			addHeader(table);
			// addRows(table,gp.getGames());

			addRows(table, gp);

		}
		return table;
	}

	/**
	 * Adds all Header needed for Games (and Results)
	 * 
	 * @param table
	 */
	private static void addHeader(PdfPTable table) {
		
		String [] head = {"Year", "Month", "Day", "Hours", "Minutes"};
		for (String title : head) {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(title));
			table.addCell(header);
		}
		original(table);
	}

	private static void addRows(PdfPTable table, Gameplaner gp) {
	  for(int i = 0; i<gp.getGames().size();i++) {
		  addRow(table, gp,i);
	  }
	}


	/**
	 * Adds a spcific game to the table
	 * 
	 * @param table
	 * @param game
	 */
	private static void addRow(PdfPTable table, Gameplaner gp, int i) {
		table.addCell("" + gp.getGames().get(i).getYear());
		table.addCell("" + gp.getGames().get(i).getMonth());
		table.addCell("" + gp.getGames().get(i).getDay());
		table.addCell("" + gp.getGames().get(i).getHours());
		table.addCell("" + gp.getGames().get(i).getMinutes());
		original(table,gp,i);
	}


}