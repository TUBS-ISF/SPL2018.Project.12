package export;

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

import planer.date;
import planer.result;

/**
 * Class to Export to PDF with iTextPDF
 * @author David
 *
 */
public class PdfExporter {

	/**
	 * Creates a PDF for a given Gameplaner
	 * @param gp
	 */
	public static void export(planer.Gameplaner gp) {
		// #if PDF
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream("Footballplaner.pdf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		document.open();
		//Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		//Chunk chunk = new Chunk("Footballplaner\n", font);
		PdfPTable table = createTable(gp);
		try {
			//document.add(chunk);
			document.add(table);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		document.close();
		// #endif

	}
	
	/**
	 * Creates a Table of the Arraylist games (and result)
	 * The Table is exportable to PDF
	 * @param gp
	 * @return
	 */
	private static PdfPTable createTable(planer.Gameplaner gp) {
		// #if !result
//@		 int width =5;
		// #endif
		// #if result
		int width = 7;
		// #endif
		PdfPTable table = null;
		if (gp != null) {
			table = new PdfPTable(width);
			addHeader(table);
			// #if !result
//@			addRows(table,gp.getGames());
			// #endif

			// #if result
			addRows(table, gp.getGames(), gp.getResults());
			// #endif

		}
		return table;
	}

	/**
	 * Adds all Header needed for Games (and Results)
	 * @param table
	 */
	private static void addHeader(PdfPTable table) {
		Stream.of("Year", "Month", "Day", "Hours", "Minutes").forEach(title -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(title));
			table.addCell(header);
		});
		// #if result
		Stream.of("Score1", "Score2").forEach(title -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.YELLOW);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(title));
			table.addCell(header);
		});
		// #endif
	}

	//#if !result
//@	/**
//@	 * Adds all Elements of the games List
//@	 * @param table
//@	 * @param games
//@	 */
//@	private static void addRows(PdfPTable table, ArrayList<date> games) {
//@		for(date g: games) {
//@			addRow(table, g);
//@		}
//@	}
	//#endif

	//#if result
	/**
	 * Adds all Elements of games and results to the table
	 * @param table
	 * @param games
	 * @param results
	 */
	private static void addRows(PdfPTable table, ArrayList<date> games, ArrayList<result> results) {
		for(int i =0;i<games.size();i++) {
			addRow(table, games.get(i),results.get(i));
		}
	}
	//#endif

	/**
	 * Adds a spcific game to the table
	 * @param table
	 * @param game
	 */
	private static void addRow(PdfPTable table, date game) {
		table.addCell("" + game.getYear());
		table.addCell("" + game.getMonth());
		table.addCell("" + game.getDay());
		table.addCell("" + game.getHours());
		table.addCell("" + game.getMinutes());
	}

	//#if result
	/**
	 * Adds a Game and its result to the table
	 * @param table
	 * @param game
	 * @param result
	 */
	private static void addRow(PdfPTable table, date game, result result) {
		addRow(table, game);
		table.addCell("" + result.getScore1());
		table.addCell("" + result.getScore2());
	}
	//#endif
}
