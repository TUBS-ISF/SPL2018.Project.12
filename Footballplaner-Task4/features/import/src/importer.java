import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import interfaces.IGame;
import interfaces.planer;

public class importer implements interfaces.importer {

	public interfaces.planer importfromJson(interfaces.planer p, List<IGame> g) {

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
		return createPlaner(p,g, json);
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

	private planer createPlaner(planer p,List<IGame> g, String json) {
		// String[] splitted = json.split("(?<=\\})(?=\\{)");
		JsonParser parser = new JsonParser();
		JsonObject jO = parser.parse(json).getAsJsonObject();
		JsonElement games = jO.get("games");
		JsonArray gamesArray = games.getAsJsonArray();
		JsonArray dateArray = gamesArray.get(0).getAsJsonArray();
		ArrayList<IGame> dateList= new ArrayList<IGame>();
		ArrayList<IGame> resultList= new ArrayList<IGame>();
		ArrayList<ArrayList<IGame>> aL = p.getGames();
		//ArrayList<ArrayList<IGame>> aL = new ArrayList<ArrayList<IGame>>();
				
		for (int i = 0; i < dateArray.size(); i++) {
			int year= dateArray.get(i).getAsJsonObject().get("year").getAsInt();
			int month= dateArray.get(i).getAsJsonObject().get("month").getAsInt();
			int day= dateArray.get(i).getAsJsonObject().get("day").getAsInt();
			int hours= dateArray.get(i).getAsJsonObject().get("hours").getAsInt();
			int minutes= dateArray.get(i).getAsJsonObject().get("minutes").getAsInt();
			date d= new date(year,month,day,hours,minutes);
			dateList.add(d);
		}
		aL.set(0, dateList);
		//aL.add(dateList);
		
		if (gamesArray.size() > 1&&g.size()>1) {
			JsonArray resultArray =gamesArray.get(1).getAsJsonArray();
			for (int i = 0; i < resultArray.size(); i++) {
				int result1= resultArray.get(i).getAsJsonObject().get("score1").getAsInt();
				int result2= resultArray.get(i).getAsJsonObject().get("score2").getAsInt();
				IGame r= g.get(1).setGame(result1+":"+result2);
				resultList.add(r);
			}
			aL.set(1,resultList);
		}
		p.setGames(aL);
		return p;
	}


	@Override
	public planer importfrom(interfaces.planer p, List<IGame> g) {
		// TODO Auto-generated method stub
		return importfromJson(p,g);
	}
}
