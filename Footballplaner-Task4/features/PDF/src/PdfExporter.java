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

import interfaces.IGame;
import interfaces.planer;

public class PdfExporter implements interfaces.export{
	/**
	 * Creates a PDF for a given Gameplaner
	 * @param gp
	 */
	public void exportPlaner(planer gp) {
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

	}
	
	/**
	 * Creates a Table of the Arraylist games (and result)
	 * The Table is exportable to PDF
	 * @param gp
	 * @return
	 */
	private static PdfPTable createTable(planer gp) {
//@		 int width =5;
		int width = 7;
		PdfPTable table = null;
		if (gp != null) {
			table = new PdfPTable(width);
			addHeader(table);
//@			addRows(table,gp.getGames());

			ArrayList<ArrayList<IGame>> g =gp.getGames();
			if(g.size()>1) {
			addRows(table, g.get(0), g.get(1));
			}else {
				addRows(table,g.get(0));
			}

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
		Stream.of("Score1", "Score2").forEach(title -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.YELLOW);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(title));
			table.addCell(header);
		});
	}

	/**
	 * Adds all Elements of the games List
	 * @param table
	 * @param games
	 */
	private static void addRows(PdfPTable table, ArrayList<IGame> games) {
		for(IGame g: games) {
			addRow(table, g);
		}
	}

	/**
	 * Adds all Elements of games and results to the table
	 * @param table
	 * @param games
	 * @param results
	 */
	private static void addRows(PdfPTable table, ArrayList<IGame> games, ArrayList<IGame> results) {
		for(int i =0;i<games.size();i++) {
			addRow(table, games.get(i),results.get(i));
		}
	}

	/**
	 * Adds a spcific game to the table
	 * @param table
	 * @param game
	 */
	private static void addRow(PdfPTable table, IGame game) {
		String[] splitgameDate=game.toString().split(" ");
		String[] gameString=splitgameDate[0].toString().split("\\.");
		String[] dateString=splitgameDate[1].toString().split(":");
		table.addCell(gameString[0]);
		table.addCell(gameString[1]);
		table.addCell(gameString[2]);
		table.addCell(dateString[0]);
		table.addCell(dateString[1]);
	}

	/**
	 * Adds a Game and its result to the table
	 * @param table
	 * @param game
	 * @param result
	 */
	private static void addRow(PdfPTable table, IGame game, IGame result) {
		addRow(table, game);
		String[] resultString=result.toString().split(":");
		table.addCell(resultString[0]);
		table.addCell(resultString[1]);
	}
}
