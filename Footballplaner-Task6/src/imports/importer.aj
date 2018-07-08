package imports;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Main.Main;
import planer.Gameplaner;
import planer.date.date_;
import planer.result;
import planer.result.result_;


public aspect importer {

	void around() : execution(private void appearance.cmd.commandImport()){
		proceed();
		Gameplaner gp= Main.getGp();
		importfromJson(gp);
	}
	
	after(): execution(public void Main.Main.test()){
		Gameplaner gp= Main.getGp();
		importfromJson(gp);
	}
	
	public Gameplaner importfromJson(Gameplaner p) {

		String json = null;
		FileReader fR = null;
		try {
			fR = new FileReader("export.json");
		} catch (FileNotFoundException fE) {
			System.err.println("File not found");
			return p;
		}
		BufferedReader bR = new BufferedReader(fR);
		try {
			StringBuilder sB = new StringBuilder();
			String line = bR.readLine();

			while (line != null) {
				sB.append(line);
				sB.append(System.lineSeparator());
				line = bR.readLine();
			}
			json = sB.toString();
			System.out.println("JsonIN: " + json);
			bR.close();
		} catch (IOException e) {
			System.err.println("Error reading File");
		}
		return createPlaner(p, json);
		/*
		 * if (json != null) { Gson g = new Gson(); gp = g.fromJson(json,
		 * planerImport.class); } else { return null; } //List<IGame> lIG =
		 * p.getIGame(); // for(int i =0;i<lIG.size();i++) { //
		 * lIG.get(i).setFirstValue(value); // } // p.setIGame(gp.g);
		 * ArrayList<ArrayList<IGame>> iGames = p.getGames(); for (int i = 0; i <
		 * iGames.size(); i++) { iGames.set(i, new ArrayList<IGame>()); //= new
		 * ArrayList<IGame>(); for (int j=0;j<gp.games.get(i).size();j++) {
		 * System.out.println("I: "+ i+" J: "+j + " value: "+ gp.games.get(i).get(j));
		 * iGames.get(i).add(gp.games.get(i).get(j)); } } p.setGames(iGames); return p;
		 */
	}

	private Gameplaner createPlaner(Gameplaner p, String json) {
		// String[] splitted = json.split("(?<=\\})(?=\\{)");
		JsonParser parser = new JsonParser();
		JsonObject jO = parser.parse(json).getAsJsonObject();
		JsonElement games = jO.get("games");
		JsonArray dateArray = games.getAsJsonArray();
		JsonElement results = jO.get("results");
		JsonArray resultArray = results.getAsJsonArray();
		ArrayList<date_> dateList= new ArrayList<date_>();
		ArrayList<result_> resultList= new ArrayList<result_>();
		//ArrayList<ArrayList<IGame>> aL = new ArrayList<ArrayList<IGame>>();
				
		for (int i = 0; i < dateArray.size(); i++) {
			int year= dateArray.get(i).getAsJsonObject().get("year").getAsInt();
			int month= dateArray.get(i).getAsJsonObject().get("month").getAsInt();
			int day= dateArray.get(i).getAsJsonObject().get("day").getAsInt();
			int hours= dateArray.get(i).getAsJsonObject().get("hours").getAsInt();
			int minutes= dateArray.get(i).getAsJsonObject().get("minutes").getAsInt();
			date_ d= new date_(year+"."+month+"."+day+" "+hours+":"+minutes);
			dateList.add(d);
		}
		p.setGames(dateList);
		//aL.add(dateList);
		
		if (resultArray!=null) {
			for (int i = 0; i < resultArray.size(); i++) {
				int result1= resultArray.get(i).getAsJsonObject().get("score1").getAsInt();
				int result2= resultArray.get(i).getAsJsonObject().get("score2").getAsInt();
				result_ r= new result_(result1+":"+result2);
				resultList.add(r);
			}
			result.setResults(resultList);
		}
//		p.setGames(aL);
		return p;
	}


}
