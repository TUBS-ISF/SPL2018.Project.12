package planer;

import java.util.ArrayList;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public aspect result {

	public static class result_ {
		int score1;
		int score2;

		/**
		 * Constructor
		 */
		public result_() {
			score1 = -1;
			score2 = -1;
		}

		/**
		 * Constructor
		 * 
		 * @param score1
		 * @param score2
		 */
		public result_(int score1, int score2) {
			setResult(score1, score2);
		}

		/**
		 * Constructor
		 * 
		 * @param input
		 */
		public result_(String input) {
			setResult(input);
		}

		/**
		 * Setter
		 * 
		 * @param score1
		 * @param score2
		 */
		public void setResult(int score1, int score2) {
			this.score1 = score1;
			this.score2 = score2;
		}

		/**
		 * Converts a well formed String to an result and saves it as score1 and score2
		 * 
		 * @param input
		 */
		public void setResult(String input) {
			String[] splitted = input.split(":");
			score1 = Integer.parseInt(splitted[0]);
			score2 = Integer.parseInt(splitted[1]);
		}

		public int getScore1() {
			return score1;
		}

		public int getScore2() {
			return score2;
		}

		/**
		 * gives back the result as a string
		 */
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(score1).append(":");
			sb.append(score2);
			return sb.toString();
		}
	}

	private static ArrayList<result_> results = new ArrayList<result_>();
	
	void around(String input) : execution(public void Gameplaner.createGame(String)) && args(input) {
		proceed(input);
		String[] splitted = input.split(">");
		result_ newResult;
		if (splitted.length >= 2) {
			newResult = new result_(splitted[1]);
		} else {
			newResult = new result_(-1, -1);
		}
		results.add(newResult);
	}
	
	String around() : execution(public String Gameplaner.toString()){
		String in = proceed();
		//System.out.println(in);
		StringBuilder sb = new StringBuilder();
		String[] splitted = in.split("\n");
		sb.append(splitted[0]).append("\n");
		for (int i = 0; i < results.size(); i++) {
			sb.append(splitted[i+1]);
			sb.append(">");
			sb.append(results.get(i));
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	
	public ArrayList<result_> getResults() {
		return results;
	}

	public static void setResults(ArrayList<result_> r) {
		results = r;
	}
	
	
	void around(PdfPTable table) : execution(private void export.PDF.addHeader(PdfPTable)) && args(table){
		proceed(table);
		String [] score = {"Score1", "Score2"};
		for (String title : score) {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.YELLOW);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(title));
			table.addCell(header);
		}
	}
	
	void around(PdfPTable table, Gameplaner gp, int i) :execution(private void export.PDF.addRow(PdfPTable, Gameplaner,int))&&args(table, gp, i){
		proceed(table,gp,i);
		result_ result = getResults().get(i);
		table.addCell("" + result.getScore1());
		table.addCell("" + result.getScore2());
	}
	
	void around(String input): execution(private void appearance.cmd.commandChangeResult(String)) && args(input){
		proceed(input);
		String[] inputSplitted= input.split(" ");
		result_ result=getResults().get(Integer.parseInt(inputSplitted[0])-1);
		result.setResult(inputSplitted[1]);
		
		System.out.println("Changed Result of Game " + inputSplitted[0] +" to "+ inputSplitted[1]);
	}
	
	void around(String input): execution(private void appearance.*.commandGameById(String)) && args(input){
		proceed(input);
		System.out.print(">"+getResults().get(Integer.parseInt(input)-1));
	}
	
	String around(Gameplaner gp): execution(private String export.JSON.addGames(Gameplaner))&&args(gp){
		String in =proceed(gp);
		StringBuilder sb = new StringBuilder();
		sb.append(in);
		sb.append(","+'"'+"results"+'"'+":[");
		for(int i =0;i< results.size();i++) {
			sb.append(addResult(results.get(i)));
			if (i!=(results.size()-1)){
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	private String addResult(result_ r) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append('"'+"score1"+'"'+":");
		sb.append(r.getScore1());
		sb.append(","+'"'+"score2"+'"'+":");
		sb.append(r.getScore2());
		sb.append("}");
		return sb.toString();
	}
}
